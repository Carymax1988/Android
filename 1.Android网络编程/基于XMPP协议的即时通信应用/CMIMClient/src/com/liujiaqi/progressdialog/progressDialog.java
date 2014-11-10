package com.liujiaqi.progressdialog;

import com.liujiaqi.cmim.R;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class progressDialog extends Dialog {

	public progressDialog(Context context) {
		super(context);
		setCancelable(false);
		setCanceledOnTouchOutside(false);
	}
	
	public progressDialog(Context context, int theme) {
		super(context, theme);
		setCancelable(false);
		setCanceledOnTouchOutside(false);
	}
	
	public static progressDialog show(Context context, CharSequence message){
		progressDialog dialog = new progressDialog(context, R.style.ProgressHUD);
		dialog.setContentView(R.layout.progress_hud);
		if(message == null || message.length() == 0) {
			dialog.findViewById(R.id.message).setVisibility(View.GONE);			
		} else {
			TextView txt = (TextView)dialog.findViewById(R.id.message);
			txt.setText(message);
		}
		dialog.getWindow().getAttributes().gravity=Gravity.CENTER;
		WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();  
		lp.dimAmount=0.2f;
		dialog.getWindow().setAttributes(lp); 
		dialog.show();
		return dialog;
	}
}
