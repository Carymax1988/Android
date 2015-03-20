public class PopupAuthentication extends Authenticator {
		public PasswordAuthentication getPasswordAuthentication(){
			String username = "jiaqi@miracles.cn";
			String pwd = "liujiaqi010";
			return new PasswordAuthentication(username, pwd);
		}
	}
	
	public void sendjavamail(){
		System.out.println("sendjavamail ......");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.miracles.cn");
		props.put("mail.smtp.auth", "true");
		PopupAuthentication auth = new PopupAuthentication();
		Session session = Session.getInstance(props, auth);
		MimeMessage message = new MimeMessage(session);
		try {
			
			Address addressFrom = new InternetAddress("jiaqi@miracles.cn", "FROM");
			Address addressTo = new InternetAddress("84810493@qq.com", "TO");
			Address addressCopy = new InternetAddress("1002503559@qq.com", "max");
			
			message.setContent("Hello","text/plain");
			message.setText("hello");
			message.setSubject("Title");
			message.setFrom(addressFrom);
			message.addRecipient(Message.RecipientType.TO, addressTo);
			message.addRecipient(Message.RecipientType.CC, addressCopy);
			message.saveChanges();
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.miracles.cn","jiaqi@miracles.cn","liujiaqi010");
			transport.send(message);
			transport.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		System.out.println("send success ......");
	}