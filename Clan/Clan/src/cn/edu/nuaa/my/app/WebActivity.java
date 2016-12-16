package cn.edu.nuaa.my.app;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.webkit.WebView;

import com.kit.app.ActivityManager;
import com.kit.app.enums.AppStatus;
import com.kit.extend.ui.web.client.DefaultWebViewClient;
import com.kit.extend.ui.web.model.WebviewGoToTop;
import com.kit.extend.ui.web.webview.LoadMoreWebView;
import com.kit.utils.ActionBarUtils;
import com.kit.utils.ZogUtils;
import com.youzu.android.framework.ViewUtils;
import com.youzu.android.framework.view.annotation.event.OnClick;

import java.lang.reflect.Field;

import cn.edu.nuaa.my.R;
import cn.edu.nuaa.my.app.config.AppConfig;
import cn.edu.nuaa.my.base.IDClan;
import cn.edu.nuaa.my.base.util.AppSPUtils;
import cn.edu.nuaa.my.base.util.CookieUtils;
import cn.edu.nuaa.my.base.util.jump.JumpWebUtils;
import cn.edu.nuaa.my.base.util.theme.ThemeUtils;
import cn.sharesdk.analysis.MobclickAgent;

public class WebActivity extends com.kit.extend.ui.web.WebActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
        initTheme();
        ActionBarUtils.setHomeActionBar(this, R.drawable.ic_back);
    }

    public void initTheme() {
        ThemeUtils.initTheme(this);
    }

    @Override
    public boolean getExtra() {
        super.getExtra();
        contentViewName = "activity_web";
        return true;
    }

    public boolean initWidget() {
        setOverflowShowingAlways();
        return super.initWidget();
    }

    public boolean loadData() {
        return super.loadData();
    }

    public boolean initWidgetWithData() {
        return super.initWidgetWithData();
    }

    private void setOverflowShowingAlways() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class
                    .getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTitle(int resId) {
        getSupportActionBar().setTitle(resId);
    }

    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ThemeUtils.initResource(this);
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.back)
    public void back(View view) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().popActivity(this);
//        if(LoadingDialogFragment.getInstance(this)!=null){
//            LoadingDialogFragment.getInstance(this).dismissAllowingStateLoss();
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppConfig.isShowing = AppStatus.SHOWING;
        //统计时长
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        AppConfig.isShowing = AppStatus.BACKGROUND;
        MobclickAgent.onPause(this);
    }

    @Override
    public void initWebView() {
        super.initWebView();
        // 允许调试
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        webFragment.getWebView().getSettings().setUserAgentString(webFragment.getWebView().getSettings().getUserAgentString() + " My_APP");
        webFragment.setGoToTopPosition(getGoToTopPosition());
        webFragment.setWebScrollListener(new LoadMoreWebView.WebScrollListener() {
            @Override
            public void onScroll(int dx, int dy) {
            }
            @Override
            public void onTop() {
            }
            @Override
            public void onCenter() {
            }
            @Override
            public void onBottom() {
                ZogUtils.printLog(WebActivity.class, "bottom load more");
                if (!webFragment.isRefreshing())
                    getData(false);
            }
        });
        webFragment.getWebView().setWebViewClient(new DefaultWebViewClient(webFragment) {
            @Override
            public void loadingUrl(WebView view, String url) {
                JumpWebUtils.gotoWeb(WebActivity.this, "", url);
            }
        });
    }

    private void getData(final boolean isJumpPage) {
    }

    @Override
    public void setCookieFromCookieStore() {
        cookies = CookieUtils.getCookies(this);
        webFragment.setCookieFromCookieStore(this, content, cookies);
    }

    private int getGoToTopPosition() {
        if (AppSPUtils.getShowGoToTop(WebActivity.this) == IDClan.ID_RADIOBUTTON_SHOW_GO_TO_TOP_RIGHT) {
            return WebviewGoToTop.RIGHT;
        } else if (AppSPUtils.getShowGoToTop(WebActivity.this) == IDClan.ID_RADIOBUTTON_SHOW_GO_TO_TOP_LEFT) {
            return WebviewGoToTop.LEFT;
        } else
            return WebviewGoToTop.NONE;
    }
}
