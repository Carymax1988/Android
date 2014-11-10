package com.liujiaqi.cmim;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

import com.liujiaqi.connectionmanager.XMPPConn;
import com.liujiaqi.connectionmanager.XMPPManager;
import com.liujiaqi.connectionmanager.XMPPManager.XMPPClientListener;
import com.liujiaqi.contants.PublicValue;

import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChatActivity extends BaseActivity implements XMPPClientListener,OnClickListener{
	TextView tv = null;
	EditText et = null;
	Button sendbtn = null;
	String user = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		user = getIntent().getStringExtra("user");
		XMPPManager.setXMPPClientListener(this);
		init();
	}
	Handler getMessagehandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			String message = msg.getData().getString("message");
			tv.setText(message);
		};
	};
	private void init() {
		tv = (TextView) findViewById(R.id.chatView);
		tv.setMovementMethod(ScrollingMovementMethod.getInstance());
		et = (EditText) findViewById(R.id.inputText);
		sendbtn = (Button) findViewById(R.id.sendbtn);
		sendbtn.setOnClickListener(this);
	}
	protected void onDestroy() {
		super.onDestroy();
	}
	@Override
	public void subscribeMessage(Packet packet) {
		
	}
	@Override
	public void unsubscribeMessage(Packet packet) {
		
	}
	@Override
	public void receiveMessage(Chat chat, Message message) {
		String oldMessage = tv.getText().toString();
		String from = message.getFrom();
		if(from.indexOf("/Spark")>=0){
			String[] s = from.split("/Spark");
			from = s[0];
		}else if(from.indexOf("/Smack")>=0){
			String[] s = from.split("/Smack");
			from = s[0];
		}
		String newMessage = oldMessage+"\n"+from+":\n"+message.getBody();
		System.out.println("user === "+user);
		System.out.println("from === "+from);
		if(user.equals(from)){
			android.os.Message m = new android.os.Message();
			Bundle b = new Bundle();
			b.putString("message", newMessage);
			m.setData(b);
			getMessagehandler.sendMessage(m);
		}
	}
	@Override
	public void onClick(View v) {
		String message = et.getText().toString();
		String oldMessage = tv.getText().toString();
		String newMessage = oldMessage+"\n"+"我：\n"+message;
		tv.setText(newMessage);
		et.setText("");
		new XMPPConn(handler).sendMessage(user, message);
	};
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case PublicValue.sendMessageSUCCESS:
				Toast.makeText(ChatActivity.this, "消息发送成功", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
		};
	};
}
