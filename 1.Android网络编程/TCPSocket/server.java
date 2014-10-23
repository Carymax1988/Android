ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(TCP_SERVER_PORT);
			Socket socket = serverSocket.accept();
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			String incomingMsg = in.readLine()+System.getProperty("line.separator");
			System.out.println("incomeMsg : "+incomingMsg);
			String outgoingMsg = "goodbye from port"+TCP_SERVER_PORT+System.getProperty("line.separator");
			out.write(outgoingMsg);
			out.flush();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(serverSocket!=null){
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}