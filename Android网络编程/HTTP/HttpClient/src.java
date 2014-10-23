//GET
public static InputStream getInputStreamFromUrl(String url){
		//定义输出流变量
		InputStream content = null;
		HttpClient httpClient = new DefaultHttpClient();
		try {
			//连接到服务器
			HttpResponse response = httpClient.execute(new HttpGet(url));
			//获取数据内容
			content = response.getEntity().getContent();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

//POST
public void postData(){
		//创建一个新的HttpClient Post头
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://www.google.com");
		//添加数据
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		nameValuePairs.add(new BasicNameValuePair("id", "12345"));
		nameValuePairs.add(new BasicNameValuePair("stringdata", "myString"));
		//使用utf-8格式对数据进行编码
		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
			HttpResponse response = httpClient.execute(httppost);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}