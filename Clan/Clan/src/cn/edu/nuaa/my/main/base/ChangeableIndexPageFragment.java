package cn.edu.nuaa.my.main.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.gc.materialdesign.views.ButtonFloat;
import com.kit.app.core.task.DoSomeThing;
import com.kit.pinnedsectionlistview.PinnedSectionListView;
import com.kit.utils.ZogUtils;
import com.kit.utils.intentutils.IntentUtils;
import com.youzu.android.framework.JsonUtils;
import com.youzu.android.framework.ViewUtils;
import com.youzu.android.framework.view.annotation.ViewInject;
import com.youzu.android.framework.view.annotation.event.OnClick;
import com.youzu.android.framework.view.annotation.event.OnItemClick;
import cn.edu.nuaa.my.R;
import cn.edu.nuaa.my.base.BaseFragment;
import cn.edu.nuaa.my.base.enums.HomeRecommendType;
import cn.edu.nuaa.my.base.enums.MainActivityStyle;
import cn.edu.nuaa.my.base.json.forumdisplay.Thread;
import cn.edu.nuaa.my.base.json.homepageconfig.FunctionSetting;
import cn.edu.nuaa.my.base.util.AppSPUtils;
import cn.edu.nuaa.my.base.util.ClanUtils;
import cn.edu.nuaa.my.base.util.jump.JumpThreadUtils;
import cn.edu.nuaa.my.base.widget.LoadingDialogFragment;
import cn.edu.nuaa.my.base.widget.list.OnLoadListener;
import cn.edu.nuaa.my.base.widget.list.PinnedSectionRefreshListView;
import cn.edu.nuaa.my.main.base.adapter.TypeContentIndexPageAdapter;
import cn.edu.nuaa.my.main.base.adapter.TypeRecommendIndexPageAdapter;
import cn.edu.nuaa.my.main.base.listener.OnBannerInitOKListener;
import cn.edu.nuaa.my.main.qqstyle.OnEmptyDataListener;
import cn.edu.nuaa.my.thread.ThreadPublishActivity;

import java.util.ArrayList;

/**
 * 可变的首页
 *
 * @author zhao
 */
public class ChangeableIndexPageFragment extends BaseFragment implements OnBannerInitOKListener {
    private BaseAdapter mAdapter;


    @ViewInject(R.id.list)
    private PinnedSectionRefreshListView mListView;


    @ViewInject(R.id.buttonFloat)
    private ButtonFloat buttonFloat;

    private OnEmptyDataListener mListener;

//    private ArrayList<FunctionSetting> homePager;

    private ArrayList<FunctionSetting> homePage = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index_page, container, false);
        ViewUtils.inject(this, view);

        if (MainActivityStyle.TAB_BOTTOM == AppSPUtils.getConfig(getActivity()).getAppStyle()
                || MainActivityStyle.QZONE == AppSPUtils.getConfig(getActivity()).getAppStyle()) {
            buttonFloat.setVisibility(View.GONE);
        }

        setAdapter();
        return view;
    }

    public void setmListener(OnEmptyDataListener mListener) {
        this.mListener = mListener;
    }

    public void setHomePager(ArrayList<FunctionSetting> homePage) {

        this.homePage = homePage;
        ZogUtils.printError(ChangeableIndexPageFragment.class, "setHomePager:" + JsonUtils.toJSONString(homePage));

        if (homePage.get(0).getRecommend() != null)
            ZogUtils.printError(ChangeableIndexPageFragment.class, "setHomePager:" + homePage.get(0).getRecommend().getThreadConfigs().get(0).getDataLink());

    }


    /**
     * 创建新帖
     *
     * @param view
     */
    @OnClick(R.id.buttonFloat)
    public void newThread(View view) {
        if (ClanUtils.isToLogin(getActivity(), null, Activity.RESULT_OK, false)) {
            return;
        }
        IntentUtils.gotoNextActivity(getActivity(), ThreadPublishActivity.class);
    }

    public void setAdapter() {

        BaseAdapter adapter = null;

        if (homePage.size() == 1 && homePage.get(0).getRecommend() != null && HomeRecommendType.TYPE_CONTENT.equals(homePage.get(0).getRecommend().getType())) {
            adapter = new TypeContentIndexPageAdapter(getActivity(), mListener, homePage);
        } else {
            adapter = new TypeRecommendIndexPageAdapter(getActivity(), mListener, homePage);
            ((TypeRecommendIndexPageAdapter) adapter).setDoSomeThing(new DoSomeThing() {
                @Override
                public void execute(Object... object) {
                    showLoading();
                    mListView.refresh(new OnLoadListener() {
                        @Override
                        public void onSuccess(boolean hasMore) {
                            dissmissLoading();
                        }

                        @Override
                        public void onFailed() {
                            dissmissLoading();
                        }
                    });
                }
            });
        }
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(null);
        ((PinnedSectionListView) mListView.getRefreshableView()).setShadowVisible(false);
        mAdapter = adapter;
    }

    public void showLoading() {
        ZogUtils.printError(ChangeableIndexPageFragment.class, "showLoading showLoading");
        LoadingDialogFragment.getInstance(getActivity()).show();
    }

    public void dissmissLoading() {
        LoadingDialogFragment.getInstance(getActivity()).dismissAllowingStateLoss();
    }

    @Override
    public void onBannerInitOK() {
        if (mAdapter != null && (mAdapter instanceof TypeRecommendIndexPageAdapter)
                && ((TypeRecommendIndexPageAdapter) mAdapter).getScrollImageView() != null)
            ((TypeRecommendIndexPageAdapter) mAdapter).getScrollImageView().startAutoScroll();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }


    public PinnedSectionRefreshListView getListView() {
        return mListView;
    }


    @OnItemClick(R.id.list)
    public void itemClick(AdapterView<?> parent, View view, int position, long id) {
        cn.edu.nuaa.my.base.json.forumdisplay.Thread thread = (Thread) mAdapter.getItem(position);
        JumpThreadUtils.gotoThreadDetail(getActivity(), thread.getTid());
//        ClanUtils.showDetail(getActivity(), thread.getTid());
    }


    @Override
    public void refresh() {
        mListView.setRefreshing(true);
        mListView.getRefreshableView().smoothScrollToPosition(0);
        mListView.refresh();
    }
}
