package cn.edu.nuaa.my.base.callback;

import android.content.Context;

import cn.edu.nuaa.my.base.json.ForumnavJson;

public abstract class ForumnavCallback extends HttpCallback<ForumnavJson>{

	@Override
	public abstract void onSuccess(Context ctx,ForumnavJson t);
	@Override
	public abstract void onFailed(Context cxt,int errorCode, String errorMsg);

}
