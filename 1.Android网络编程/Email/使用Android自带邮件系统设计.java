private void sendmail(){
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_SEND);
		intent.putExtra(Intent.EXTRA_EMAIL, 
		new String[]{"liujiaqi19881124@gmail.com","84810493@qq.com"});
		intent.putExtra(Intent.EXTRA_SUBJECT, "test");
		intent.putExtra(Intent.EXTRA_TEXT, "test mail");
		startActivity(Intent.createChooser(intent, "Sending mail..."));
	}