package cn.edu.nuaa.my.base.callback;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.kit.utils.ToastUtils;
import cn.edu.nuaa.my.base.json.ProfileJson;
import cn.edu.nuaa.my.base.json.login.YZLoginParams;

public abstract class YZLoginCallback extends ProgressCallback<ProfileJson> {
	
	public YZLoginCallback(FragmentActivity activity) {
		super(activity);
	}

	public void onSuccess(Context ctx,ProfileJson t) {
		super.onSuccess(ctx,t);
	}
	
	@Override
	public void onFailed(Context cxt,int errorCode, String msg) {
		super.onFailed(activity,errorCode, msg);
		ToastUtils.mkLongTimeToast(activity, msg);
	}
	
	public void onFailed(YZLoginParams params) {
		super.onFailed(activity,-1, "");
	}
	
}
