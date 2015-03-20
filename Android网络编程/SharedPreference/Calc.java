package com.sharepreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

//����������ģʽ�±�������
public class Calc extends Activity {
	//���屣��SharedPreferencesʹ�õ�����
	public static final String PREFS_NAME = "MyPrefsFile";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//�洢����
		SharedPreferences setting = getSharedPreferences(PREFS_NAME, 0);
		//�������ñ�����������ֵΪfalse
		boolean silent = setting.getBoolean("silentMode", false);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		//�޸�SharedPreferences�еĴ洢ֵ
		SharedPreferences setting = getSharedPreferences(PREFS_NAME, 0);
		//�½�SharedPreferences�༭��
		SharedPreferences.Editor editor = setting.edit();
		//����Booleanֵ
		editor.putBoolean("silentMode", true);
		//�ύ�޸�
		editor.commit();
	}
}
