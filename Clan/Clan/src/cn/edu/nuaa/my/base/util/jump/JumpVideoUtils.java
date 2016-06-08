package cn.edu.nuaa.my.base.util.jump;

import android.content.Context;

import cn.edu.nuaa.my.video.VideoPlayWebActivity;

/**
 * Created by Zhao on 15/9/9.
 */
public class JumpVideoUtils {


    /**
     * @param context
     * @param title
     * @param url     视频页面的地址
     */
    public static void gotoVideo(Context context, String title, String url) {
        VideoPlayWebActivity.gotoVideo(context, VideoPlayWebActivity.class, title, url);
    }


    /**
     * 直接全屏播放视频
     *
     * @param context
     * @param title
     * @param url     视频的真实地址
     */
    public static void play(Context context, String title, String url) {
        VideoPlayWebActivity.play(context, VideoPlayWebActivity.class, title, url);
    }
}
