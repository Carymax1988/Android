package com.liujiaqi.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import com.liujiaqi.bean.UserBean;
import com.liujiaqi.cmim.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainMenuListAdapter extends BaseExpandableListAdapter {
	Context context;
	ArrayList<ArrayList<UserBean>> UsersList = null;
	HashMap<String, String> map = null;
	LayoutInflater inflater = null;
	LayoutInflater childinflater = null;
	Holder holder = null;
	ChildHolder childhold = null;
	public MainMenuListAdapter(Context context,ArrayList<ArrayList<UserBean>> UsersList,HashMap<String, String> map) {
		this.context = context;
		this.UsersList = UsersList;
		this.map = map;
		inflater = LayoutInflater.from(context);
		childinflater = LayoutInflater.from(context);
	}

	@Override
	public int getGroupCount() {
		return UsersList.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return UsersList.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return UsersList.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return UsersList.get(groupPosition).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = inflater.inflate(R.layout.mainmenulist_group_item, null);
			holder = new Holder();
			holder.tv = (TextView) convertView.findViewById(R.id.textview1);
			convertView.setTag(holder);
		}else{
			holder = (Holder) convertView.getTag();
		}
		String position = String.valueOf(groupPosition);
		String groupName = map.get(position);
		holder.tv.setText(groupName);
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = childinflater.inflate(R.layout.mainmenulist_group_childitem, null);
			childhold = new ChildHolder();
			childhold.tv = (TextView) convertView.findViewById(R.id.textview1);
			childhold.imageView = (ImageView) convertView.findViewById(R.id.imageview1);
			convertView.setTag(childhold);
		}else{
			childhold = (ChildHolder) convertView.getTag();
		}
		UserBean user = UsersList.get(groupPosition).get(childPosition);
		childhold.tv.setText(user.getName());
		
		String status = user.getStatus();
		System.out.println("s ========= "+status);
		if(status == null){
			childhold.imageView.setBackgroundResource(R.drawable.im_unavailable);
		}else{
			if("空闲".equalsIgnoreCase(status)){
				childhold.imageView.setBackgroundResource(R.drawable.im_free_chat);
			}else if("在线".equalsIgnoreCase(status)){
				childhold.imageView.setBackgroundResource(R.drawable.im_available);
			}else if("离开".equalsIgnoreCase(status)){
				childhold.imageView.setBackgroundResource(R.drawable.im_away);
			}else if("正忙".equalsIgnoreCase(status)){
				childhold.imageView.setBackgroundResource(R.drawable.im_dnd);
			}
		}
		
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	private class Holder{
		public TextView tv;
	}
	private class ChildHolder{
		public TextView tv;
		public ImageView imageView;
	}
}
