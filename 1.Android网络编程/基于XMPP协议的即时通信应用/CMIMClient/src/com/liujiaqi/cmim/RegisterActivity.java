package com.liujiaqi.cmim;

import com.liujiaqi.connectionmanager.XMPPConn;
import com.liujiaqi.contants.GlobalContantsApplication;
import com.liujiaqi.contants.PublicValue;
import com.liujiaqi.progressdialog.progressDialog;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends BaseActivity {
	
	EditText user = null;
	EditText passwd = null;
	EditText confirmpasswd = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		init();
	}
	XMPPConn conn = null;
	private void init() {			
		conn = new XMPPConn(handler);
		user = (EditText) findViewById(R.id.user);
		passwd = (EditText) findViewById(R.id.passwd);
		confirmpasswd = (EditText) findViewById(R.id.confirmpasswd);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_register, menu);
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.finsh:
			//注册
			if("".equalsIgnoreCase(user.getText().toString().trim())){
				Toast.makeText(GlobalContantsApplication.getInstens().getContext(), "账号不能为空", Toast.LENGTH_SHORT).show();
				return super.onMenuItemSelected(featureId, item);
			}
			if("".equalsIgnoreCase(passwd.getText().toString().trim())){
				Toast.makeText(GlobalContantsApplication.getInstens().getContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
				return super.onMenuItemSelected(featureId, item);
			}
			if(! passwd.getText().toString().equals(confirmpasswd.getText().toString())){
				Toast.makeText(GlobalContantsApplication.getInstens().getContext(), "两次输入的密码不相同", Toast.LENGTH_SHORT).show();
				return super.onMenuItemSelected(featureId, item);
			}
			GlobalContantsApplication.getInstens().setPd(progressDialog.show(this, ""));
			RegisterActivity.this.conn.connectServer(GlobalContantsApplication.getInstens().getServerIP(), GlobalContantsApplication.getInstens().getServerPort());
			break;
		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}
	
	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case PublicValue.ConnectSUCCESS:
				String username = user.getText().toString();
				String password = passwd.getText().toString();
				conn.createNewUser(username, password);
				break;
			case PublicValue.RegisterSUCCESS:
				RegisterActivity.this.finish();
				GlobalContantsApplication.getInstens().getPd().dismiss();
				break;
			case PublicValue.ConnectFAIL:
				Toast.makeText(GlobalContantsApplication.getInstens().getContext(), "连接服务器失败", Toast.LENGTH_SHORT).show();
				GlobalContantsApplication.getInstens().getPd().dismiss();
				break;
			case PublicValue.RegisterFAIL:
				Toast.makeText(GlobalContantsApplication.getInstens().getContext(), "注册失败", Toast.LENGTH_SHORT).show();
				GlobalContantsApplication.getInstens().getPd().dismiss();
				break;
			default:
				GlobalContantsApplication.getInstens().getPd().dismiss();
				break;
			}
		}
	};
	
	protected void onDestroy() {
		super.onDestroy();
	};
}
