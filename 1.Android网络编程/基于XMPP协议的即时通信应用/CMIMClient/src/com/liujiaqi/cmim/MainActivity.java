package com.liujiaqi.cmim;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

import com.liujiaqi.connectionmanager.XMPPConn;
import com.liujiaqi.connectionmanager.XMPPConnManager;
import com.liujiaqi.connectionmanager.XMPPManager;
import com.liujiaqi.connectionmanager.XMPPManager.XMPPClientListener;
import com.liujiaqi.contants.GlobalContantsApplication;
import com.liujiaqi.contants.PublicValue;
import com.liujiaqi.progressdialog.progressDialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements Button.OnClickListener,XMPPClientListener{
	XMPPConn conn = null;
	EditText account = null;
	EditText passwd = null;
	Button login = null;
	Button register = null;
	Button setting = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		GlobalContantsApplication.getInstens().init(this);
		XMPPManager.setXMPPClientListener(this);
		init();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	private void init() {
		conn = new XMPPConn(handler);
		account = (EditText) findViewById(R.id.account);
		passwd = (EditText) findViewById(R.id.passwd);
		login = (Button) findViewById(R.id.login);
		register = (Button) findViewById(R.id.register);
		setting = (Button) findViewById(R.id.setting);
		login.setOnClickListener(this);
		register.setOnClickListener(this);
		setting.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login:
			//登录
			login();
			break;
		case R.id.register:
			//注册
			register();
			break;
		case R.id.setting:
			//设置
			setting();
			break;
		default:
			break;
		}
	}

	private void register() {
		Intent i = new Intent(MainActivity.this,RegisterActivity.class);
		startActivity(i);
	}

	private void setting() {
		Intent i = new Intent(MainActivity.this,SettingActivity.class);
		startActivity(i);
	}

	private void login() {
		if(account!=null){
			if("".equalsIgnoreCase(account.getText().toString().trim())){
				Toast.makeText(GlobalContantsApplication.getInstens().getContext(), "账号不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
		}
		if(passwd!=null){
			if("".equalsIgnoreCase(passwd.getText().toString().trim())){
				Toast.makeText(GlobalContantsApplication.getInstens().getContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
		}
		if(account!=null && passwd!=null){
			GlobalContantsApplication.getInstens().setPd(progressDialog.show(this, ""));
			conn.connectServer(GlobalContantsApplication.getInstens().getServerIP(), GlobalContantsApplication.getInstens().getServerPort());
		}
	}

	//连接服务器处理方法
	Handler handler = new Handler(){
		@Override
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case PublicValue.ConnectFAIL:
				Toast.makeText(GlobalContantsApplication.getInstens().getContext(), "连接服务器失败", Toast.LENGTH_SHORT).show();
				GlobalContantsApplication.getInstens().getPd().dismiss();
				break;
			case PublicValue.ConnectSUCCESS:
				Toast.makeText(GlobalContantsApplication.getInstens().getContext(), "连接服务器成功", Toast.LENGTH_SHORT).show();
				String saccount = account.getText().toString();
				String spasswd = passwd.getText().toString();
				conn.login(saccount, spasswd);
				break;
			case PublicValue.LoginFAIL:
				Toast.makeText(GlobalContantsApplication.getInstens().getContext(), "登录失败", Toast.LENGTH_SHORT).show();
				GlobalContantsApplication.getInstens().getPd().dismiss();
				break;
			case PublicValue.LoginSUCCESS:
				Toast.makeText(GlobalContantsApplication.getInstens().getContext(), "登录成功", Toast.LENGTH_SHORT).show();
				GlobalContantsApplication.getInstens().getPd().dismiss();
				loginsuccess();
				break;
			case PublicValue.LogoutSUCCESS:
				Toast.makeText(GlobalContantsApplication.getInstens().getContext(), "注销成功", Toast.LENGTH_SHORT).show();
				break;
			case PublicValue.ERROR:
				Toast.makeText(GlobalContantsApplication.getInstens().getContext(), "未知错误", Toast.LENGTH_SHORT).show();
				GlobalContantsApplication.getInstens().getPd().dismiss();
				break;
			default:
				GlobalContantsApplication.getInstens().getPd().dismiss();
				break;
			}
		}
	};
	private void loginsuccess() {
		XMPPManager.addListener();
		XMPPManager.addMessageListener();
		Intent i = new Intent(MainActivity.this,MainMenuList.class);
		startActivityForResult(i, PublicValue.MainMenuListActivityID);
	}
	@Override
	public void subscribeMessage(Packet packet) {
		
	}

	@Override
	public void unsubscribeMessage(Packet packet) {
		
	}

	@Override
	public void receiveMessage(Chat chat, Message message) {
		System.out.println("message from = "+message.getFrom());
		System.out.println("message body = "+message.getBody());
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case PublicValue.MainMenuListActivityID:
			new XMPPConn(handler).logout();
			break;
		default:
			break;
		}
	}
}
