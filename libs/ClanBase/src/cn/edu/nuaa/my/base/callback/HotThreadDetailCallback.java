package cn.edu.nuaa.my.base.callback;

import android.support.v4.app.FragmentActivity;

import cn.edu.nuaa.my.base.json.threadview.ThreadDetailJson;

public  class HotThreadDetailCallback extends ProgressCallback<ThreadDetailJson>{

	public HotThreadDetailCallback(FragmentActivity activity) {
		super(activity);
	}
}
