package cn.edu.nuaa.my.base.callback;

import android.support.v4.app.FragmentActivity;

public  class StringCallback extends ProgressCallback<String> {

    public StringCallback(FragmentActivity activity) {
        super(activity);
    }
}
