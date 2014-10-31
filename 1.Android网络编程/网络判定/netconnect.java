package com.connect;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class netconnect {
	
	/**
	 * �ж��û��Ƿ�����
	 */
	public static boolean hasConnect(Context context){
		//��ȡConnectivityManager��ʵ��
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		//��ȡNetworkInfo��ʵ��
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		//�ж��Ƿ�����������
		boolean isConnected = activeNetwork.isConnectedOrConnecting();
		return isConnected;
	}
	
	/**
	 * �ж��Ƿ�Ϊwifi
	 */
	public static boolean isWifi(Context context){
		//��ȡConnectivityManager��ʵ��
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		//��ȡNetworkInfo��ʵ��
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		boolean isWifi = activeNetwork.getType()==ConnectivityManager.TYPE_WIFI;
		return isWifi;
	}
	
}
