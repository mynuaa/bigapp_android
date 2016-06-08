package cn.edu.nuaa.my.main.qqstyle;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cn.edu.nuaa.my.R;
import cn.edu.nuaa.my.base.util.ClanUtils;
import cn.edu.nuaa.my.main.base.IndexPageFragment;

public class QQStyleMainTabAdapter extends FragmentPagerAdapter {

    private Context context;
    private OnEmptyDataListener listener;

    public QQStyleMainTabAdapter(Context context, FragmentManager fm, OnEmptyDataListener listener) {
        super(fm);
        this.context = context;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int arg0) {
        return arg0 == 0 ? IndexPageFragment.getInstance(listener) : ClanUtils.getForumNav(context);
    }

    @Override
    public CharSequence getPageTitle(int position) {
//		super.getPageTitle(position);
        String[] tabTiles = context.getResources().getStringArray(R.array.main_tab);
        return tabTiles[position];

    }

}
