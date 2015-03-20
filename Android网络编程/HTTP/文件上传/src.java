public static String post(String actionUrl,String FileName)throws IOException{
		//��������ָ�����
		String BOUNDARY = java.util.UUID.randomUUID().toString();
		String PREFIX = "--",LINEND = "\r\n";
		String MULTIPART_FROM_DATA  ="multipart/form-data";
		String CHARSET  = "UTF-8";
		//����URLʵ��
		URL uri = new URL(actionUrl);
		//����HttpURLConnectionʵ����������
		HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
		//���ô�������ȡ���ݳ�ʱ����λ���룩
		conn.setReadTimeout(5*1000);
		//������������
		conn.setDoInput(true);
		//�����������
		conn.setDoOutput(true);
		//���ò�����ʹ�û���
		conn.setUseCaches(false);
		//����ΪPOST���ͷ���
		conn.setRequestMethod("POST");
		//����ά�ֳ�����
		conn.setRequestProperty("connection", "keep-alive");
		//�����ļ��ַ���ΪUTF-8
		conn.setRequestProperty("Charsert", "UTF-8");
		//�����ļ�����
		conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA+";boundary="+BOUNDARY);
		//����һ���µ������������������д��ָ�����������
		DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
		//�����ļ�����
		if(FileName!=""){
			//����StringBuffer���󣬹��������ַ�������
			StringBuffer sb1 = new StringBuffer();
			sb1.append(PREFIX);
			sb1.append(BOUNDARY);
			sb1.append(LINEND);
			sb1.append("Content-Disposition:form-data;name=\"file1\";filename=\""+FileName+"\""+LINEND);
			sb1.append("Content-Type:application/octet-stream;charset="+CHARSET+LINEND);
			sb1.append(LINEND);
			//д���������
			outStream.write(sb1.toString().getBytes());
			//���ļ�������������
			InputStream is = new FileInputStream(FileName);
			byte[] buffer = new byte[1024];
			int len = 0;
			//д���������
			while((len = is.read(buffer))!=-1){
				outStream.write(buffer,0,len);
			}
			//�ر�������
			is.close();
			//��ӻ��б�־
			outStream.write(LINEND.getBytes());
		}
		//���������־
		byte[] end_data = (PREFIX+BOUNDARY+PREFIX+LINEND).getBytes();
		outStream.write(end_data);
		//ˢ�£���������
		outStream.flush();
		//�õ���Ӧ��
		int res = conn.getResponseCode();
		InputStream in = null;
		//�ϴ��ɹ���᷵����Ӧ��200
		if(res == 200){
			//��ȡ����
			in = conn.getInputStream();
			int ch;
			//����StringBuffer�ַ���
			StringBuffer sb2 = new StringBuffer();
			//��������
			while((ch = in.read())!=-1){
				sb2.append(ch);
			}
		}
		//������ݲ�Ϊ�գ������ַ�����ʽ�������ݣ����򷵻�null;
		return in==null?null:in.toString();
	}