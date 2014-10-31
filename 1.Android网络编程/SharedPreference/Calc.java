package com.sharepreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

//计算器静音模式下保存配置
public class Calc extends Activity {
	//定义保存SharedPreferences使用的名称
	public static final String PREFS_NAME = "MyPrefsFile";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//存储设置
		SharedPreferences setting = getSharedPreferences(PREFS_NAME, 0);
		//加入设置变量，设置其值为false
		boolean silent = setting.getBoolean("silentMode", false);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		//修改SharedPreferences中的存储值
		SharedPreferences setting = getSharedPreferences(PREFS_NAME, 0);
		//新建SharedPreferences编辑器
		SharedPreferences.Editor editor = setting.edit();
		//设置Boolean值
		editor.putBoolean("silentMode", true);
		//提交修改
		editor.commit();
	}
}
