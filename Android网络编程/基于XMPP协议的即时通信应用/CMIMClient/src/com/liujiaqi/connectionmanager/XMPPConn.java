package com.liujiaqi.connectionmanager;

import com.liujiaqi.contants.PublicValue;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class XMPPConn implements Runnable {
	XMPPType.Type ConnType = null;
	String ip;
	int port;
	String sid;
	String pwd;
	String status;
	String user;
	String raw;
	String message;
	private Handler handler;
	
	public XMPPConn() {
		this(new Handler());
	}
	public XMPPConn(Handler handler) {
		this.handler = handler;
	}
	public XMPPType.Type getConnType() {
		return ConnType;
	}

	public void setConnType(XMPPType.Type connType) {
		ConnType = connType;
	}

	public void connectServer(String ip,int port){
		this.ip = ip;
		this.port = port;
		ConnType = XMPPType.Type.ConnectServer;
		XMPPConnManager.getInstance().push(this);
	} 
	public void createNewUser(String sid,String pwd){
		this.sid = sid;
		this.pwd = pwd;
		ConnType = XMPPType.Type.Register;
		XMPPConnManager.getInstance().push(this);
	}
	public void login(String sid,String pwd){
		this.sid = sid;
		this.pwd = pwd;
		ConnType = XMPPType.Type.Login;
		XMPPConnManager.getInstance().push(this);
	}
	public void logout(){
		ConnType = XMPPType.Type.Logout;
		XMPPConnManager.getInstance().push(this);
	}
	public void dismisslogin(){
		ConnType = XMPPType.Type.Disappear;
		XMPPConnManager.getInstance().push(this);
	}
	public void getContactsList(){
		ConnType = XMPPType.Type.ContactsList;
		XMPPConnManager.getInstance().push(this);
	}
	public void getContactsStatues(){
		ConnType = XMPPType.Type.ContactsStatues;
		XMPPConnManager.getInstance().push(this);
	}
	public void setStatus(String s){
		this.status = s;
		XMPPManager.setStatus(s);
		ConnType = XMPPType.Type.Status;
		XMPPConnManager.getInstance().push(this);
	}
	public void addContacts(String user,String raw){
		this.user = user;
		this.raw = raw;
		ConnType = XMPPType.Type.addContacts;
		XMPPConnManager.getInstance().push(this);
	}
	public void deleteContacts(String user){
		this.user = user;
		ConnType = XMPPType.Type.deleteContacts;
		XMPPConnManager.getInstance().push(this);
	}
	public void addListener(){
		ConnType = XMPPType.Type.addListener;
		XMPPConnManager.getInstance().push(this);
	}
	public void addMessageListener(){
		ConnType = XMPPType.Type.addMessageListener;
		XMPPConnManager.getInstance().push(this);
	}
	public void sendMessage(String user,String message){
		this.user = user;
		this.message = message;
		ConnType = XMPPType.Type.sendMessage;
		XMPPConnManager.getInstance().push(this);
	}
	
	public void getSomeOneStatus(String user){
		this.user = user;
		ConnType = XMPPType.Type.SomeOneStatus;
		XMPPConnManager.getInstance().push(this);
	}
	@Override
	public void run() {
		switch (ConnType) {
		case ConnectServer:
			if(XMPPManager.connectServer(ip, port)){
				handler.sendMessage(Message.obtain(handler, PublicValue.ConnectSUCCESS));
			}else{
				handler.sendMessage(Message.obtain(handler, PublicValue.ConnectFAIL));
			}
			break;
		case Register:
			if(XMPPManager.createNewUser(sid, pwd)){
				handler.sendMessage(Message.obtain(handler, PublicValue.RegisterSUCCESS));
			}else{
				handler.sendMessage(Message.obtain(handler, PublicValue.RegisterFAIL));
			}
			break;
		case Login:
			if(XMPPManager.login(sid, pwd)){
				handler.sendMessage(Message.obtain(handler, PublicValue.LoginSUCCESS));
			}else{
				handler.sendMessage(Message.obtain(handler, PublicValue.LoginFAIL));
			}
			break;
		case Logout:
			XMPPManager.logout();
			handler.sendMessage(Message.obtain(handler, PublicValue.LogoutSUCCESS));
			break;
		case Disappear:
			if(XMPPManager.dismisslogin()){
				handler.sendMessage(Message.obtain(handler, PublicValue.DisappearSUCCESS));
			}else{
				handler.sendMessage(Message.obtain(handler, PublicValue.DisappearFAIL));
			}
			break;
		case ContactsList:
			XMPPManager.getContactsList();
			handler.sendMessage(Message.obtain(handler, PublicValue.ContactsListSUCCESS));
			break;
		case ContactsStatues:
			XMPPManager.getContactsStatues();
			handler.sendMessage(Message.obtain(handler, PublicValue.ContactsStatuesSUCCESS));
			break;
		case Status:
			XMPPManager.setStatus(status);
			handler.sendMessage(Message.obtain(handler, PublicValue.StatusSUCCESS));
			break;
		case addContacts:
			XMPPManager.addContacts(user, raw);
			handler.sendMessage(Message.obtain(handler, PublicValue.addContactsSUCCESS));
			break;
		case deleteContacts:
			XMPPManager.deleteContacts(user);
			handler.sendMessage(Message.obtain(handler, PublicValue.deleteContactsSUCCESS));
			break;
		case addListener:
			XMPPManager.addListener();
			handler.sendMessage(Message.obtain(handler, PublicValue.addListenerSUCCESS));
			break;
		case addMessageListener:
			XMPPManager.addMessageListener();
			handler.sendMessage(Message.obtain(handler, PublicValue.addMessageListenerSUCCESS));
			break;
		case sendMessage:
			XMPPManager.sendMessage(user, message);
			handler.sendMessage(Message.obtain(handler, PublicValue.sendMessageSUCCESS));
			break;
		case SomeOneStatus:
			String status = XMPPManager.getSomeOneStatus(user);
			Message message = Message.obtain(handler, PublicValue.getSomeOneStatusSUCCESS);
			Bundle b = new Bundle();
			b.putString("user", user);
			b.putString("status", status);
			message.setData(b);
			handler.sendMessage(message);
			break;
		default:
			break;
		}
		XMPPConnManager.getInstance().didComplete(this);
	}
}
