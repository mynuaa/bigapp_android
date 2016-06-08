package cn.edu.nuaa.my.main.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kit.widget.slidingtab.SlidingTabLayout;
import com.youzu.android.framework.ViewUtils;
import com.youzu.android.framework.view.annotation.ViewInject;
import cn.edu.nuaa.my.R;
import cn.edu.nuaa.my.base.BaseFragment;
import cn.edu.nuaa.my.base.interfaces.IRefresh;
import cn.edu.nuaa.my.base.json.homepageconfig.ViewTabConfig;
import cn.edu.nuaa.my.base.json.homepageconfig.NavPage;
import cn.edu.nuaa.my.base.util.theme.ThemeUtils;
import cn.edu.nuaa.my.main.qqstyle.OnEmptyDataListener;

import java.util.ArrayList;

/**
 * 导航Fragment
 */
public class ChangeableNavPageFragment extends BaseFragment {

    @ViewInject(R.id.covering)
    private View covering;

    @ViewInject(R.id.viewPager)
    private ViewPager viewPager;

    @ViewInject(value = R.id.slidingIndicator)
    private SlidingTabLayout indicator;

    private OnEmptyDataListener mListener;


    private ChangeableNavPageAdapter adapter;

    private ViewTabConfig viewTabConfig;

    private ArrayList<NavPage> navPages;

    public void setOnEmptyDataListener(OnEmptyDataListener listener) {
        mListener = listener;
    }


    public void setViewTabConfig(ViewTabConfig viewTabConfig) {
        this.viewTabConfig = viewTabConfig;
        this.navPages = viewTabConfig.getNavPage();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_changable_portal_page, null);
        ViewUtils.inject(this, view);
        adapter = new ChangeableNavPageAdapter(getChildFragmentManager(), getActivity());
        adapter.setViewTabConfig(viewTabConfig);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(5);

        indicator.setSelectedIndicatorColors(ThemeUtils.getThemeColor(getActivity()));
        indicator.setDividerColors(0);
        indicator.setViewPager(viewPager);

        return view;
    }

    @Override
    public void refresh() {
        Fragment f = adapter.getMaps().get(viewPager.getCurrentItem());
        if (f instanceof IRefresh) {
            ((IRefresh) f).refresh();
        }
    }
}
