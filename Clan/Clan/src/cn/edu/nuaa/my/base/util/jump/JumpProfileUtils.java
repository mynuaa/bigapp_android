package cn.edu.nuaa.my.base.util.jump;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.kit.utils.ZogUtils;
import com.kit.utils.intentutils.IntentUtils;
import cn.edu.nuaa.my.app.constant.Key;
import cn.edu.nuaa.my.base.util.AppSPUtils;
import cn.edu.nuaa.my.base.util.ClanUtils;
import cn.edu.nuaa.my.profile.homepage.HomePageActivity;

/**
 * Created by Zhao on 15/11/3.
 */
public class JumpProfileUtils {
    public static void gotoProfilePage(Context context, String uid) {

        if (!AppSPUtils.isLogined(context)) {
//			toLogin();
            ClanUtils.gotoLogin(context, null, 1, false);
            return;
        }
//        Intent intent = new Intent();
//        if (uid.equals(myUid)) {
//        intent.setClass(context, MyHomePageActivity.class);
//        }else{
//          intent.setClass(context, OthersPageActivity.class);
//        }

//        intent.setClass(context, HomePageActivity.class);

        ZogUtils.printError(ClanUtils.class, "toProfilePage uid:" + uid);
//        intent.putExtra(Key.KEY_UID, uid);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent);
        Bundle bundle = new Bundle();
        bundle.putString(Key.KEY_UID, uid);
        IntentUtils.gotoNextActivity(context, HomePageActivity.class, bundle);
    }

    /**
     * 个人信息Intent
     *
     * @param context
     */
    public static Intent getHomePageIntent(Context context, String uid) {
        Intent intent = new Intent(context, HomePageActivity.class);
        intent.putExtra(Key.KEY_UID, uid);
        return intent;
    }

}
