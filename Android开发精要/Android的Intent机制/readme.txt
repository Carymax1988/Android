//�������ѡ��û��Ĭ��ֵ
Intent intent = new Intent();
		intent.setAction(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, "Hello");
		try{
			startActivity(Intent.createChooser(intent, "Choose"));
		}catch(Exception e){
			//û�к��ʵ�Ӧ��
		}

//�������ѡ����Զ�����ʽ
Intent intent = new Intent();
		intent.setAction(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, "Hello");
		List<ResolveInfo> activities = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
		//�Զ�����ʽ