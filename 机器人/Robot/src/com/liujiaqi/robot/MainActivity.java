package com.liujiaqi.robot;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity implements HttpResponseListener,OnClickListener{
	public static final String key = "35bbe89a9ddd01b3f62aa6f593c3b7e8";
	public static final String URL = "http://www.tuling123.com/openapi/api?key=%s&info=%s";
	
	private String str = "";
	
	ListView lv = null;
	EditText et = null;
	Button btn = null;
	MessageListViewAdapter adapter = null;
	
	List<MessageData> messageList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}
	
	private void init(){
		messageList = new ArrayList<MessageData>();
		lv = (ListView) findViewById(R.id.list_view);
		et = (EditText) findViewById(R.id.edit_text);
		btn = (Button) findViewById(R.id.send_btn);
		btn.setOnClickListener(this);
		adapter = new MessageListViewAdapter(this, messageList);
		lv.setAdapter(adapter);
		String[] strs = getResources().getStringArray(R.array.initmessage);
		int random = (int) (Math.random()*1000%5);
		String str = strs[random];
		int flag = MessageData.ROBOTMESSAGE;
		MessageData data = new MessageData();
		data.setFlag(flag);
		data.setMessage(str);
		messageList.add(data);
		adapter.notifyDataSetChanged();
	}
	
	@Override
	public void getResult(String result) {
		try {
			JSONObject jsObject = new JSONObject(result);
			str = jsObject.getString("text");
			int flag = MessageData.ROBOTMESSAGE;
			MessageData data = new MessageData();
			data.setFlag(flag);
			data.setMessage(str);
			messageList.add(data);
			adapter.notifyDataSetChanged();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		MessageData md = new MessageData();
		md.setFlag(MessageData.USERMESSAGE);
		md.setMessage(et.getText().toString());
		messageList.add(md);
		adapter.notifyDataSetChanged();
		String messge = et.getText().toString();
		messge.replaceAll(" ", "");
		messge.replaceAll("\n", "");
		HttpConn conn = new HttpConn(String.format(URL,key,messge), this);
		conn.execute();
		et.setText("");
	}
}
