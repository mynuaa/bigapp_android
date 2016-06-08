package cn.edu.nuaa.my.base.callback;

import android.support.v4.app.FragmentActivity;

import cn.edu.nuaa.my.base.json.VariablesJson;

public abstract class UploadAvatarCallback extends ProgressCallback<VariablesJson>{

	public UploadAvatarCallback(FragmentActivity activity) {
		super(activity);
	}
}
