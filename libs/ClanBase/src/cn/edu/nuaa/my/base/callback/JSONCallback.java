package cn.edu.nuaa.my.base.callback;

import android.content.Context;

public class JSONCallback extends HttpCallback<String> {

    @Override
    public void onSuccess(Context ctx, String jsonStr) {
        super.onSuccess(ctx, jsonStr);
    }

    @Override
    public void onFailed(Context cxt, int errorCode, java.lang.String errorMsg) {
        super.onFailed(cxt, errorCode, errorMsg);

    }
}
