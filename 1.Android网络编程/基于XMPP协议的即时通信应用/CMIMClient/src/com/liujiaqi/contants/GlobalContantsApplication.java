package com.liujiaqi.contants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.liujiaqi.bean.UserBean;
import com.liujiaqi.progressdialog.progressDialog;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class GlobalContantsApplication extends Application{
	private static GlobalContantsApplication manager = null;
	private String serverIP;
	private int serverPort;
	private String serverDomain;
	private SharedPreferences sp;
	private Context context;
	private progressDialog pd;
	
	private ArrayList<ArrayList<UserBean>> ContactsList = null;
	//列表和组名对应
	private HashMap<String, String> map = null;
	
	
	public HashMap<String, String> getMap() {
		return map;
	}

	public void setMap(HashMap<String, String> map) {
		this.map = map;
	}

	public ArrayList<ArrayList<UserBean>> getContactsList() {
		return ContactsList;
	}

	public void setContactsList(ArrayList<ArrayList<UserBean>> contactsList) {
		ContactsList = contactsList;
	}

	public Context getContext() {
		return context;
	}
	
	public void setContext(Context context) {
		this.context = context;
	}
	
	public progressDialog getPd() {
		return pd;
	}

	public void setPd(progressDialog pd) {
		this.pd = pd;
	}

	public static GlobalContantsApplication getInstens(){
		if(manager == null){
			manager = new GlobalContantsApplication();
		}
		return manager;
	}
	
	public void init(Context context){
		this.context = context;
		ContactsList = new ArrayList<ArrayList<UserBean>>();
		map = new HashMap<String, String>();
		sp = context.getSharedPreferences(PublicValue.DATAPREFERENCES,Context.MODE_PRIVATE);
		serverIP = sp.getString(PublicValue.ServerIPKey, PublicValue.defaultServerIP);
		serverPort = sp.getInt(PublicValue.ServerPortKey, PublicValue.defaultServerPort);
		serverDomain = sp.getString(PublicValue.ServerDomainKey, PublicValue.defaultServerDomain);
	}
	
	public void save(Context context){
		if(sp == null){
			sp = context.getSharedPreferences(PublicValue.DATAPREFERENCES,Context.MODE_PRIVATE);
		}
		Editor editor = sp.edit();
		editor.putString(PublicValue.ServerIPKey, serverIP);
		editor.putInt(PublicValue.ServerPortKey, serverPort);
		editor.putString(PublicValue.ServerDomainKey, serverDomain);
		editor.commit();
	}
	
	public String getServerIP() {
		return serverIP;
	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public String getServerDomain() {
		return serverDomain;
	}

	public void setServerDomain(String serverDomain) {
		this.serverDomain = serverDomain;
	}
}
