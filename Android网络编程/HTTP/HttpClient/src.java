//GET
public static InputStream getInputStreamFromUrl(String url){
		//�������������
		InputStream content = null;
		HttpClient httpClient = new DefaultHttpClient();
		try {
			//���ӵ�������
			HttpResponse response = httpClient.execute(new HttpGet(url));
			//��ȡ��������
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
		//����һ���µ�HttpClient Postͷ
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://www.google.com");
		//�������
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		nameValuePairs.add(new BasicNameValuePair("id", "12345"));
		nameValuePairs.add(new BasicNameValuePair("stringdata", "myString"));
		//ʹ��utf-8��ʽ�����ݽ��б���
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