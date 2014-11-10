package com.liujiaqi.alertdialog;

import android.app.AlertDialog;
import android.content.Context;

public class CustomDialog extends AlertDialog {
	public CustomDialog(Context context) {
		super(context);
	}

	public CustomDialog(Context context, int theme) {
		super(context, theme);
	}

	public CustomDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
	}
}
