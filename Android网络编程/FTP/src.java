//����1.����Ŀ�����commons-net-3.3.jar��������Ҫ���е�FTPClient��
		//����2.��ʼ��FTPClient
		FTPClient ftpClient = new FTPClient();
		try {
			//����3.���õ�¼��ַ�Ͷ˿ں�
			ftpClient.connect(InetAddress.getByName("124.205.94.218"));
			//����4.���õ�¼�û���������
			ftpClient.login("ftpuser", "Miracles1999");
			//����5.�����ļ����ͺͲ��ñ������䷽ʽ
			//��ⷵ�ص��ַ��������Ƿ����250
			//250��Ӧ�����ʾ����Ϊ��ɡ�
			System.out.println("ssss============="+ftpClient.getReplyString());
			if(ftpClient.getReplyString().contains("250")){
				//�����ļ�����
				ftpClient.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);
				//����һ�����뻺����
				BufferedInputStream buffin = null;
				buffin = new BufferedInputStream(new FileInputStream(new File("/assets/ģ�����ߴ�.txt")));
				//���ÿͻ���PASVģʽ���˿�20
				ftpClient.enterLocalPassiveMode();
				//����6.�����ļ�
				//�洢�ļ��������Ƿ�ɹ�
				boolean result = ftpClient.storeFile("ģ�����ߴ�.txt", buffin);
				System.out.println("result =============== "+result);
				//����7.�ر�����
				//�رջ�����
				buffin.close();
				//�ǳ�
				ftpClient.logout();
				//�Ͽ�����
				ftpClient.disconnect();
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}