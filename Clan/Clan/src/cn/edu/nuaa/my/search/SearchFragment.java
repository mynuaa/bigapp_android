package cn.edu.nuaa.my.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.kit.utils.StringUtils;
import com.kit.utils.ZogUtils;
import cn.edu.nuaa.my.R;
import cn.edu.nuaa.my.app.constant.Key;
import cn.edu.nuaa.my.base.BaseFragment;
import cn.edu.nuaa.my.base.json.search.SearchForum;
import cn.edu.nuaa.my.base.json.search.SearchThread;
import cn.edu.nuaa.my.base.json.search.User;
import cn.edu.nuaa.my.base.net.ClanHttpParams;
import cn.edu.nuaa.my.base.util.ClanBaseUtils;
import cn.edu.nuaa.my.base.util.jump.JumpForumUtils;
import cn.edu.nuaa.my.base.widget.list.BaseRefreshAdapter;
import cn.edu.nuaa.my.base.widget.list.OnLoadListener;
import cn.edu.nuaa.my.base.widget.list.SearchRefreshListview;
import cn.edu.nuaa.my.profile.homepage.HomePageActivity;
import cn.edu.nuaa.my.thread.detail.ThreadDetailActivity;

/**
 * Created by tangh on 2015/7/8.
 */
public class SearchFragment extends BaseFragment {

    public static final String SEARCH_THREAD = "search";
    public static final String SEARCH_FORUM = "searchforum";
    public static final String SEARCH_USER = "searchuser";
    public static int isShowListView = View.GONE;

    private String lastKey;
    private String moduleType;
    private String title;


    private ClanHttpParams params;
    private BaseRefreshAdapter adapter;
    private SearchRefreshListview searchListView;

    public void setModule(String module) {
        this.moduleType = module;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, null);
        searchListView = (SearchRefreshListview) view.findViewById(R.id.searchListView);
        searchListView.setVisibility(isShowListView);

        params = getClanHttpParams("");

        if (SEARCH_THREAD == moduleType) {
            adapter = new SearchThreadAdapter(getActivity(), params);
        } else if (SEARCH_FORUM == moduleType) {
            adapter = new SearchForumAdapter(getActivity(), params);
        } else {
            adapter = new SearchUserAdapter(getActivity(), params);
        }

        searchListView.setAdapter(adapter);

        searchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (SEARCH_THREAD == moduleType) {
                    String tid = ((SearchThread) adapter.getItem(position)).getTid();
                    Intent in = new Intent(getActivity(), ThreadDetailActivity.class);
                    in.putExtra("tid", tid);
                    startActivity(in);
                } else if (SEARCH_FORUM == moduleType) {
                    SearchForum forum = (SearchForum) adapter.getItem(position);
//                    ClanUtils.showForum(getActivity(), forum);

                    JumpForumUtils.gotoForum(getActivity(), forum);
                } else {
                    String uid = ((User) adapter.getItem(position)).getUid();
                    Intent in = new Intent(getActivity(), HomePageActivity.class);
                    in.putExtra(Key.KEY_UID, uid);
                    startActivity(in);
                }
            }
        });

        return view;
    }

    public void loadSearch(String lastKey) {
        this.lastKey = lastKey;
        if (TextUtils.isEmpty(lastKey)) {
            return;
        }
        isShowListView = View.VISIBLE;
        adapter.getData().clear();
        adapter.notifyDataSetChanged();
        adapter.mParams = getClanHttpParams(lastKey);
        searchListView.refresh(new OnLoadListener() {
            @Override
            public void onSuccess(boolean hasMore) {
                searchListView.setVisibility(isShowListView);
            }

            @Override
            public void onFailed() {

            }
        });
    }

    private ClanHttpParams getClanHttpParams(String lastKey) {
        ZogUtils.printError(SearchFragment.class, "adapter:" + adapter);

        if (adapter != null) {
            ZogUtils.printError(SearchFragment.class, "adapter.getPage():" + adapter.getPage());
        }
        ClanHttpParams params = new ClanHttpParams(getActivity());
        params.addQueryStringParameter("module", moduleType);
        params.addQueryStringParameter("iyzmobile", "1");
        params.addQueryStringParameter("keyword", lastKey);

        if (SEARCH_THREAD == moduleType) {
            params.addQueryStringParameter("tpp", "10");
        }
        if (SEARCH_FORUM != moduleType) {
            if (!StringUtils.isEmptyOrNullOrNullStr(ClanBaseUtils.getFormhash(getActivity())))
                params.addQueryStringParameter("formhash", ClanBaseUtils.getFormhash(getActivity()));
        }
        return params;
    }
}
