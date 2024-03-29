package cn.edu.nuaa.my.base.net;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.kit.app.ActivityManager;
import com.youzu.android.framework.JsonUtils;
import cn.edu.nuaa.my.app.InjectDo;
import cn.edu.nuaa.my.app.constant.Key;
import cn.edu.nuaa.my.base.callback.HttpCallback;
import cn.edu.nuaa.my.base.common.ResultCode;
import cn.edu.nuaa.my.base.json.ProfileJson;
import cn.edu.nuaa.my.base.json.profile.ProfileVariables;
import cn.edu.nuaa.my.base.util.AppSPUtils;
import cn.edu.nuaa.my.base.util.ClanUtils;
import cn.edu.nuaa.my.base.util.StringUtils;
import cn.edu.nuaa.my.login.LoginActivity;

/**
 * Created by Zhao on 15/9/6.
 */
public class DoProfile {


    /**
     * 统一的检查web登录的结束。
     * 包含第三方登录
     *
     * @param context
     * @param cookieStr
     * @param injectDo
     */
    public static void getProfile(final FragmentActivity context, String cookieStr, final InjectDo injectDo) {


        if (!StringUtils.isEmptyOrNullOrNullStr(cookieStr)) {
            ClanUtils.pushCookie(context, cookieStr);
        }


        ProfileHttp.profile(context, new HttpCallback() {
            @Override
            public void onSuccess(Context ctx, Object o) {
                super.onSuccess(context, o);

                ProfileJson profileJson = (ProfileJson) o;
                ProfileVariables profileVariables = profileJson.getVariables();
                String uid = profileVariables.getMemberUid();
                onLoginSuccess(context, uid, profileJson.getVariables().getMemberUsername(), profileJson);

                AppSPUtils.saveProfile(context, JsonUtils.toJSONString(profileJson));

                if (injectDo != null)
                    injectDo.doSuccess(o);

            }

            @Override
            public void onFailed(Context cxt, int errorCode, String errorMsg) {
                super.onFailed(cxt, errorCode, errorMsg);

                if (injectDo != null)
                    injectDo.doFail(errorMsg, errorCode + "");

            }
        });


    }


    private static void onLoginSuccess(Context context, String uid, String username, ProfileJson json) {
        AppSPUtils.setLoginInfo(context, true, uid, username);
        Intent intent = new Intent();
        intent.putExtra(Key.KEY_LOGINED, true);
        intent.putExtra(Key.KEY_LOGIN_RESULT, json);
        ((FragmentActivity) context).setResult(ResultCode.RESULT_CODE_LOGIN, intent);

        ActivityManager.getInstance().popActivity(LoginActivity.class);
        ((FragmentActivity) context).finish();
    }


}
