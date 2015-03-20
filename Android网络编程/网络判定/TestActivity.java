package com.connect;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

public class TestActivity extends Activity {
	BroadcastReceiver mNetworkStateReceiver = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ע���������
		IntentFilter filter = new IntentFilter();
		//��ӹ����¼�
		filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		//ע�����
		registerReceiver(mNetworkStateReceiver, filter);
	}
	
	protected void onDestroy() {
		unregisterReceiver(mNetworkStateReceiver);
	};
}
