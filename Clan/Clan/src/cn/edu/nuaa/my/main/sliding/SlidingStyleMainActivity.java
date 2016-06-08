package cn.edu.nuaa.my.main.sliding;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.youzu.android.framework.view.annotation.ContentView;
import cn.edu.nuaa.my.R;
import cn.edu.nuaa.my.base.util.SlidingMenuUtils;
import cn.edu.nuaa.my.main.qqstyle.QQStyleMainActivity;

@ContentView(R.layout.activity_main_sliding)
public class SlidingStyleMainActivity extends QQStyleMainActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void initSlidingMenu() {

        viewPager = (ViewPager) findViewById(R.id.pager);
//        View[] ignoreView = {viewPager};

        SlidingMenuUtils.initSilidingMenu(this, null, true, getResources().getDrawable(R.drawable.trans), false);
    }
}
