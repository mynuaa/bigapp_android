package cn.edu.nuaa.my.base.util.jump;

import android.content.Context;
import android.content.Intent;

import com.kit.utils.ZogUtils;
import cn.edu.nuaa.my.app.constant.Key;
import cn.edu.nuaa.my.base.json.forum.BaseForum;
import cn.edu.nuaa.my.base.json.forumnav.NavForum;
import cn.edu.nuaa.my.forum.ForumActivity;

/**
 * Created by Zhao on 15/9/9.
 */
public class JumpForumUtils {

    /**
     * 跳转到帖子详情
     *
     * @param context
     */
    public static void gotoForum(Context context, String forumTitle, String forumID) {
        NavForum forum = new NavForum();
//                forum.setIcon(item.getPic());
        forum.setName(forumTitle);
        forum.setFid(forumID);
        gotoForum(context,forum);

//        Intent intent = getForumIntent(context, forum);
//        context.startActivity(intent);
    }

    /**
     * 跳转到帖子详情
     *
     * @param context
     */
    public static void gotoForum(Context context, BaseForum forum) {
        Intent intent = getForumIntent(context, forum);
        context.startActivity(intent);
    }


    /**
     * 帖子详情Intent
     *
     * @param context
     */
    public static Intent getForumIntent(Context context, BaseForum forum) {
        ZogUtils.printError(JumpForumUtils.class,"context:::::"+context);
        Intent intent = new Intent(context, ForumActivity.class);
        intent.putExtra(Key.KEY_FORUM, forum);
        return intent;
    }


}
