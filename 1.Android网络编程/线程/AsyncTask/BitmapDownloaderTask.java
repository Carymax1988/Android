package com.asynctask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

public class BitmapDownloaderTask extends AsyncTask<String,Void,Bitmap> {
	//定义字符串
	private String url;
	
	//使用WeakReference解决内存问题
	private final WeakReference<ImageView> imageViewReference;
	
	public BitmapDownloaderTask(ImageView imageView) {
		imageViewReference = new WeakReference<ImageView>(imageView);
	}
	
	@Override
	protected Bitmap doInBackground(String... params) {
		//实际的下载线程的内部其实是concurrent线程，所以不会阻塞
		return downloadBitmap(params[0]);
	}
	
	//下载完后执行
	@Override
	protected void onPostExecute(Bitmap bitmap) {
		//判断是否取消
		if(isCancelled()){
			//如果取消则赋值为null
			bitmap = null;
		}
		//WeakReference对象不为null
		if(imageViewReference!=null){
			//获取图像
			ImageView imageView = imageViewReference.get();
			if(imageView!=null){
				//下载完成设置imageview为刚才下载的bitmap对象
				imageView.setImageBitmap(bitmap);
			}
		}
	}
	
	private Bitmap downloadBitmap(String string) {
		//下载图片
		try {
			URL url = new URL(string);
			URLConnection connection = url.openConnection();
			InputStream stream = connection.getInputStream();
			Bitmap bitmap = BitmapFormatTools.getInstance().InputStream2Bitmap(stream);
			return bitmap;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
