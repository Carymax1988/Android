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
	//�����ַ���
	private String url;
	
	//ʹ��WeakReference����ڴ�����
	private final WeakReference<ImageView> imageViewReference;
	
	public BitmapDownloaderTask(ImageView imageView) {
		imageViewReference = new WeakReference<ImageView>(imageView);
	}
	
	@Override
	protected Bitmap doInBackground(String... params) {
		//ʵ�ʵ������̵߳��ڲ���ʵ��concurrent�̣߳����Բ�������
		return downloadBitmap(params[0]);
	}
	
	//�������ִ��
	@Override
	protected void onPostExecute(Bitmap bitmap) {
		//�ж��Ƿ�ȡ��
		if(isCancelled()){
			//���ȡ����ֵΪnull
			bitmap = null;
		}
		//WeakReference����Ϊnull
		if(imageViewReference!=null){
			//��ȡͼ��
			ImageView imageView = imageViewReference.get();
			if(imageView!=null){
				//�����������imageviewΪ�ղ����ص�bitmap����
				imageView.setImageBitmap(bitmap);
			}
		}
	}
	
	private Bitmap downloadBitmap(String string) {
		//����ͼƬ
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
