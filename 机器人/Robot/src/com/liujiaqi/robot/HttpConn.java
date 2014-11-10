package com.liujiaqi.robot;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class HttpConn extends AsyncTask<String,Void,String> {

	private HttpClient client;
	private HttpGet get;
	private HttpEntity entity;
	private HttpResponse response;
	private InputStream in;
	
	private String url;
	
	private HttpResponseListener listener;
	
	public HttpConn(String url,HttpResponseListener listener) {
		this.url = url;
		this.listener = listener;
	}
	
	@Override
	protected String doInBackground(String... params) {
		try{
			client = new DefaultHttpClient();
			get = new HttpGet(url);
			response = client.execute(get);
			entity = response.getEntity();
			in = entity.getContent();
			String str = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			StringBuffer sb = new StringBuffer();
			while((str=br.readLine()) != null){
				sb.append(str);
			}
			return sb.toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	@Override
	protected void onPostExecute(String result) {
		listener.getResult(result);
		super.onPostExecute(result);
	}
}
