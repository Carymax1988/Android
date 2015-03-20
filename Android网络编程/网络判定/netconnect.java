package com.connect;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class netconnect {
	
	/**
	 * 判断用户是否连接
	 */
	public static boolean hasConnect(Context context){
		//获取ConnectivityManager的实例
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		//获取NetworkInfo的实例
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		//判断是否有网络连接
		boolean isConnected = activeNetwork.isConnectedOrConnecting();
		return isConnected;
	}
	
	/**
	 * 判断是否为wifi
	 */
	public static boolean isWifi(Context context){
		//获取ConnectivityManager的实例
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		//获取NetworkInfo的实例
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		boolean isWifi = activeNetwork.getType()==ConnectivityManager.TYPE_WIFI;
		return isWifi;
	}
	
}
