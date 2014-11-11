package com.styleflying.AIDL;


import com.styleflying.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private static final String TAG = "AIDLActivity";
	private Button btnOk;  
    private Button btnCancel;  
    private Button btnCallBack; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnOk = (Button)findViewById(R.id.btnOk);  
        btnCancel = (Button)findViewById(R.id.btnCancel);  
        btnCallBack = (Button)findViewById(R.id.btnCallBack);
        
        btnOk.setOnClickListener(new Button.OnClickListener(){  
            public void onClick(View v){  
                Bundle args = new Bundle();  
                Intent intent = new Intent("com.styleflying.AIDL.service");  
                intent.putExtras(args);  
                bindService(intent,mConnection,Context.BIND_AUTO_CREATE);  
            }  
        });
        
        btnCancel.setOnClickListener(new Button.OnClickListener(){  
            public void onClick(View v){  
                unbindService(mConnection);  
            }  
        });
		
        btnCallBack.setOnClickListener(new Button.OnClickListener(){  
            public void onClick(View v){  
                try{  
                	android.util.Log.i(TAG,"current Thread id = " + Thread.currentThread().getId());  
                    mService.invokTest();  
                }  
                catch(RemoteException e){  
                      
                }  
            }  
        });
	}

	private void Log(String str){  
        android.util.Log.d(TAG,"----------" + str + "----------");
    }
	
	mInterface mService;
	
	private ServiceConnection mConnection = new ServiceConnection(){  
        public void onServiceConnected(ComponentName className,  
                IBinder service){  
            Log("connect service");  
            mService = mInterface.Stub.asInterface(service);  
        }  
          
        public void onServiceDisconnected(ComponentName className){  
            Log("disconnect service");  
            mService = null;  
        }  
    };
}
