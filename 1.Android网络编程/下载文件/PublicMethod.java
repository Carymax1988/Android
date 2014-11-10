package com.miracle.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.miracle.mma.MMAClientApp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class PublicMethod {
	
	//�ж��ֻ�����ϵͳ�汾
	static public int getAndroidSDKVersion()
	{
		int version = 0;
		try {
			version = Integer.valueOf(android.os.Build.VERSION.SDK);
		}
		catch ( NumberFormatException e)
		{
			//log
		}
		return version;
	}
	
	static public boolean appInAndroidVirtualDevice() {

		if (MMAClientApp.phoneModel.toLowerCase().equals("sdk")
				&& "000000000000000".equals(MMAClientApp.phoneDeviceId)) {
			return true;
		}
		return false;
	}
	
	
	
	static public boolean isEmulator() {
		if(Build.MODEL.indexOf("Genymotion") >=0)
			return true;
        return ( (Build.MODEL.toLowerCase().indexOf("emulator") > 0)//intel emulator
        		|| (Build.MODEL.equals("sdk")) || (Build.MODEL.equals("google_sdk"))); // not intel emulator
	}
	
	static public boolean validateIPAddress(String ip)
	{
		return true;
	}

	static public boolean validatePort(int port)
	{
		return true;
	}
	
	static public String getImageDrawableNameForFileType(String sPathExtension)
	{
		String pathExtension = sPathExtension.toLowerCase();
		if (pathExtension!=null && pathExtension.length()==0) return "";
		if (pathExtension.startsWith("doc") )
				return "doc";
		else if (pathExtension.startsWith("xls") )
			return "xls";
		else if (pathExtension.startsWith("ppt") )
			return "ppt";
		else if (pathExtension.indexOf("db")>=0 )
			return "db";
		else if ( (pathExtension.startsWith("bmp")) ||
				 (pathExtension.startsWith("gif")) || 
				 (pathExtension.startsWith("png")) || 
				 (pathExtension.startsWith("jpg"))	 || 
				 (pathExtension.startsWith("jpeg")))
			return "jpg";
		else if (pathExtension.startsWith("rar") ||
				 pathExtension.startsWith("zip") )
			return "zip";
		else if (pathExtension.startsWith("txt") )
			return "txt";
		else if (pathExtension.startsWith("pdf") )
			return "pdf";
		else if (pathExtension.startsWith("mov") || pathExtension.startsWith("mp"))
			return "videofile";
		return "unknow";
	}
	
	
	// ����һ��MIME�������ļ���׺����ƥ���
	static private final String[][] MIME_MapTable = {
		{ ".3gp", "video/3gpp" },
			{ ".apk", "application/vnd.android.package-archive" },
			{ ".asf", "video/x-ms-asf" }, { ".avi", "video/x-msvideo" },
			{ ".bin", "application/octet-stream" }, { ".bmp", "image/bmp" },
			{ ".c", "text/plain" }, { ".class", "application/octet-stream" },
			{ ".conf", "text/plain" }, { ".cpp", "text/plain" },
			{ ".doc", "application/msword" },
			{ ".docx", "application/msword" },
			{ ".exe", "application/octet-stream" },
			{ ".gif", "image/gif" }, { ".gtar", "application/x-gtar" },
			{ ".gz", "application/x-gzip" }, { ".h", "text/plain" },
			{ ".htm", "text/html" }, { ".html", "text/html" },
			{ ".jar", "application/java-archive" }, { ".java", "text/plain" },
			{ ".jpeg", "image/jpeg" }, { ".jpg", "image/jpeg" },
			{ ".js", "application/x-javascript" }, { ".log", "text/plain" },
			{ ".m3u", "audio/x-mpegurl" }, { ".m4a", "audio/mp4a-latm" },
			{ ".m4b", "audio/mp4a-latm" }, { ".m4p", "audio/mp4a-latm" },
			{ ".m4u", "video/vnd.mpegurl" }, { ".m4v", "video/x-m4v" },
			{ ".mov", "video/quicktime" }, { ".mp2", "audio/x-mpeg" },
			{ ".mp3", "audio/x-mpeg" }, { ".mp4", "video/mp4" },
			{ ".mpc", "application/vnd.mpohun.certificate" },
			{ ".mpe", "video/mpeg" }, { ".mpeg", "video/mpeg" },
			{ ".mpg", "video/mpeg" }, { ".mpg4", "video/mp4" },
			{ ".mpga", "audio/mpeg" },
			{ ".msg", "application/vnd.ms-outlook" }, { ".ogg", "audio/ogg" },
			{ ".pdf", "application/pdf" }, { ".png", "image/png" },
			{ ".pot", "application/vnd.ms-powerpoint" },
			{ ".pps", "application/vnd.ms-powerpoint" },
			{ ".ppt", "application/vnd.ms-powerpoint" },
			{ ".ppz", "application/vnd.ms-powerpoint" },
			{ ".prop", "text/plain" },
			{ ".rar", "application/x-rar-compressed" },
			{ ".rc", "text/plain" }, { ".rmvb", "audio/x-pn-realaudio" },
			{ ".rtf", "application/rtf" },
			{ ".sh", "text/plain" }, { ".tar", "application/x-tar" },
			{ ".tgz", "application/x-compressed" },
			{ ".tiff", "image/tiff" }, { ".tif", "image/tiff" },
			{ ".txt", "text/plain" }, { ".wav", "audio/x-wav" },
			{ ".wma", "audio/x-ms-wma" }, { ".wmv", "audio/x-ms-wmv" },
			{ ".wps", "application/vnd.ms-works" },
			{ ".xlc", "application/vnd.ms-excel" },
			{ ".xls", "application/vnd.ms-excel" },
			{ ".xlsx", "application/vnd.ms-excel" },
			{ ".xlm", "application/vnd.ms-excel" },
			{ ".xlw", "application/vnd.ms-excel" }, { ".xml", "text/xml" },
			{ ".z", "application/x-compress" }, { ".zip", "application/zip" },
			{ "", "*/*" } };

		/**
		 * �����ļ���׺����ö�Ӧ��MIME���͡�
		 * 
		 * @param file
		 */
		static public String getMIMEType(File file) {
			String type = "*/*";
			String fName = file.getName();

			/* ��ȡ�ļ��ĺ�׺�� */
			String end = fName.substring(fName.lastIndexOf("."), fName.length())
					.toLowerCase();
			if (end == "")
				return type;

			// ��MIME���ļ����͵�ƥ������ҵ���Ӧ��MIME���͡�
			for (int i = 0; i < MIME_MapTable.length; i++) {

				if (end.equals(MIME_MapTable[i][0]))
					type = MIME_MapTable[i][1];
			}
			return type;
		}
	
		static public void hideSoftInputFormDismiss(Context c, EditText et) {
			// TODO Auto-generated method stub
			InputMethodManager imm = (InputMethodManager) c
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
		}

		static public void hideSoftInputFormDismiss(Activity activity) {
			// TODO Auto-generated method stub
			InputMethodManager imm = (InputMethodManager) activity
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(
					activity.getCurrentFocus().getWindowToken(), 0);
		}
	
		static public int calculatePixelValByDensity(int val, float mscale)
		{
			return (int)(Math.round(val * mscale));
		}
		static public Bitmap ResizeBmp(Context mContext,int imgResId)
		{
			Bitmap bmp = BitmapFactory.decodeResource(mContext.getResources(), imgResId);        
	        int bmpWidth = bmp.getWidth();
	        int bmpHeight = bmp.getHeight();
	        float scale = mContext.getResources().getDisplayMetrics().density;
		  	float scaleWidth = 1;
		    float scaleHeight = 1;
			double scale1 = 1 / scale;
		    scaleWidth = (float) (scaleWidth * scale1);
		    scaleHeight = (float) (scaleHeight * scale1);
		    Matrix matrix = new Matrix();
		    matrix.postScale(scaleWidth, scaleHeight);
		    Bitmap resizeBmp = Bitmap.createBitmap(bmp, 0, 0, bmpWidth, bmpHeight,
		            matrix, true);		
			return resizeBmp;
		}
		public static Drawable createImage(Context context,File imageFile) {
	        try {
	            // ȡ�õ�ǰ��Ļ�ĳ���
	            DisplayMetrics dm = new DisplayMetrics();
	            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
	            float screenWidth = dm.widthPixels;
	            float screenHeight = dm.heightPixels;
	            
	            // ȡ��ͼƬ�Ĵ�С���������ű���
	            BitmapFactory.Options o = new BitmapFactory.Options();
	            o.inJustDecodeBounds = true;
	            BitmapFactory.decodeStream(new FileInputStream(imageFile), null, o);
	            float bitmapWidth = o.outWidth;
	            float bitmapHeight = o.outHeight;
	            float scale = (screenWidth / bitmapWidth < screenHeight
	                    / bitmapHeight) ? screenWidth / bitmapWidth : screenHeight
	                    / bitmapHeight;
	            
	            // ͼƬ��С�Ŵ�
	            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(
	                    imageFile));
	            Matrix matrix = new Matrix();
	            matrix.postScale(scale, scale);
	            Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
	                    bitmap.getWidth(), bitmap.getHeight(), matrix, true);
	            bitmap.recycle();
	            // ���Ʊ���ͼƬ
	            Bitmap mBitmap = Bitmap.createBitmap((int) screenWidth,
	                    (int) screenHeight, Bitmap.Config.RGB_565);
	            Canvas mCanvas = new Canvas(mBitmap);
	            Paint BitmapPaint = new Paint(Paint.FILTER_BITMAP_FLAG);
	            // �趨������ɫ
	            mCanvas.drawColor(0xff000000);
	            mCanvas.drawBitmap(resizedBitmap, screenWidth / 2 - bitmapWidth
	                    * scale / 2, screenHeight / 2 - bitmapHeight * scale / 2,
	                    BitmapPaint);
	            mCanvas.save();
	            BitmapDrawable drawable = new BitmapDrawable(mBitmap);
	            resizedBitmap.recycle();
	            return drawable;
	        } catch (FileNotFoundException e) {
	        	
	        }
	        return null;
	    }
}
