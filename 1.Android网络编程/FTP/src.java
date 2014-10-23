//步骤1.在项目引入包commons-net-3.3.jar，导入需要其中的FTPClient类
		//步骤2.初始化FTPClient
		FTPClient ftpClient = new FTPClient();
		try {
			//步骤3.设置登录地址和端口号
			ftpClient.connect(InetAddress.getByName("124.205.94.218"));
			//步骤4.设置登录用户名和密码
			ftpClient.login("ftpuser", "Miracles1999");
			//步骤5.设置文件类型和采用被动传输方式
			//检测返回的字符串里面是否包含250
			//250响应代码表示“行为完成”
			System.out.println("ssss============="+ftpClient.getReplyString());
			if(ftpClient.getReplyString().contains("250")){
				//设置文件类型
				ftpClient.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);
				//定义一个输入缓冲区
				BufferedInputStream buffin = null;
				buffin = new BufferedInputStream(new FileInputStream(new File("/assets/模拟器尺寸.txt")));
				//设置客户端PASV模式，端口20
				ftpClient.enterLocalPassiveMode();
				//步骤6.传输文件
				//存储文件，返回是否成功
				boolean result = ftpClient.storeFile("模拟器尺寸.txt", buffin);
				System.out.println("result =============== "+result);
				//步骤7.关闭连接
				//关闭缓冲区
				buffin.close();
				//登出
				ftpClient.logout();
				//断开连接
				ftpClient.disconnect();
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}