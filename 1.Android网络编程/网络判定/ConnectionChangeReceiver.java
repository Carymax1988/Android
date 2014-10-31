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
            <!-- ��������� -->
            <intent-filter >
                <action android:name="android.net.conn.CONNECTIVITY_CHANGE"/>
            </intent-filter>
        </receiver>
	 */
	
	
	//�������ʹ�õı�ǩ����
	//��ȡ����
	private static final String TAG = ConnectionChangeReceiver.class.getSimpleName();
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.e(TAG, "����״̬�ı�");
		boolean success = false;
		//��ȡ�������ӷ���
		ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		//��ȡWi-Fi��������״̬
		State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
		//�ж��Ƿ�����ʹ��Wi-Fi����
		if((State.CONNECTED == state)){
			success = true;
		}
		//��ȡGPRS��������״̬
		state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
		//�ж��Ƿ�����ʹ��GPRS����
		if(State.CONNECTED!=state){
			success = true;
		}
		//û�����ӳɹ�
		if(!success){
			//������ʾ��
			Toast.makeText(context, "δ��������", Toast.LENGTH_LONG).show();
		}
	}
	
}
