try {
			Socket socket = new Socket("172.26.137.12", 5000);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			String outMsg = "I love you"+System.getProperty("line.separator");
			out.write(outMsg);
			out.flush();
			String inMsg = in.readLine()+System.getProperty("line.separator");
			System.out.println("Client message = "+inMsg);
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}