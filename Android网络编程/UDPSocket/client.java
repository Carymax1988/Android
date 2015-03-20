String udpMsg = "I love you";
		DatagramSocket ds = null;
		try {
			ds = new DatagramSocket();
			InetAddress serverAddr = InetAddress.getByName("192.168.0.178");
			DatagramPacket dp;
			dp = new DatagramPacket(udpMsg.getBytes("UTF-8"), udpMsg.length(),serverAddr,5002);
			ds.send(dp);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ds!=null){
				ds.close();
			}
		}