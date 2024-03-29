package cn.edu.nuaa.my.forum;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.kit.utils.ZogUtils;
import com.youzu.android.framework.JsonUtils;
import cn.edu.nuaa.my.base.callback.JSONCallback;
import cn.edu.nuaa.my.base.config.Url;
import cn.edu.nuaa.my.base.json.ForumAdJson;
import cn.edu.nuaa.my.base.json.ForumDisplayJson;
import cn.edu.nuaa.my.base.json.forumdisplay.Forum;
import cn.edu.nuaa.my.base.json.forumdisplay.Thread;
import cn.edu.nuaa.my.base.net.BaseHttp;
import cn.edu.nuaa.my.base.net.ClanHttpParams;
import cn.edu.nuaa.my.base.widget.list.OnLoadListener;
import cn.edu.nuaa.my.threadandarticle.BaseThreadAndArticleAdapter;

import java.util.ArrayList;
import java.util.List;

public class ForumTypeAdapter extends BaseThreadAndArticleAdapter {

    private Context context;
    private String typeId;

    public ForumTypeAdapter(FragmentActivity context, Forum forum, String typeId) {
        super(context);
        this.context = context;
        this.mForum = forum;
        this.typeId = typeId;
    }

    @Override
    public void refresh(final OnLoadListener listener) {
        mCurrentPage = 1;
        loadListData(listener, true);
    }

    private void loadListData(final OnLoadListener listener, boolean isRefresh) {
        getSubjects(listener, isRefresh);
    }


    public void getSubjects(final OnLoadListener listener, final boolean isRefresh) {
        ZogUtils.printError(ForumAdapter.class, "getSubjects getSubjects getSubjects");

        BaseHttp.post(Url.DOMAIN,
                getParams(), new JSONCallback() {
                    List<ForumAdJson> ads = null;

                    @Override
                    public void onSuccessInThread(Context cxt, String s) {
                        super.onSuccessInThread(cxt, s);
                        try {
                            ads = getAdsList();
                        } catch (Exception e) {
                            ZogUtils.showException(e);
                        }
                    }


                    @Override
                    public void onSuccess(Context ctx, String jsonStr) {
                        super.onSuccess(ctx, jsonStr);
                        ForumDisplayJson hotThreadJson = JsonUtils.parseObject(jsonStr, ForumDisplayJson.class);
                        boolean isNeedMore = false;

                        if (hotThreadJson != null && hotThreadJson.getVariables() != null) {

                            if (isRefresh) {
                                mSubjects.clear();
                            }
                            isNeedMore = "1".equals(hotThreadJson.getVariables().getNeedMore());
                            ArrayList<Thread> articleOrThreads = hotThreadJson.getVariables().getForumThreadList();
                            ZogUtils.printError(ForumTypeAdapter.class, "articleOrThreads.size():" + articleOrThreads.size());
                            mSubjects.addAll(articleOrThreads);
                            notifyDataSetChanged();
                        } else {
                            loadSuccess();
                        }
                        mCurrentPage++;
                        listener.onSuccess(isNeedMore);
                    }

                    @Override
                    public void onFailed(Context cxt, int errorCode, String errorMsg) {
                        super.onFailed(context, errorCode, errorMsg);
                        listener.onFailed();

                    }
                });

    }


    protected void loadSuccess() {
        if ((mSubjects == null || mSubjects.isEmpty())) {
//			onEmptyDataListener.onEmpty();
        }
    }

    @Override
    public void loadMore(OnLoadListener listener) {
        getSubjects(listener, false);
    }

    @Override
    public int getCount() {
        return mSubjects.size();
    }

    private ClanHttpParams getParams() {

        ClanHttpParams params = new ClanHttpParams(context);
        params.addQueryStringParameter("module", "forumdisplay");
        params.addQueryStringParameter("fid", mForum.getFid());
        params.addQueryStringParameter("filter", "typeid");
        params.addQueryStringParameter("typeid", typeId);
        params.addQueryStringParameter("page", mCurrentPage + "");
        return params;
    }


}
