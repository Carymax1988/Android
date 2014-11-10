package com.liujiaqi.cmim;

import com.liujiaqi.contants.GlobalContantsApplication;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class SettingActivity extends BaseActivity {
	EditText ip = null;
	EditText port = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		init();
		/*显示App icon左侧的back键*/
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		String sip = ip.getText().toString();
		int iport = Integer.parseInt(port.getText().toString());
		GlobalContantsApplication.getInstens().setServerIP(sip);
		GlobalContantsApplication.getInstens().setServerPort(iport);
		GlobalContantsApplication.getInstens().save(this);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			if(canfinish()){
				this.finish();
			}else{
				Toast.makeText(GlobalContantsApplication.getInstens().getContext(), "ip地址或端口不能为空", Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private boolean canfinish() {
		if(ip.getText().length()!=0 && port.getText().length()!=0){
			return true;
		}
		return false;
	}

	private void init() {
		ip = (EditText) findViewById(R.id.ip);
		port = (EditText) findViewById(R.id.port);
		ip.setText(GlobalContantsApplication.getInstens().getServerIP());
		port.setText(String.valueOf(GlobalContantsApplication.getInstens().getServerPort()));
	}
	
}
