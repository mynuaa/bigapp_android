package cn.edu.nuaa.my.profile.thread;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.kit.utils.ZogUtils;
import com.kit.widget.slidingtab.SlidingTabLayout;
import com.youzu.android.framework.ViewUtils;
import com.youzu.android.framework.view.annotation.ContentView;
import cn.edu.nuaa.my.R;
import cn.edu.nuaa.my.app.ClanApplication;
import cn.edu.nuaa.my.app.constant.Key;
import cn.edu.nuaa.my.base.BaseActivity;
import cn.edu.nuaa.my.base.util.AppSPUtils;
import cn.edu.nuaa.my.base.util.theme.ThemeUtils;
import cn.edu.nuaa.my.base.widget.SimplePagerAdapter;

/**
 * TA的帖子
 *
 * @author wangxi
 */
@ContentView(R.layout.activity_mythread)
public class OwnerThreadActivity extends BaseActivity {
    private ClanApplication mApplication;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);

        mApplication = (ClanApplication) getApplication();

        Intent intent = getIntent();
        String uid = intent.getStringExtra(Key.KEY_UID);
        String[] tabs;
        if (AppSPUtils.getUid(this).equals(uid)) {
            setTitle(R.string.my_thread);
            tabs = getResources().getStringArray(R.array.my_thread);
        } else {
            setTitle(R.string.other_thread);
            tabs = getResources().getStringArray(R.array.other_thread);
        }

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        Fragment[] fragments = {new MyThreadFragment(uid), new MyReplyFragment(uid)};
        viewPager.setAdapter(new SimplePagerAdapter(getSupportFragmentManager(), tabs, fragments));
        SlidingTabLayout indicator = (SlidingTabLayout) findViewById(R.id.slidingIndicator);
        indicator.setDividerColors(0);
        indicator.setSelectedIndicatorColors(ThemeUtils.getThemeColor(this));
        indicator.setViewPager(viewPager);
        setCurr(indicator);

    }

    private void setCurr(SlidingTabLayout indicator) {
        String[] name = getResources().getStringArray(R.array.my_thread);

        String getName = name[0];
        try {
            getName = (String) getIntent().getExtras().get("name");
        } catch (Exception e) {
        }

        ZogUtils.printError(MyThreadActivity.class, "getName::::" + getName + " name[0]:" + name[0]);

        if (getName == null || getName.equals(name[0])) {
            indicator.setCurrentItem(0);
        } else {
            indicator.setCurrentItem(1);
        }
    }


}
