package com.liujiaqi.juheshuju_duanxin;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.thinkland.sdk.sms.SMSCaptcha;
import com.thinkland.sdk.util.BaseData;
import com.thinkland.sdk.util.CommonFun;


public class MainActivity extends ActionBarActivity implements BaseData.ResultCallBack {
    EditText phonenumber = null;
    EditText codeedit = null;
    Button getcodebtn = null;
    Button submitbtn = null;
    Button getfrindbtn = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        /**
         * 初始化方法
         * @param context
         * @needFriends 是否需要好友功能
         */
        CommonFun.initialize(getApplicationContext(), true);
        setContentView(R.layout.activity_main);
        phonenumber = (EditText) findViewById(R.id.phonenumber);
        codeedit = (EditText) findViewById(R.id.codeedit);
        getcodebtn = (Button) findViewById(R.id.getcodebtn);
        submitbtn = (Button) findViewById(R.id.submitbtn);
        getfrindbtn = (Button) findViewById(R.id.getfrindbtn);
        final SMSCaptcha smsCaptcha= SMSCaptcha.getInstance();

        getcodebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s_phone = phonenumber.getText().toString();
                //发送验证码
                smsCaptcha.sendCaptcha(s_phone,MainActivity.this);
            }
        });

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s_phone = phonenumber.getText().toString();
                String s_code = codeedit.getText().toString();
                //提交验证码
                smsCaptcha.commitCaptcha(s_phone,s_code,MainActivity.this);
            }
        });

        getfrindbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取已注册的好友
                smsCaptcha.getContentFriends(MainActivity.this);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResult(int  code, String  reason, String  result) {
        System.out.println("code="+code);
        System.out.println("reason="+reason);
        System.out.println("result="+result);
        Toast.makeText(this,"code="+code+"\n"+"reason="+reason+"\n"+"result="+result+"\n",Toast.LENGTH_SHORT).show();
    }
}
