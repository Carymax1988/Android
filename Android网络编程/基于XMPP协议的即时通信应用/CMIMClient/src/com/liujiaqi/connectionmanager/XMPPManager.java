package com.liujiaqi.connectionmanager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterGroup;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;

import com.liujiaqi.bean.UserBean;
import com.liujiaqi.contants.GlobalContantsApplication;

import android.util.Log;

public class XMPPManager {
	private static XMPPConnection mXmppConnection = null;
	
	public static XMPPConnection getmXmppConnection() {
		return mXmppConnection;
	}

	public static void setmXmppConnection(XMPPConnection mXmppConnection) {
		XMPPManager.mXmppConnection = mXmppConnection;
	}

	//���ӷ�����
	public static boolean connectServer(String ip,int port){
		//ָ�����ӵ��������Ĳ�������վ�Ͷ˿�
		ConnectionConfiguration mConnectionConfiguration = new ConnectionConfiguration(ip,port);
		//��ʼ��XMPPConnection����
		mXmppConnection = new XMPPConnection(mConnectionConfiguration);
		//������XMPP������
		try {
			mXmppConnection.connect();
			return true;
		} catch (XMPPException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//������ϵ��
	public static boolean createNewUser(String sid,String pwd){
		if(mXmppConnection == null){
			return false;
		}
		AccountManager mAccountManager = mXmppConnection.getAccountManager();
		try {
			//��ָ�����û��������봴���û�
			mAccountManager.createAccount(sid, pwd);
			return true;
		} catch (XMPPException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//��¼����
	public static boolean login(String sid,String pwd){
		if(mXmppConnection == null){
			return false;
		}
		//ʹ��ָ�����û����������¼
		try {
			System.out.println("sid = "+sid);
			System.out.println("pwd = "+pwd);
			mXmppConnection.login(sid, pwd);
			return true;
		} catch (XMPPException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//�û�����
	public static void logout(){
		if(mXmppConnection == null){
			return;
		}
		mXmppConnection.disconnect();
	}
	
	//����
	public static boolean dismisslogin(){
		if(mXmppConnection == null){
			return false;
		}
		//Presence����������û����Ƿ����ߵ���������
		Presence mPresence = new Presence(Presence.Type.unavailable);
		//��������ύ������
		mXmppConnection.sendPacket(mPresence);
		return true;
	}
	
	//��ȡ��ϵ���б�
	public static void getContactsList(){
		if(mXmppConnection == null){
			return;
		}
		//�½�Roster����
		Roster roster = mXmppConnection.getRoster();
		ArrayList<ArrayList<UserBean>> templist = GlobalContantsApplication.getInstens().getContactsList();
		HashMap<String, String> tempmap = GlobalContantsApplication.getInstens().getMap();
		templist.clear();
		tempmap.clear();
		int i = 0;
		for(RosterGroup group:roster.getGroups()){
			String mapKey = group.getName();
			tempmap.put(String.valueOf(i), mapKey);
			i++;
			ArrayList<UserBean> groupusers = new ArrayList<UserBean>();
			/*System.out.println("groupname = "+group.getName());
			System.out.println("groupcount = "+group.getEntryCount());*/
			//��ȡ��ϵ���б�ļ���
			Collection<RosterEntry> rg = group.getEntries();
			//������ϵ���б�
			for(RosterEntry rosterEntry : rg){
				UserBean user = new UserBean();
				user.setName(rosterEntry.getName());
				user.setUser(rosterEntry.getUser());
				//��ȡ����״̬����
				Presence presence = roster.getPresence(rosterEntry.getUser());
				if(presence!=null){
					System.out.println("presence.getStatus() = "+presence.getStatus());
					user.setStatus(presence.getStatus());
				}else{
					user.setStatus(null);
				}
				groupusers.add(user);
				//��ȡ��ϵ���б�
				/*System.out.println("Name === "+rosterEntry.getName());
				System.out.println("User === "+rosterEntry.getUser());
				System.out.println("Type === "+rosterEntry.getType());
				System.out.println("Groups === "+rosterEntry.getGroups());*/
				/*//��ȡ����״̬����
				Presence presence = roster.getPresence(rosterEntry.getUser());
				//��ȡ����״̬
				if(presence!=null){
					System.out.println("presence.getStatus() = "+presence.getStatus());
					System.out.println("presence.getFrom() = "+presence.getFrom());
					System.out.println("presence.getLanguage() = "+presence.getLanguage());
					System.out.println("presence.getPacketID() = "+presence.getPacketID());
					System.out.println("presence.getPriority() = "+presence.getPriority());
					System.out.println("presence.getTo() = "+presence.getTo());
					System.out.println("presence.getXmlns() = "+presence.getXmlns());
					System.out.println("presence.getDefaultLanguage() = "+presence.getDefaultLanguage());
					System.out.println("presence.getMode() = "+presence.getMode());
					System.out.println("presence.getError() = "+presence.getError());
				}*/
			}
			templist.add(groupusers);
		}
	}
	
	//��ȡ��ϵ��״̬
	public static void getContactsStatues(){
		if(mXmppConnection == null){
			return;
		}
		//�½�Roster����
		Roster roster = mXmppConnection.getRoster();
		//��ȡ��ϵ���б�ļ���
		Collection<RosterEntry> rg = roster.getEntries();
		//������ϵ���б�
		for(RosterEntry rosterEntry:rg){
			//��ȡ����״̬����
			Presence presence = roster.getPresence(rosterEntry.getUser());
			//��ȡ����״̬
			if(presence!=null){
				System.out.println("aaaaaaa = "+presence.getStatus());
			}
		}
	}
	
	//��ȡָ����ϵ��״̬
	public static String getSomeOneStatus(String user){
		if(mXmppConnection == null){
			return null;
		}
		//�½�Roster����
		Roster roster = mXmppConnection.getRoster();
		RosterEntry rosterEntry = roster.getEntry(user);
		if(rosterEntry!=null){
			//��ȡ����״̬����
			Presence presence = roster.getPresence(rosterEntry.getUser());
			//��ȡ����״̬
			if(presence!=null){
				return presence.getStatus();
			}
		}
		return null;
	}
	
	//����״̬������ǩ����
	public static void setStatus(String s){
		if (mXmppConnection == null) {
			return;
		}
		//�½�״̬Ϊ����
		Presence mPresence = new Presence(Presence.Type.available);
		//����״̬��Ϣ
		mPresence.setStatus(s);
		//�������ð�����������״̬
		mXmppConnection.sendPacket(mPresence);
	}
	
	//�����ϵ��
	public static void addContacts(String user,String raw){
		if(mXmppConnection == null){
			return;
		}
		//�½�Roster����
		Roster roster = mXmppConnection.getRoster();
		try {
			//�����ϵ�ˣ�3�������ֱ�Ϊ���û�����JID�����ǳƺͷ���
			roster.createEntry(user, null, new String[]{raw});
		} catch (XMPPException e) {
			//�׳��쳣
			e.printStackTrace();
		}
	}
	
	//ɾ����ϵ��
	public static void deleteContacts(String user){
		if(mXmppConnection == null){
			return;
		}
		//�½�Roster����
		Roster roster = mXmppConnection.getRoster();
		try {
			//ɾ����ϵ��
			roster.removeEntry(roster.getEntry(user));
		} catch (XMPPException e) {
			e.printStackTrace();
		}
	}
	
	//������ϵ�������Ϣ
	/**
	 * �յ���������ʱ���XMPP��Ϣ���£�
	 * <presence id="rm1952_24" to="newuser@openfire" from="windows1@openfire" type="subscribed"></presence>
	 * �յ���ɾ�����ĵ�ʱ���XMPP��Ϣ���£�
	 * <presence to="newuser@openfire" from="windows1@openfire" type="unsubscribe"></presence>
	 */
	public static void addListener(){
		if(mXmppConnection == null){
			return;
		}
		//�½���Ϣ������
		PacketFilter filterMessage = new PacketTypeFilter(Presence.class);
		//����������
		PacketListener myListener = new PacketListener() {
			@Override
			public void processPacket(Packet packet) {
				//��Ϣ����ֵ��һ���µ�Presence
				final Presence mPresence = (Presence) packet;
				if(mPresence.getType().equals(Presence.Type.subscribe)){
					//�յ������ĵ���Ϣ�������ڸô�����
					listener.subscribeMessage(packet);
				}else if(mPresence.getType().equals(Presence.Type.unsubscribe)){
					//���յ�ȡ��������Ϣ�������ڸô�����
					listener.unsubscribeMessage(packet);
				}
				//packet.toXML()���Խ����յ�����Ϣת��ΪXML�ַ�����ʽ
			}
		};
		//��XMPP������ע��ü���
		mXmppConnection.addPacketListener(myListener, filterMessage);
	}
	
	//������Ϣ
	public static void addMessageListener(){
		//�����Ϣ����
		mXmppConnection.getChatManager().addChatListener(new ChatManagerListener() {
			@Override
			//������Ϣ
			public void chatCreated(Chat chat, boolean createdLocally) {
				//�����Ϣ��Դ,�Ƿ�Ϊ���ط�����
				//����Ϊ���յ�����Ϣ
				if(!createdLocally){
					//�������յ�����Ϣ
					chat.addMessageListener(new MessageListener() {
						@Override
						public void processMessage(Chat chat, Message message) {
							//��ӡ���յ�����Ϣ
							Log.d("TAG", "���յ�����ϢΪ��"+message.getBody());
							listener.receiveMessage(chat, message);
						}
					});
				}
			}
		});
	}
	
	//������Ϣ
	public static void sendMessage(String user,String message){
		//�½�һ����Ϣ�߳�
		Chat chat = mXmppConnection.getChatManager()
				//ָ�������ˣ��������Ϣ����
				.createChat(user, new MessageListener() {
					@Override
					//������Ϣ
					public void processMessage(Chat chat, Message message) {
						//��ӡ���յ�����Ϣ
						Log.d("TAG", "Received message:"+message);
					}
				});
		//�����µ���Ϣ
		Message newMessage = new Message();
		//������Ϣ����
		newMessage.setBody(message);
		try {
			//������Ϣ
			chat.sendMessage(newMessage);
		} catch (XMPPException e) {
			e.printStackTrace();
		}
	}
	
	static XMPPClientListener listener = null;
	
	public static void setXMPPClientListener(XMPPClientListener listener){
		XMPPManager.listener = listener;
	}
	
	public interface XMPPClientListener{
		public void subscribeMessage(Packet packet);
		public void unsubscribeMessage(Packet packet);
		public void receiveMessage(Chat chat, Message message);
	}
}
