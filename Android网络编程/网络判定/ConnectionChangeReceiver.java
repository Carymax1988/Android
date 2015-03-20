package com.connect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.util.Log;
import android.widget.Toast;

public class ConnectionChangeReceiver extends BroadcastReceiver {

	/**
	 * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
	 * 
	 * <receiver 
            android:name="com.connect.ConnectionChangeReceiver"
            android:label="NetworkConnection">
            <!-- 定义过滤器 -->
            <intent-filter >
                <action android:name="android.net.conn.CONNECTIVITY_CHANGE"/>
            </intent-filter>
        </receiver>
	 */
	
	
	//定义调试使用的标签变量
	//获取类名
	private static final String TAG = ConnectionChangeReceiver.class.getSimpleName();
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.e(TAG, "网络状态改变");
		boolean success = false;
		//获取网络连接服务
		ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		//获取Wi-Fi网络连接状态
		State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
		//判断是否正在使用Wi-Fi网络
		if((State.CONNECTED == state)){
			success = true;
		}
		//获取GPRS网络连接状态
		state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
		//判断是否正在使用GPRS网络
		if(State.CONNECTED!=state){
			success = true;
		}
		//没有连接成功
		if(!success){
			//弹出提示框
			Toast.makeText(context, "未连接网络", Toast.LENGTH_LONG).show();
		}
	}
	
}
