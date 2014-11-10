package com.liujiaqi.robot;

import java.util.List;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class MessageListViewAdapter extends BaseAdapter {
	Context context;
	List<MessageData> messageList;
	LayoutInflater inflater;
	public MessageListViewAdapter(Context context,List<MessageData> messageList2) {
		this.context = context;
		this.messageList = messageList2;
		inflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		return messageList.size();
	}
	@Override
	public Object getItem(int position) {
		return messageList.get(position);
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		System.out.println("1111111111");
		MessageData data = messageList.get(position);
		TextView tv = null;
		if(data.getFlag() == MessageData.ROBOTMESSAGE){
			convertView = inflater.inflate(R.layout.robot_layout, null);
		}else if(data.getFlag() == MessageData.USERMESSAGE){
			convertView = inflater.inflate(R.layout.user_layout, null);
		}
		tv = (TextView) convertView.findViewById(R.id.message);
		System.out.println("message = "+data.getMessage());
		if(tv!=null){
			String message = data.getMessage();
			tv.setText(message);
		}
		return convertView;
	}
}
