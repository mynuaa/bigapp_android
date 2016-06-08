package cn.edu.nuaa.my.thread.deal.rate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.kit.utils.FragmentUtils;
import com.kit.utils.ZogUtils;
import com.kit.utils.intentutils.BundleData;
import com.kit.widget.textview.GoBackTextView;
import com.youzu.android.framework.ViewUtils;
import com.youzu.android.framework.view.annotation.ViewInject;
import cn.edu.nuaa.my.R;
import cn.edu.nuaa.my.app.constant.Key;
import cn.edu.nuaa.my.base.BaseFragment;
import cn.edu.nuaa.my.base.json.threadview.rate.ViewRating;
import cn.edu.nuaa.my.base.json.threadview.rate.ViewRatingJson;
import cn.edu.nuaa.my.base.util.StringUtils;
import cn.edu.nuaa.my.base.widget.list.RefreshListView;

import java.util.ArrayList;


/**
 * 帖子评分
 */
public class ViewRatingFragment extends BaseFragment {

    private ViewRatingAdapter mAdapter;

    private RefreshListView mListView;
    ViewRatingJson viewRatingJson;


    String reason;

    @ViewInject(R.id.gtv)
    private GoBackTextView gtv;


    @ViewInject(R.id.et)
    private EditText et;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_rating, container, false);
        ViewUtils.inject(this, view);
        mListView = (RefreshListView) view.findViewById(R.id.list);
        mListView.setMode(PullToRefreshBase.Mode.DISABLED);
        BundleData bundleData = FragmentUtils.getData(this);

        ZogUtils.printObj(ViewRatingFragment.class, bundleData);
        viewRatingJson = bundleData.getObject(Key.CLAN_DATA, ViewRatingJson.class);
        ArrayList<ViewRating> list = viewRatingJson.getVariables().getRateList();


        ZogUtils.printError(ViewRatingFragment.class, "list.size():" + list.size());

        mAdapter = new ViewRatingAdapter(getActivity(), list);
        mListView.setAdapter(mAdapter);
        return view;
    }


    public RefreshListView getListView() {
        return mListView;
    }

    @Override
    public void refresh() {
        super.refresh();
    }


    public ViewRatingAdapter getAdapter() {
        return mAdapter;
    }


    public String getReason() {
        String etStr = et.getText().toString();
        String sub = StringUtils.isEmptyOrNullOrNullStr(etStr) ? "" : " " + etStr;
        return reason + sub;
    }
}
