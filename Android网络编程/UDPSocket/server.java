byte[] IMsg = new byte[MAX_UP_DATAGRAM_LEN];
		DatagramPacket dp = new DatagramPacket(IMsg, IMsg.length);
		DatagramSocket ds = null;
		try {
			ds = new DatagramSocket(5002);
			ds.receive(dp);
			String ss = new String(IMsg,"UTF-8");
			System.out.println("Client Message : "+ss);
		} catch (SocketException e) {
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