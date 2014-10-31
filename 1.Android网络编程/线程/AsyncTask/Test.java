package com.asynctask;

import android.widget.ImageView;

public class Test {
	public static void download(String url,ImageView imageView){
		//新建下载任务
		BitmapDownloaderTask task = new BitmapDownloaderTask(imageView);
		//传入参数
		task.execute(url);
	}
}
