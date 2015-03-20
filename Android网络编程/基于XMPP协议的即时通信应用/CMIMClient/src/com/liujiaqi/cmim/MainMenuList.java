package com.liujiaqi.cmim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.packet.Packet;

import com.liujiaqi.adapter.MainMenuListAdapter;
import com.liujiaqi.bean.UserBean;
import com.liujiaqi.connectionmanager.XMPPConn;
import com.liujiaqi.connectionmanager.XMPPManager;
import com.liujiaqi.connectionmanager.XMPPManager.XMPPClientListener;
import com.liujiaqi.contants.GlobalContantsApplication;
import com.liujiaqi.contants.PublicValue;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;

public class MainMenuList extends BaseActivity implements XMPPClientListener,OnChildClickListener{
	private ArrayList<ArrayList<UserBean>> UsersList = null;
	private HashMap<String, String> map = null;
	ExpandableListView listview = null;
	MainMenuListAdapter adapter = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mainmenulist);
		XMPPManager.setXMPPClientListener(this);
		init();
	}
	
	protected void onDestroy() {
		super.onDestroy();
	};
	
	private void init() {
		listview = (ExpandableListView) findViewById(R.id.expandableListView1);
		listview.setOnChildClickListener(this);
		new XMPPConn(handler).getContactsList();
	}

	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case PublicValue.ContactsListSUCCESS:
				Toast.makeText(GlobalContantsApplication.getInstens().getContext(), "获取联系人列表成功", Toast.LENGTH_SHORT).show();
				showlist();
				break;
			case PublicValue.getSomeOneStatusSUCCESS:
				Bundle b = msg.getData();
				String status = b.getString("status");
				String user = b.getString("user");
				updateSomeOneStatus(user,status);
				if(adapter!=null){
					adapter.notifyDataSetChanged();
				}
				break;
			default:
				break;
			}
		}

	};
	private void updateSomeOneStatus(String user,String status) {
		for(ArrayList<UserBean> users : UsersList){
			for(UserBean useritem : users){
				if(useritem.getUser().equals(user)){
					useritem.setStatus(status);
					return;
				}
			}
		}
	}
	private void showlist() {
		UsersList = GlobalContantsApplication.getInstens().getContactsList();
		map = GlobalContantsApplication.getInstens().getMap();
		if(UsersList!=null){
			System.out.println("size ==== "+UsersList.size());
		}
		adapter = new MainMenuListAdapter(this, UsersList, map);
		listview.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	@Override
	public void subscribeMessage(Packet packet) {
		
	}

	@Override
	public void unsubscribeMessage(Packet packet) {
		
	}

	@Override
	public void receiveMessage(Chat chat,
			org.jivesoftware.smack.packet.Message message) {
		System.out.println("message from = "+message.getFrom());
		System.out.println("message subject = "+message.getSubject());
		System.out.println("message body = "+message.getBody());
		String user = message.getFrom();
		if(user.indexOf("/Spark")>=0){
			String[] s = user.split("/Spark");
			user = s[0];
		}else if(user.indexOf("/Smack")>=0){
			String[] s = user.split("/Smack");
			user = s[0];
		}
		System.out.println("user= ====== "+user);
		new XMPPConn(handler).getSomeOneStatus(user);
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		String user = UsersList.get(groupPosition).get(childPosition).getUser();
		Intent i = new Intent(MainMenuList.this,ChatActivity.class);
		i.putExtra("user", user);
		startActivity(i);
		return false;
	}
}
