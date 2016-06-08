package cn.edu.nuaa.my.base.util.view.threadandarticle;

import android.app.Activity;

import com.kit.utils.ListUtils;
import com.kit.utils.intentutils.IntentUtils;
import cn.edu.nuaa.my.R;
import cn.edu.nuaa.my.base.json.forumnav.NavForum;
import cn.edu.nuaa.my.base.util.ClanUtils;
import cn.edu.nuaa.my.base.util.ToastUtils;
import cn.edu.nuaa.my.main.base.forumnav.DBForumNavUtils;
import cn.edu.nuaa.my.thread.ThreadPublishActivity;

import java.util.List;

/**
 * 设置文章和帖子列表
 * Created by Zhao on 15/11/5.
 */
public class ThreadAndArticleUtils extends ContentUtils {



    public static void addThread(Activity context) {
        if (ClanUtils.isToLogin(context, null, Activity.RESULT_OK, false)) {
            return;
        }
        List<NavForum> forums = DBForumNavUtils.getAllNavForum(context);
        if (ListUtils.isNullOrContainEmpty(forums)) {
            ToastUtils.mkShortTimeToast(context, context.getString(R.string.wait_a_moment));
        } else
            IntentUtils.gotoNextActivity(context, ThreadPublishActivity.class);
    }

}
