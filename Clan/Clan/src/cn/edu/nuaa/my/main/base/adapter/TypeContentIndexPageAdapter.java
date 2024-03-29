package cn.edu.nuaa.my.main.base.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.kit.imagelib.banner.common.ScrollImageEntity;
import com.kit.imagelib.banner.image.Banner;
import com.kit.imagelib.banner.image.ScrollImageController;
import com.kit.imagelib.uitls.ImageLibUitls;
import com.kit.utils.DeviceUtils;
import com.kit.utils.ZogUtils;
import com.kit.widget.listview.HorizontalListView;
import com.youzu.android.framework.JsonUtils;
import cn.edu.nuaa.my.R;
import cn.edu.nuaa.my.base.callback.JSONCallback;
import cn.edu.nuaa.my.base.enums.HomePageType;
import cn.edu.nuaa.my.base.enums.HomeRecommendType;
import cn.edu.nuaa.my.base.json.article.Article;
import cn.edu.nuaa.my.base.json.articleorthread.ArticleOrThread;
import cn.edu.nuaa.my.base.json.articleorthread.ArticleOrThreadJson;
import cn.edu.nuaa.my.base.json.homeconfig.HomeConfigItem;
import cn.edu.nuaa.my.base.json.homepageconfig.FunctionSetting;
import cn.edu.nuaa.my.base.json.homepageconfig.Recommend;
import cn.edu.nuaa.my.base.json.homepageconfig.ThreadConfig;
import cn.edu.nuaa.my.base.net.ThreadHttp;
import cn.edu.nuaa.my.base.util.AppSPUtils;
import cn.edu.nuaa.my.base.util.LoadImageUtils;
import cn.edu.nuaa.my.base.util.jump.JumpArticleUtils;
import cn.edu.nuaa.my.base.util.view.threadandarticle.ThreadAndArticleItemUtils;
import cn.edu.nuaa.my.base.widget.ViewHolder;
import cn.edu.nuaa.my.base.widget.list.OnLoadListener;
import cn.edu.nuaa.my.main.base.listener.OnBannerItemOnClickLisetener;
import cn.edu.nuaa.my.main.qqstyle.OnEmptyDataListener;
import cn.edu.nuaa.my.threadandarticle.BaseThreadAndArticleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 内容型
 */
public class TypeContentIndexPageAdapter extends BaseThreadAndArticleAdapter {

    private int pageCount = 1;
    public final static String TYPE_ARTICLE_FILTER = "4";

    protected final int TYPE_BANNER = 20 + 0;
    protected final int TYPE_LINK = 20 + 1;
    protected final int TYPE_PLATE = 20 + 2;
    protected final int TYPE_HOT_THREAD = 20 + 3;
    protected final int TYPE_HOT_THREAD_TITLE = 20 + 4;
    protected final int TYPE_FORUM_FILTER_MORE = 20 + 5;
    protected final int TYPE_FORUM_FILTER_ONLY_ONE = 20 + 6;

    private Context context;
    private LayoutInflater inflater;

    private OnEmptyDataListener onEmptyDataListener;


    //    private ArticleOrThreadVariables articleOrThreadVariables;
    private ArrayList<ArticleOrThread> articleOrThreads;

    private ScrollImageController scrollImageView;

    private int screenWidth;
    private boolean isShowFilter = false;
    private HashMap<String, String> maps = new HashMap<>();
    private ArrayList<Integer> eachFunctionCount = new ArrayList<>();

    private ArrayList<FunctionSetting> homePage;

    public TypeContentIndexPageAdapter(FragmentActivity context, OnEmptyDataListener mListener, ArrayList<FunctionSetting> homePage) {
        super(context);
        this.context = context;
        this.onEmptyDataListener = mListener;

        this.homePage = homePage;

        inflater = LayoutInflater.from(context);
        scrollImageView = new ScrollImageController(context);
        screenWidth = DeviceUtils.getScreenWidth(context);

        ZogUtils.printLog(TypeContentIndexPageAdapter.class, "ImageLibUitls.px2dip:" + ImageLibUitls.px2dip(context, 340));

    }


    @Override
    public int getCount() {
        return mSubjects.size();
    }


