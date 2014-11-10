package com.liujiaqi.catchpoint;

import com.liujiaqi.catchpoint.MainPanel.restartListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements restartListener{
	SharedPreferences sp;
	TextView tv = null;
	MainPanel mp = null;
	Button restartBtn = null;
	Button resetBtn = null;
	Editor edit = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.text);
		mp = (MainPanel) findViewById(R.id.panel);
		restartBtn = (Button) findViewById(R.id.restartBtn);
		resetBtn = (Button) findViewById(R.id.resetBtn);
		mp.setRestartListener(this);
		sp = getSharedPreferences(MainPanel.DATA, Context.MODE_PRIVATE);
		edit = sp.edit();
		int bestScore = sp.getInt(MainPanel.BESTSCORE, 99);
		tv.setText("你的最好成绩是： "+bestScore+" 步");
		tv.setTextSize(18);
		restartBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mp!=null){
					mp.initGame();
					mp.redraw();
				}
			}
		});
		resetBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				edit.putInt(MainPanel.BESTSCORE, 99);
				edit.commit();
				tv.setText("你的最好成绩是： "+99+" 步");
			}
		});
	}

	@Override
	public void OnReStart(boolean isRefresh) {
		if(isRefresh){
			if(sp!=null){
				int newScore = sp.getInt(MainPanel.BESTSCORE, 99);
				Dialog d = new AlertDialog
						.Builder(this)
						.setTitle("你赢了")
						.setMessage("恭喜，你打破记录了，我们的新记录是："+newScore)
						.setIcon(R.drawable.ic_launcher)
						.setPositiveButton("确定", null)
						.create();
				d.setCancelable(false);
				d.show();
				tv.setText("你的最好成绩是： "+newScore+" 步");
			}
		}
	}
}
