package cn.edu.nuaa.my.base.widget.list;

import android.content.Context;
import android.util.AttributeSet;

import cn.edu.nuaa.my.R;
import cn.edu.nuaa.my.base.widget.EmptyView;

/**
 * Created by tangh on 2015/7/9.
 */
public class SearchRefreshListview extends RefreshListView {
    public SearchRefreshListview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void setEmptyView(Context context) {
        mEmptyView = new EmptyView(context, R.layout.include_empty_search_view);
        mEmptyView.setOnErrorClickListener(mErrorClickListener);
        setEmptyView(mEmptyView);
    }

}