    private int getHomePageIndex(int position) {
        for (int i = 0; i < eachFunctionCount.size(); i++) {
            if (eachFunctionCount.get(i) > position) {
                return i;
            }
        }
        return 0;
    }

    private int getBeforeCount(int index) {
        int headerCount = 0;
        if (index > 0) {
            headerCount = eachFunctionCount.get(index - 1);
        }
        return headerCount;
    }

    @Override
    public int getViewTypeCount() {
        return 7 + super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {

        int index = getHomePageIndex(position);
        int headerCount = getBeforeCount(index);

        return super.getThreadType(position, headerCount);
    }


    @Override
    public Object getItem(int position) {
        int type = getItemViewType(position);
        int index = getHomePageIndex(position);
        FunctionSetting functionSetting = homePage.get(index);
        switch (type) {
            case TYPE_BANNER:
            case TYPE_LINK:
            case TYPE_PLATE:
                return functionSetting.getSetting();
            case TYPE_HOT_THREAD_TITLE:
            case TYPE_HOT_THREAD:
            case TYPE_THREAD_NO_IMAGE_MODE:
            case TYPE_THREAD_TEXT:
            case TYPE_SINGLE_IMAGE:
            case TYPE_IMAGES:
            case TYPE_AD_ONE_IMAGE:
            case TYPE_AD_MORE_IMAGES:
            case TYPE_ARTICLE_NO_IMAGE:
            case TYPE_ARTICLE_ONE_IMAGE:
                int headerCount = getBeforeCount(index);
                if (!HomeRecommendType.TYPE_CONTENT.equals(functionSetting.getRecommend().getType())) {
                    headerCount++;
                }
                Object object = mSubjects.get(position - headerCount);
                if (object instanceof ArticleOrThread) {
                    ArticleOrThread articleOrThread = (ArticleOrThread) object;
                    if (articleOrThread.isArticle()) {
                        return articleOrThread.getArticle();
                    }
                }
                return object;
        }

        return null;
    }

    protected void loadSuccess() {
        if (onEmptyDataListener != null && (articleOrThreads == null || articleOrThreads.isEmpty()) && homePage == null) {
            onEmptyDataListener.onEmpty();
        }
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int type = getItemViewType(position);
        switch (type) {
            case TYPE_BANNER:
                convertView = getBannerView(position, convertView);
                break;
            case TYPE_LINK:
                convertView = getLinkView(position, convertView);
                break;
            case TYPE_PLATE:
                convertView = getPlateView(position, convertView);
                break;
            case TYPE_ARTICLE_NO_IMAGE:
                convertView = getArticleNoImageView(position, convertView);
                break;
            case TYPE_ARTICLE_ONE_IMAGE:
                convertView = getArticleOneImageView(position, convertView);
                break;
            case TYPE_FORUM_FILTER_MORE:
                convertView = getForumFilterMoreItem(position, convertView);
                break;
            case TYPE_FORUM_FILTER_ONLY_ONE:
                convertView = getForumFilterOnlyOneItem(position, convertView);
                break;
            default:
                convertView = super.getView(position, convertView, parent);
        }
        return convertView;
    }

    /**
     * 过滤条件的item
     */
    public View getForumFilterItem(int position, View convertView) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_forum_filter_recomm, null);
        }
        RadioGroup group = ViewHolder.get(convertView, R.id.radio);

        int index = getHomePageIndex(position);
        final Recommend recommend = homePage.get(index).getRecommend();
        ArrayList<ThreadConfig> threadConfigItems = recommend.getThreadConfigs();

        int[] ids = {R.id.radioButton1, R.id.radioButton2, R.id.radioButton3, R.id.radioButton4};
        group.check(ids[recommend.getForumFilterSelectIndex()]);

        for (int i = 0; i < ids.length; i++) {
            RadioButton radioButton = ViewHolder.get(convertView, ids[i]);
            if (threadConfigItems != null && i < threadConfigItems.size()) {
                ThreadConfig item = threadConfigItems.get(i);
                radioButton.setVisibility(View.VISIBLE);
                radioButton.setText(getFilterItemTitle(item));
            } else {
                radioButton.setVisibility(View.GONE);
            }
            final int ii = i;
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    filterItemClick(recommend, ii);
                }
            });
        }
        return convertView;
    }


    /**
     * 只有一条过滤条件的item
     */
    public View getForumFilterOnlyOneItem(int position, View convertView) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_forum_filter_only_one, null);
        }
        int index = getHomePageIndex(position);
        final Recommend recommend = homePage.get(index).getRecommend();
        ArrayList<ThreadConfig> threadConfigItems = recommend.getThreadConfigs();

        TextView filterName = ViewHolder.get(convertView, R.id.filterName);
        if (threadConfigItems != null && threadConfigItems.size() == 1) {
            ThreadConfig threadConfigItem = threadConfigItems.get(0);
            filterName.setText(getFilterItemTitle(threadConfigItem));
        }
        return convertView;
    }

    private String getFilterItemTitle(ThreadConfig threadConfigItem) {
        return threadConfigItem.getTitle();
    }

    private void filterItemClick(Recommend recommend, int ii) {
        if (recommend.getForumFilterSelectIndex() == ii) {
            return;
        }
        recommend.setForumFilterSelectIndex(ii);
        if (doSomeThing != null) {
            doSomeThing.execute(ii);
        }
    }

    /**
     * 更多过滤条件的item
     */
    public View getForumFilterMoreItem(int position, View convertView) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_forum_filter_more, null);
        }

        int index = getHomePageIndex(position);
        final Recommend recommend = homePage.get(index).getRecommend();
        ArrayList<ThreadConfig> threadConfigItems = recommend.getThreadConfigs();

        HorizontalListView filterList = ViewHolder.get(convertView, R.id.filterList);
        final ChangeableFilterAdapter adapter = new ChangeableFilterAdapter(context);
        adapter.setForumFilterSelectIndex(recommend.getForumFilterSelectIndex());
        adapter.setThreadConfigItems(threadConfigItems);
        adapter.setMaps(maps);
        filterList.setAdapter(adapter);
        filterList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                filterItemClick(recommend, position);
            }
        });
        return convertView;
    }


    /**
     * 无图文章
     */
    private View getArticleNoImageView(int position, View convertView) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_forum_article_no_image, null);
        }
        Article article = (Article) getItem(position);
        dealArticle(position, convertView, article);
        return convertView;
    }

    /**
     * 单图文章
     */
    private View getArticleOneImageView(int position, View convertView) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_forum_article_one_image, null);
        }
        Article article = (Article) getItem(position);
        ImageView image = ViewHolder.get(convertView, R.id.content_image);
