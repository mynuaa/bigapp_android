package cn.edu.nuaa.my.base.net;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;

import com.kit.utils.MessageUtils;
import cn.edu.nuaa.my.base.callback.StringCallback;
import cn.edu.nuaa.my.base.enums.MessageVal;
import cn.edu.nuaa.my.base.json.BaseJson;
import cn.edu.nuaa.my.base.json.act.ActPublishInfo;
import cn.edu.nuaa.my.base.util.ClanUtils;

/**
 * Created by Zhao on 15/8/20.
 */
public class DoBlog {

    public static final int SEND_PUBLISH_OK = 111 + 1;
    public static final int SEND_FAIL = 111 + 2;
    public static final int SEND_PASS_APPLY_OK = 111 + 3;
    public static final int SEND_REFUSE_APPLY_ZJ_OK = 111 + 4;
    public static final int SEND_REFUSE_APPLY_ZL_OK = 111 + 5;
    public static final int SEND_ACT_APPLY_OK = 111 + 6;
    public static final int SEND_ACT_CANCEL_APPLY_OK = 111 + 7;

    public static void send(FragmentActivity activity, final Handler handler,
                            ActPublishInfo info, Object attaches) {
        ActHttp.sendActPublish(activity, info, attaches, new StringCallback(activity) {
            @Override
            public void onstart(Context cxt) {
                setShowLoading(false);
                super.onstart(cxt);
            }

            @Override
            public void onSuccess(Context ctx, String t) {
                try {
                    BaseJson baseJson = ClanUtils.parseObject(t, BaseJson.class);
                    cn.edu.nuaa.my.base.json.model.Message message = baseJson.getMessage();
                    if (message.getMessageval().contains(MessageVal.POST_NEWTHREAD_SUCCEED)) {
                        MessageUtils.sendMessage(handler, SEND_PUBLISH_OK);
                    } else {
                        String msgStr = message.getMessagestr();
                        MessageUtils.sendMessage(handler, 0, 0, msgStr, SEND_FAIL, null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    MessageUtils.sendMessage(handler, 0, 0, "请求失败了", SEND_FAIL, null);
                }
            }

            @Override
            public void onFailed(Context cxt, int errorCode, String msg) {
                super.onFailed(activity, errorCode, msg);
                MessageUtils.sendMessage(handler, 0, 0, msg, SEND_FAIL, null);
            }
        });
    }
}
