try
{
	File fileHandler = new File(fileSavePath);
	if (fileHandler.exists())
	{
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(android.content.Intent.ACTION_VIEW);
		String type = PublicMethod.getMIMEType(fileHandler);
		intent.setDataAndType(Uri.fromFile(fileHandler),type);
		vcInfo.mmaViewController.startActivity(intent);	
		return;
	}else
		errMsg = "����ʧ�ܣ��ļ��������޷��򿪣�";
} 
catch (Exception e) 
{
	// e.printStackTrace();
	errMsg = "�Ҳ����򿪴������ļ���Ӧ�ó���";
}