package cn.edu.nuaa.my.base.callback;

import android.support.v4.app.FragmentActivity;

import cn.edu.nuaa.my.base.json.FavThreadJson;

public  class FavThreadCallback extends ProgressCallback<FavThreadJson> {

    public FavThreadCallback(FragmentActivity activity) {
        super(activity);
    }
}
