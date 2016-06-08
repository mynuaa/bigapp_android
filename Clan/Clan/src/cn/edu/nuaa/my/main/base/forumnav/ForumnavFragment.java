package cn.edu.nuaa.my.main.base.forumnav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.youzu.android.framework.ViewUtils;
import com.youzu.android.framework.http.HttpCache;
import com.youzu.android.framework.view.annotation.ViewInject;
import com.youzu.android.framework.view.annotation.event.OnItemClick;
import cn.edu.nuaa.my.R;
import cn.edu.nuaa.my.base.BaseFragment;
import cn.edu.nuaa.my.base.config.AppBaseConfig;
import cn.edu.nuaa.my.base.json.forumnav.NavForum;
import cn.edu.nuaa.my.base.net.ClanHttpParams;
import cn.edu.nuaa.my.base.util.jump.JumpForumUtils;
import cn.edu.nuaa.my.base.widget.list.RefreshListView;

public class ForumnavFragment extends BaseFragment {

    private ForumnavAdapter mAdapter;
    @ViewInject(R.id.list)
    private RefreshListView mListView;
    private static ForumnavFragment fragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mListView = (RefreshListView) inflater.inflate(R.layout.fragment_list, container, false);
        ViewUtils.inject(this, mListView);
        ClanHttpParams params = new ClanHttpParams(getActivity());
        params.addQueryStringParameter("iyzmobile", "1");
        params.addQueryStringParameter("module", "forumnav");
        params.setCacheMode(HttpCache.CACHE_AND_REFRESH);
        params.setCacheTime(AppBaseConfig.CACHE_NET_TIME);
        ForumnavAdapter adapter = new ForumnavAdapter(getActivity(), params, null);
        mListView.setAdapter(adapter);
        mAdapter = adapter;
        return mListView;
    }


    public RefreshListView getListView() {
        return mListView;
    }

    @OnItemClick(R.id.list)
    public void itemClick(AdapterView<?> parent, View view, int position, long id) {
        NavForum forum = (NavForum) mAdapter.getItem(position);
//        ClanUtils.showForum(getActivity(), forum);
        JumpForumUtils.gotoForum(getActivity(), forum);
    }


    @Override
    public void refresh() {
        super.refresh();
        mListView.getRefreshableView().smoothScrollToPosition(0);
        mListView.setRefreshing(true);
        mListView.refresh();
    }
}
