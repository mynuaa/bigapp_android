package cn.edu.nuaa.my.base.callback;

import android.content.Context;

import cn.edu.nuaa.my.base.json.threadview.ThreadJson;

public abstract class HotThreadCallback extends HttpCallback<ThreadJson>{

	@Override
	public abstract void onSuccess(Context ctx,ThreadJson t);
	@Override
	public abstract void onFailed(Context cxt,int errorCode, String errorMsg);

}
