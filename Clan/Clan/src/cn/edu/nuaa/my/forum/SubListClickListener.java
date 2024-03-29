package cn.edu.nuaa.my.forum;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.kit.utils.ZogUtils;
import cn.edu.nuaa.my.app.constant.Key;
import cn.edu.nuaa.my.base.json.forumnav.NavForum;

public class SubListClickListener implements OnClickListener {
    private FragmentActivity mContext;
    private NavForum forum;

    public SubListClickListener(FragmentActivity context, NavForum forum) {
        mContext = context;
        this.forum = forum;
    }

    @Override
    public void onClick(View widget) {

        ZogUtils.printLog(SubListClickListener.class,"SubListClickListener onClick!!!");
        Intent intent = new Intent(mContext, ForumActivity.class);
        intent.putExtra(Key.KEY_FORUM, forum);
        mContext.startActivity(intent);
    }

}
