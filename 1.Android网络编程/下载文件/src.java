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
		errMsg = "下载失败，文件不存在无法打开！";
} 
catch (Exception e) 
{
	// e.printStackTrace();
	errMsg = "找不到打开此类型文件的应用程序！";
}