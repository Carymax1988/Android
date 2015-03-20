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
		//×¢²áÍøÂç¼àÌı
		IntentFilter filter = new IntentFilter();
		//Ìí¼Ó¹ıÂËÊÂ¼ş
		filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		//×¢²á¼àÌı
		registerReceiver(mNetworkStateReceiver, filter);
	}
	
	protected void onDestroy() {
		unregisterReceiver(mNetworkStateReceiver);
	};
}
