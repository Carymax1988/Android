public static String post(String actionUrl,String FileName)throws IOException{
		//产生随机分隔内容
		String BOUNDARY = java.util.UUID.randomUUID().toString();
		String PREFIX = "--",LINEND = "\r\n";
		String MULTIPART_FROM_DATA  ="multipart/form-data";
		String CHARSET  = "UTF-8";
		//定义URL实例
		URL uri = new URL(actionUrl);
		//定义HttpURLConnection实例，打开连接
		HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
		//设置从主机读取数据超时（单位毫秒）
		conn.setReadTimeout(5*1000);
		//设置允许输入
		conn.setDoInput(true);
		//设置允许输出
		conn.setDoOutput(true);
		//设置不允许使用缓存
		conn.setUseCaches(false);
		//设置为POST发送方法
		conn.setRequestMethod("POST");
		//设置维持长连接
		conn.setRequestProperty("connection", "keep-alive");
		//设置文件字符集为UTF-8
		conn.setRequestProperty("Charsert", "UTF-8");
		//设置文件类型
		conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA+";boundary="+BOUNDARY);
		//创建一个新的数据输出流，将数据写入指定基础输出流
		DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
		//发送文件数据
		if(FileName!=""){
			//定义StringBuffer对象，构建发送字符串数据
			StringBuffer sb1 = new StringBuffer();
			sb1.append(PREFIX);
			sb1.append(BOUNDARY);
			sb1.append(LINEND);
			sb1.append("Content-Disposition:form-data;name=\"file1\";filename=\""+FileName+"\""+LINEND);
			sb1.append("Content-Type:application/octet-stream;charset="+CHARSET+LINEND);
			sb1.append(LINEND);
			//写入输出流中
			outStream.write(sb1.toString().getBytes());
			//将文件读到输入流中
			InputStream is = new FileInputStream(FileName);
			byte[] buffer = new byte[1024];
			int len = 0;
			//写入输出流中
			while((len = is.read(buffer))!=-1){
				outStream.write(buffer,0,len);
			}
			//关闭输入流
			is.close();
			//添加换行标志
			outStream.write(LINEND.getBytes());
		}
		//请求结束标志
		byte[] end_data = (PREFIX+BOUNDARY+PREFIX+LINEND).getBytes();
		outStream.write(end_data);
		//刷新，发送数据
		outStream.flush();
		//得到响应码
		int res = conn.getResponseCode();
		InputStream in = null;
		//上传成功则会返回响应码200
		if(res == 200){
			//读取数据
			in = conn.getInputStream();
			int ch;
			//定义StringBuffer字符串
			StringBuffer sb2 = new StringBuffer();
			//保存数据
			while((ch = in.read())!=-1){
				sb2.append(ch);
			}
		}
		//如果数据不为空，则以字符串方式返回数据；否则返回null;
		return in==null?null:in.toString();
	}