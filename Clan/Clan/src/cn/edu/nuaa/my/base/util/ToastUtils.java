package cn.edu.nuaa.my.base.util;


import android.content.Context;


public class ToastUtils extends com.kit.utils.ToastUtils{
	private static android.widget.Toast mToast;
	
	public static void show(Context context, String msg) {
		if (mToast == null) {
			mToast = android.widget.Toast.makeText(context, msg, android.widget.Toast.LENGTH_LONG);
		}
		mToast.setText(msg);
		mToast.show();
	}
	
	public static void show(Context context, int resId) {
		show(context, context.getResources().getString(resId));
	}

}
