package cn.edu.nuaa.my.article;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import cn.edu.nuaa.my.R;
import cn.edu.nuaa.my.base.BaseFragment;
import cn.edu.nuaa.my.base.widget.list.RefreshListView;

/**
 * Created by tangh on 2015/9/2.
 */
public class ArticleListFragment extends BaseFragment {

    private String catId;
    private RefreshListView list;
    private  ArticleListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        list = (RefreshListView) inflater.inflate(R.layout.fragment_list, null);
        adapter = new ArticleListAdapter(getActivity(), null, null);
        adapter.setCatId(catId);
        list.setDefaultMode(PullToRefreshBase.Mode.BOTH);
        list.setAdapter(adapter);
        return list;
    }

    @Override
    public void refresh() {
        adapter.notifyDataSetChanged();
    }

    public RefreshListView getListView() {
        return list;
    }


    public void setCatId(String catId) {
        this.catId = catId;
    }
}
