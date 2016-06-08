package cn.edu.nuaa.my.base.util.jump;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import cn.edu.nuaa.my.app.constant.Key;
import cn.edu.nuaa.my.base.json.friends.Friends;
import cn.edu.nuaa.my.base.json.mypm.Mypm;
import cn.edu.nuaa.my.base.util.AppSPUtils;
import cn.edu.nuaa.my.message.pm.ChatActivity;

/**
 * Created by Zhao on 15/9/9.
 */
public class JumpChatUtils {


    /**
     * 发起聊天
     * @param context
     */
    public static void gotoChat(FragmentActivity context,Mypm mypm) {
        Intent intent = getChatIntent(context,mypm);
        context.startActivity(intent);
    }



    /**
     * 发起聊天
     * @param context
     */
    public static void gotoChat(FragmentActivity context,Friends user) {

        Mypm mypm = new Mypm();
        mypm.setMsgfromidAvatar(AppSPUtils.getAvatartUrl(context));
        mypm.setMsgtoidAvatar(user.getAvatar());
        mypm.setTousername(user.getUsername());
        mypm.setTouid(user.getUid());

        gotoChat(context, mypm);
    }



    /**
     * 发起聊天
     * @param context
     * @param username
     * @param userID
     * @param avatar
     */
    public static void gotoChat(FragmentActivity context, String username, String userID, String avatar) {

        Mypm mypm = new Mypm();
        mypm.setMsgfromidAvatar(AppSPUtils.getAvatartUrl(context));
        mypm.setTousername(username);
        mypm.setTouid(userID);
        mypm.setMsgtoidAvatar(avatar);

        gotoChat(context,mypm);

    }


    /**
     * 得到推送过来信息的Intent
     *
     * @param context
     * @param username
     * @param userID
     * @param avatar
     */
    public static Intent getPushToChatIntent(Context context, String username, String userID, String avatar) {


        Mypm mypm = new Mypm();
        mypm.setMsgfromidAvatar(AppSPUtils.getAvatartUrl(context));
        mypm.setTousername(username);
        mypm.setTouid(userID);
        mypm.setMsgtoidAvatar(avatar);

        Intent intent = getChatIntent(context,mypm);

        return intent;


    }


    /**
     * 发起聊天
     * @param context
     */
    public static Intent getChatIntent(Context context,Mypm mypm) {
        Intent intent = new Intent();
        intent.setClass(context, ChatActivity.class);

        intent.putExtra(Key.KEY_MESSAGE, mypm);

        return intent;

    }




}
