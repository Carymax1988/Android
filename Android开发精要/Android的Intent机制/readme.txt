//界面组件选择没有默认值
Intent intent = new Intent();
		intent.setAction(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, "Hello");
		try{
			startActivity(Intent.createChooser(intent, "Choose"));
		}catch(Exception e){
			//没有合适的应用
		}

//界面组件选择的自定义样式
Intent intent = new Intent();
		intent.setAction(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, "Hello");
		List<ResolveInfo> activities = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
		//自定义样式