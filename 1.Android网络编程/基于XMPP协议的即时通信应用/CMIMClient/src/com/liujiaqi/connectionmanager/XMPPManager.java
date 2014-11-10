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

	//连接服务器
	public static boolean connectServer(String ip,int port){
		//指定连接到服务器的参数：网站和端口
		ConnectionConfiguration mConnectionConfiguration = new ConnectionConfiguration(ip,port);
		//初始化XMPPConnection连接
		mXmppConnection = new XMPPConnection(mConnectionConfiguration);
		//连接上XMPP服务器
		try {
			mXmppConnection.connect();
			return true;
		} catch (XMPPException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//创建联系人
	public static boolean createNewUser(String sid,String pwd){
		if(mXmppConnection == null){
			return false;
		}
		AccountManager mAccountManager = mXmppConnection.getAccountManager();
		try {
			//用指定的用户名和密码创建用户
			mAccountManager.createAccount(sid, pwd);
			return true;
		} catch (XMPPException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//登录代码
	public static boolean login(String sid,String pwd){
		if(mXmppConnection == null){
			return false;
		}
		//使用指定的用户名和密码登录
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
	
	//用户下线
	public static void logout(){
		if(mXmppConnection == null){
			return;
		}
		mXmppConnection.disconnect();
	}
	
	//隐身
	public static boolean dismisslogin(){
		if(mXmppConnection == null){
			return false;
		}
		//Presence类可以设置用户的是否在线的类型属性
		Presence mPresence = new Presence(Presence.Type.unavailable);
		//向服务器提交该属性
		mXmppConnection.sendPacket(mPresence);
		return true;
	}
	
	//获取联系人列表
	public static void getContactsList(){
		if(mXmppConnection == null){
			return;
		}
		//新建Roster对象
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
			//获取联系人列表的集合
			Collection<RosterEntry> rg = group.getEntries();
			//遍历联系人列表
			for(RosterEntry rosterEntry : rg){
				UserBean user = new UserBean();
				user.setName(rosterEntry.getName());
				user.setUser(rosterEntry.getUser());
				//获取好友状态对象
				Presence presence = roster.getPresence(rosterEntry.getUser());
				if(presence!=null){
					System.out.println("presence.getStatus() = "+presence.getStatus());
					user.setStatus(presence.getStatus());
				}else{
					user.setStatus(null);
				}
				groupusers.add(user);
				//获取联系人列表
				/*System.out.println("Name === "+rosterEntry.getName());
				System.out.println("User === "+rosterEntry.getUser());
				System.out.println("Type === "+rosterEntry.getType());
				System.out.println("Groups === "+rosterEntry.getGroups());*/
				/*//获取好友状态对象
				Presence presence = roster.getPresence(rosterEntry.getUser());
				//获取好友状态
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
	
	//获取联系人状态
	public static void getContactsStatues(){
		if(mXmppConnection == null){
			return;
		}
		//新建Roster对象
		Roster roster = mXmppConnection.getRoster();
		//获取联系人列表的集合
		Collection<RosterEntry> rg = roster.getEntries();
		//遍历联系人列表
		for(RosterEntry rosterEntry:rg){
			//获取好友状态对象
			Presence presence = roster.getPresence(rosterEntry.getUser());
			//获取好友状态
			if(presence!=null){
				System.out.println("aaaaaaa = "+presence.getStatus());
			}
		}
	}
	
	//获取指定联系人状态
	public static String getSomeOneStatus(String user){
		if(mXmppConnection == null){
			return null;
		}
		//新建Roster对象
		Roster roster = mXmppConnection.getRoster();
		RosterEntry rosterEntry = roster.getEntry(user);
		if(rosterEntry!=null){
			//获取好友状态对象
			Presence presence = roster.getPresence(rosterEntry.getUser());
			//获取好友状态
			if(presence!=null){
				return presence.getStatus();
			}
		}
		return null;
	}
	
	//设置状态（个性签名）
	public static void setStatus(String s){
		if (mXmppConnection == null) {
			return;
		}
		//新建状态为在线
		Presence mPresence = new Presence(Presence.Type.available);
		//设置状态信息
		mPresence.setStatus(s);
		//发送配置包，设置在线状态
		mXmppConnection.sendPacket(mPresence);
	}
	
	//添加联系人
	public static void addContacts(String user,String raw){
		if(mXmppConnection == null){
			return;
		}
		//新建Roster对象
		Roster roster = mXmppConnection.getRoster();
		try {
			//添加联系人，3个参数分别为：用户名（JID）、昵称和分组
			roster.createEntry(user, null, new String[]{raw});
		} catch (XMPPException e) {
			//抛出异常
			e.printStackTrace();
		}
	}
	
	//删除联系人
	public static void deleteContacts(String user){
		if(mXmppConnection == null){
			return;
		}
		//新建Roster对象
		Roster roster = mXmppConnection.getRoster();
		try {
			//删除联系人
			roster.removeEntry(roster.getEntry(user));
		} catch (XMPPException e) {
			e.printStackTrace();
		}
	}
	
	//监听联系人添加信息
	/**
	 * 收到订阅请求时候的XMPP信息如下：
	 * <presence id="rm1952_24" to="newuser@openfire" from="windows1@openfire" type="subscribed"></presence>
	 * 收到被删除订阅的时候的XMPP信息如下：
	 * <presence to="newuser@openfire" from="windows1@openfire" type="unsubscribe"></presence>
	 */
	public static void addListener(){
		if(mXmppConnection == null){
			return;
		}
		//新建消息过滤器
		PacketFilter filterMessage = new PacketTypeFilter(Presence.class);
		//创建监听器
		PacketListener myListener = new PacketListener() {
			@Override
			public void processPacket(Packet packet) {
				//消息包赋值给一个新的Presence
				final Presence mPresence = (Presence) packet;
				if(mPresence.getType().equals(Presence.Type.subscribe)){
					//收到请求订阅的信息，可以在该处处理
					listener.subscribeMessage(packet);
				}else if(mPresence.getType().equals(Presence.Type.unsubscribe)){
					//接收到取消订阅信息，可以在该处处理
					listener.unsubscribeMessage(packet);
				}
				//packet.toXML()可以将接收到的信息转化为XML字符串格式
			}
		};
		//在XMPP连接上注册该监听
		mXmppConnection.addPacketListener(myListener, filterMessage);
	}
	
	//接收消息
	public static void addMessageListener(){
		//添加消息监听
		mXmppConnection.getChatManager().addChatListener(new ChatManagerListener() {
			@Override
			//创建消息
			public void chatCreated(Chat chat, boolean createdLocally) {
				//检测消息来源,是否为本地发出的
				//这里为接收到的消息
				if(!createdLocally){
					//监听接收到的消息
					chat.addMessageListener(new MessageListener() {
						@Override
						public void processMessage(Chat chat, Message message) {
							//打印接收到的消息
							Log.d("TAG", "接收到的消息为："+message.getBody());
							listener.receiveMessage(chat, message);
						}
					});
				}
			}
		});
	}
	
	//发送消息
	public static void sendMessage(String user,String message){
		//新建一个消息线程
		Chat chat = mXmppConnection.getChatManager()
				//指定接收人，并添加消息监听
				.createChat(user, new MessageListener() {
					@Override
					//处理消息
					public void processMessage(Chat chat, Message message) {
						//打印接收到的消息
						Log.d("TAG", "Received message:"+message);
					}
				});
		//创建新的消息
		Message newMessage = new Message();
		//设置消息内容
		newMessage.setBody(message);
		try {
			//发送消息
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
