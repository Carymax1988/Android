package com.asynctask;

import android.widget.ImageView;

public class Test {
	public static void download(String url,ImageView imageView){
		//�½���������
		BitmapDownloaderTask task = new BitmapDownloaderTask(imageView);
		//�������
		task.execute(url);
	}
}