//        if(article.getAttachmentUrls()!=null&&article.getAttachmentUrls().size()>0){
//            PicassoUtils.display(context, image, article.getAttachmentUrls().get(0));
//        }
        LoadImageUtils.display(context, image, article.getPic());
        dealArticle(position, convertView, article);
        return convertView;
    }

    private void dealArticle(int position, View convertView, final Article article) {
        TextView contentTitle = ViewHolder.get(convertView, R.id.content_title);
        TextView contentSummary = ViewHolder.get(convertView, R.id.content_summary);
        TextView contentDate = ViewHolder.get(convertView, R.id.content_date);

        final String aid = article.getAid();
        boolean hasRead = ThreadAndArticleItemUtils.articleHasRead(context, aid);
        int colorRes = context.getResources().getColor(hasRead ? R.color.text_black_selected : R.color.text_black_ta_title);
        contentTitle.setTextColor(colorRes);

        contentTitle.setText(article.getTitle());
        contentSummary.setText(article.getSummary());
        contentDate.setText(article.getDateline());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpArticleUtils.gotoArticleDetail(context, aid);
            }
        });
    }

    private View getBannerView(int position, View convertView) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_home_banner, null);
            Banner banner = ViewHolder.get(convertView, R.id.banner);
            LinearLayout llBottom = ViewHolder.get(convertView, R.id.llBottom);
            ArrayList<HomeConfigItem> bannerData = (ArrayList<HomeConfigItem>) getItem(position);
            /**
             * 初始化滚动图片数据数据
             */
            ArrayList list = new ArrayList<ScrollImageEntity>();
            for (HomeConfigItem item : bannerData) {
                ScrollImageEntity scrollImageEntity = new ScrollImageEntity();
                scrollImageEntity.setImageUrl(item.getPic());
                scrollImageEntity.setTitle(item.getTitle());
                list.add(scrollImageEntity);
            }
            //启用图片滚动
            scrollImageView.init(list, banner, llBottom, 10 * 1000, new OnBannerItemOnClickLisetener(context, banner
                    , bannerData));
            scrollImageView.startAutoScroll();

        }

        return convertView;
    }

    private View getLinkView(int position, View convertView) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_home_link, null);
        }

        GridView gridView = ViewHolder.get(convertView, R.id.gridView);

        gridView.setNumColumns(3);
        gridView.setColumnWidth(screenWidth / 3);

        gridView.setAdapter(new LinkAdapter(context, (ArrayList<HomeConfigItem>) getItem(position)));

        return convertView;
    }


    private View getPlateView(int position, View convertView) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_home_plate, null);
        }

        GridView gridView = ViewHolder.get(convertView, R.id.gridView);

        gridView.setNumColumns(2);
        gridView.setColumnWidth(screenWidth / 2);

        gridView.setAdapter(new PlateAdapter(context, (ArrayList<HomeConfigItem>) getItem(position)));


        return convertView;
    }


    @Override
    public void refresh(final OnLoadListener listener) {
        pageCount = 1;
        loadListData(listener, true);
    }

    private void loadListData(final OnLoadListener listener, boolean isRefresh) {
        FunctionSetting functionSetting = homePage.get(0);
        if (HomePageType.FUNCTION_THREAD_OR_ARTICLE_LIST.equals(functionSetting.getType())) {
            loadHotThread(listener, functionSetting.getRecommend(), isRefresh);
        }
    }

    private void loadHotThread(final OnLoadListener listener, final Recommend recommend, final boolean isRefresh) {


        if (recommend == null || recommend.getThreadConfigs() == null || recommend.getThreadConfigs().size() == 0) {
            listener.onSuccess(false);
            return;
        }

        ZogUtils.printError(TypeContentIndexPageAdapter.class, "loadHotThread:" + recommend.getThreadConfigs().get(recommend.getForumFilterSelectIndex()).getDataLink());


        ThreadHttp.getHomeThread(context, recommend.getThreadConfigs().get(recommend.getForumFilterSelectIndex()).getDataLink(), pageCount, new JSONCallback() {
            @Override
            public void onSuccess(Context ctx, String jsonStr) {
                super.onSuccess(ctx, jsonStr);
                ArticleOrThreadJson hotThreadJson = JsonUtils.parseObject(jsonStr, ArticleOrThreadJson.class);
                boolean isNeedMore = false;

                if (hotThreadJson != null && hotThreadJson.getVariables() != null) {
                    String picMode = hotThreadJson.getVariables().getPicMode();
                    AppSPUtils.saveIndexPagePicMode(context, picMode);

                    if (isRefresh) {
                        mSubjects.clear();
                    }
                    isNeedMore = "1".equals(hotThreadJson.getVariables().getNeedMore());
                    articleOrThreads = hotThreadJson.getVariables().getData();
                    ZogUtils.printError(TypeContentIndexPageAdapter.class, "articleOrThreads.size():" + articleOrThreads.size());
                    mSubjects.addAll(articleOrThreads);
                    notifyDataSetChanged();
                } else {
                    loadSuccess();
                }
                pageCount++;
                listener.onSuccess(isNeedMore);
            }

            @Override
            public void onFailed(Context cxt, int errorCode, String errorMsg) {
                super.onFailed(cxt, errorCode, errorMsg);
                listener.onFailed();
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public void loadMore(final OnLoadListener listener) {
        loadListData(listener, false);
    }

    protected String getArticleId() {
        return "";
    }

    public ScrollImageController getScrollImageView() {
        return scrollImageView;
    }

    public void setScrollImageView(ScrollImageController scrollImageView) {
        this.scrollImageView = scrollImageView;
    }

    @Override
    public boolean isItemViewTypePinned(int viewType) {
        if (viewType == TYPE_FORUM_FILTER_MORE) {
            return true;
        }
        return super.isItemViewTypePinned(viewType);
    }


}