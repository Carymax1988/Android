package com.styleflying.AIDL;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;
public class mAIDLService extends Service{
	private static final String TAG = "AIDLService";
	
	private void Log(String str){
		Log.i(TAG,"----------" + str + "----------");
	}
	
	public void onCreate(){
		Log("service created");
	}
	
	public void onStart(Intent intent, int startId){
		Log("service started id = " + startId);
	}
	
	public IBinder onBind(Intent t){
		Log("service on bind");
		return mBinder;
	}
	
	public void onDestroy(){
		Log("service on destroy");
		super.onDestroy();
	}
	
	public boolean onUnbind(Intent intent){
		Log("service on unbind");
		return super.onUnbind(intent);
	}
	
	public void onRebind(Intent intent){
		Log("service on rebind");
		super.onRebind(intent);
	}
	
	
	private final mInterface.Stub mBinder = new mInterface.Stub() {		
		public void invokTest() throws RemoteException {
			// TODO Auto-generated method stub
			Log.e(TAG, "remote call from client! current thread id = " + Thread.currentThread().getId());
		}
	};
}