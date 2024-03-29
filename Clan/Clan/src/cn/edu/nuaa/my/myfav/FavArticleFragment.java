package cn.edu.nuaa.my.myfav;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.kit.utils.ZogUtils;
import com.youzu.android.framework.ViewUtils;
import com.youzu.android.framework.view.annotation.event.OnItemClick;
import cn.edu.nuaa.my.R;
import cn.edu.nuaa.my.base.EditableFragment;
import cn.edu.nuaa.my.base.callback.FavThreadCallback;
import cn.edu.nuaa.my.base.json.FavThreadJson;
import cn.edu.nuaa.my.base.json.article.ArticleFav;
import cn.edu.nuaa.my.base.json.model.Message;
import cn.edu.nuaa.my.base.net.ArticleHttp;
import cn.edu.nuaa.my.base.net.ThreadHttp;
import cn.edu.nuaa.my.base.util.ToastUtils;
import cn.edu.nuaa.my.base.util.jump.JumpArticleUtils;
import cn.edu.nuaa.my.base.widget.list.OnDataSetChangedObserver;
import cn.edu.nuaa.my.base.widget.list.RefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 收藏帖子
 * 
 * @author wangxi
 *
 */
public class FavArticleFragment extends EditableFragment {

	private RefreshListView mListView;
	private FavArticleAdapter mAdapter;
	private OnDataSetChangedObserver mObserver;

	public FavArticleFragment() {
	}

	public FavArticleFragment(OnDataSetChangedObserver observer) {
		mObserver = observer;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		RefreshListView listView = (RefreshListView) inflater.inflate(R.layout.fragment_list, container, false);
		ViewUtils.inject(this, listView);
		FavArticleAdapter adapter = new FavArticleAdapter(getActivity(), ArticleHttp.getArticleFavsParams(getActivity(),1));
		listView.setAdapter(adapter);
		listView.setOnEditListener(this);
		mListView = listView;
		mAdapter = adapter;
		adapter.setOnDataSetChangedObserver(mObserver);
		return listView;
	}

	@OnItemClick(R.id.list)
	public void itemClick(AdapterView<?> parent, View view, int position, long id) {
    	ZogUtils.printLog(FavArticleFragment.class, "position--->" + position
                + " adapter.getCount()：" + mAdapter.getCount());
    	ArticleFav article = (ArticleFav) mAdapter.getItem(position);

		JumpArticleUtils.gotoArticleDetail(getActivity(), article.getId());
	}
	
	/**
	 * 删除
	 */
	@Override
	public void onDelete() {
		if (mListView.getCheckedItemCount() < 1) {
			return;
		}
		final List<ArticleFav> articles = getDeletedArticles();
		String[] ids=new String[articles.size()];

		for(int i=0;i<articles.size();i++){
			ids[i]=articles.get(i).getFavid();
		}
		//帖子的取消收藏和文章的取消收藏是一个接口；
		ThreadHttp.delFavThread(getActivity(), ids, new FavThreadCallback(getActivity()){
			@Override
			public void onSuccess(Context ctx, FavThreadJson favThreadJson) {
				super.onSuccess(ctx, favThreadJson);
				Message message = favThreadJson.getMessage();
				if (message != null && "favorite_delete_succeed".equals(message.getMessageval())) {
					mListView.deleteChoices();
					ToastUtils.show(getActivity(), R.string.delete_success);
					deleteLocalFavArticle(articles);
					return;
				}
				String value = message.getMessageval();
				onFailed(ctx, -1, value);
			}

			@Override
			public void onFailed(Context cxt, int errorCode, String msg) {
				super.onFailed(getActivity(), errorCode, msg);
				ToastUtils.show(getActivity(), TextUtils.isEmpty(msg) ? getString(R.string.delete_failed) : msg);
			}
		});
	}
	
	/**
	 * 删除本地数据库中的文章
	 * @param articles
	 */
	public void deleteLocalFavArticle(List<ArticleFav> articles) {
		for (ArticleFav article:articles) {
			MyFavUtils.deleteArticle(getActivity(), article.getId());
		}
	}

	private ArrayList<ArticleFav> getDeletedArticles() {
		SparseBooleanArray array = mListView.getChoicePostions();
		int headers = mListView.getRefreshableView().getHeaderViewsCount();
		int count = mAdapter.getCount();
		ArrayList<ArticleFav> articles = new ArrayList<ArticleFav>();
		for (int i = headers; i < count + headers; i++) {
			if (array.get(i)) {
				ArticleFav article = (ArticleFav) mAdapter.getItem(i-headers);
				articles.add(article);
			}
		}
		return articles;
	}

	@Override
	public RefreshListView getListView() {
		return mListView;
	}


}
