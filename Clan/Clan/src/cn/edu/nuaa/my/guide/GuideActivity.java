package cn.edu.nuaa.my.guide;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

import com.igexin.sdk.PushManager;
import com.kit.app.core.task.DoSomeThing;
import com.kit.utils.ListUtils;
import com.kit.utils.MessageUtils;
import com.kit.utils.ZogUtils;
import com.kit.widget.numberprogressbar.NumberProgressBar;
import com.shelwee.update.UpdateHelper;
import com.youzu.android.framework.JsonUtils;
import com.youzu.android.framework.view.annotation.ContentView;
import com.youzu.android.framework.view.annotation.ViewInject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.edu.nuaa.my.R;
import cn.edu.nuaa.my.app.ClanApplication;
import cn.edu.nuaa.my.app.config.AppConfig;
import cn.edu.nuaa.my.app.constant.Key;
import cn.edu.nuaa.my.base.BaseActivity;
import cn.edu.nuaa.my.base.callback.JSONCallback;
import cn.edu.nuaa.my.base.json.ProfileJson;
import cn.edu.nuaa.my.base.json.config.content.ContentConfig;
import cn.edu.nuaa.my.base.json.forumnav.NavForum;
import cn.edu.nuaa.my.base.json.homepageconfig.HomePageJson;
import cn.edu.nuaa.my.base.json.profile.ProfileVariables;
import cn.edu.nuaa.my.base.net.ClanHttp;
import cn.edu.nuaa.my.base.util.AppSPUtils;
import cn.edu.nuaa.my.base.util.ClanUtils;
import cn.edu.nuaa.my.base.util.InitUtils;
import cn.edu.nuaa.my.base.util.theme.ThemeUtils;
import cn.edu.nuaa.my.main.base.forumnav.DBForumNavUtils;

@ContentView(R.layout.activity_guide)
public class GuideActivity extends BaseActivity {

    private static final int INIT_CONFIG_END = 2;
    private static final int INIT_CONTENT_CONFIG_END = 3;
    private static final int INIT_PROFILE_END = 6;
    private static final int INIT_HOME_PAGE_CONFIG_END = 7;
    private static final int INIT_FORUM_DATA_END = 8;
    private static final int INIT_CONFIG_ERROR = 101;
    private boolean isInitError = false;
    private String errorMsg = "";


    private ProfileVariables mProfileVariables;
    private ClanApplication mApplication;

    private long showTime;


    private int initSplashFlag = 0;

    private int initData = 0;


    @ViewInject(R.id.image)
    private ImageView mImageView;
    @ViewInject(R.id.progressBar)
    private NumberProgressBar progressBar;
    @ViewInject(R.id.errorMsg)
    private TextView errorView;

    private Handler mHander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case INIT_CONFIG_END:
                    initSplashFlag++;
                    if (initSplashFlag >= 1) {
                        initContentConfig();
                        initProfile();
                        initHomePageConfig();
                        initForumData();
                    }
                    break;

                case INIT_HOME_PAGE_CONFIG_END:
                    HomePageJson homePageJson = AppSPUtils.getHomePageConfigJson(GuideActivity.this);
                    if (homePageJson == null || homePageJson.getVariables() == null || homePageJson.getVariables().getButtonConfigs() == null) {
                        ZogUtils.printError(GuideActivity.class, "homePageJson is errer   homePageJson is errer");
                        errorMsg += getString(R.string.homePage_config_error);
                        MessageUtils.sendMessage(mHander, INIT_CONFIG_ERROR);
                        return;
                    }
                    initData++;
                    ZogUtils.printError(GuideActivity.class,"INIT_HOME_PAGE_CONFIG_END initData:"+initData);

                    break;
                case INIT_CONTENT_CONFIG_END:
                    ContentConfig contentConfig = AppSPUtils.getContentConfig(GuideActivity.this);
                    if (contentConfig == null) {
                        errorMsg += getString(R.string.content_config_error);
                        MessageUtils.sendMessage(mHander, INIT_CONFIG_ERROR);
                        return;
                    }
                    initData++;
                    ZogUtils.printError(GuideActivity.class,"INIT_CONTENT_CONFIG_END initData:"+initData);

                    break;
                case INIT_PROFILE_END:
                    initData++;
                    ZogUtils.printError(GuideActivity.class,"INIT_PROFILE_END initData:"+initData);
                    break;
                case INIT_FORUM_DATA_END:
                    List<NavForum> forums = DBForumNavUtils.getAllNavForum(GuideActivity.this);
                    if (ListUtils.isNullOrContainEmpty(forums)) {
                        errorMsg +=getString(R.string.forum_data_error);
                        ZogUtils.printError(GuideActivity.class, "forums data is errer   forums data is errer");
                        MessageUtils.sendMessage(mHander, INIT_CONFIG_ERROR);
                        return;
                    }
                    initData++;
                    ZogUtils.printError(GuideActivity.class, "INIT_FORUM_DATA_END initData:" + initData);
                    break;
                case INIT_CONFIG_ERROR:
                    isInitError = true;
                    progressBar.setReachedBarColor(getResources().getColor(R.color.red));
                    progressBar.setProgressTextColor(getResources().getColor(R.color.red));
                    errorView.setText(errorMsg);
                    break;

            }
            super.handleMessage(msg);
        }
    };

    private Runnable mToMainRunnable = new Runnable() {

        @Override
        public void run() {
            toMain();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = (ClanApplication) getApplication();

        ClanUtils.loadMyFav(this);

        InitUtils.initShareSDK(getApplicationContext());

        PushManager.getInstance().initialize(this.getApplicationContext(), cn.edu.nuaa.my.forum.DemoPushService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), cn.edu.nuaa.my.forum.DemoIntentService.class);
        //初始化广告配置
        InitUtils.initConfig(GuideActivity.this, new DoSomeThing() {
            @Override
            public void execute(Object... objects) {
            ZogUtils.printLog(GuideActivity.class, "initConfig initConfig initConfig");
            MessageUtils.sendMessage(mHander, INIT_CONFIG_END);
            }
        });

        mImageView.setImageResource(R.drawable.splash);

        new Thread(new Runnable() {
            @Override
            public void run() {
                mImageView.setImageBitmap(getHttpBitmap("http://oceg1qe2m.bkt.clouddn.com/images/splash.png"));
            }
        }).start();

        progressBar();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String time = format.format(date);
        UpdateHelper updateHelper = new UpdateHelper.Builder(this)
                .checkUrl("http://oceg1qe2m.bkt.clouddn.com/version.json?" + time)
                .isAutoInstall(false) //设置为false需在下载完手动点击安装;默认值为true，下载后自动安装。
                .build();
        updateHelper.check();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHander.removeCallbacks(mToMainRunnable);
    }


    /**
     * 加载用户信息
     */
    private void initProfile() {
        ClanHttp.getProfile(this, new JSONCallback() {
            public void onSuccess(Context ctx, String str) {
                super.onSuccess(ctx, str);
                try {
                    ProfileJson t = JsonUtils.parseObject(str, ProfileJson.class);
                    if (t == null || t.getVariables() == null) {
                        MessageUtils.sendMessage(mHander, INIT_PROFILE_END);
                        return;
                    }
                    mProfileVariables = t.getVariables();
                    ZogUtils.printError(GuideActivity.class, "mProfileVariables:" + mProfileVariables.getMemberUid());
                } catch (Exception e) {

                }
                MessageUtils.sendMessage(mHander, INIT_PROFILE_END);

            }

            @Override
            public void onFailed(Context cxt, int errorCode, String errorMsg) {
                MessageUtils.sendMessage(mHander, INIT_PROFILE_END);
            }
        });
    }

    private void initForumData() {
        InitUtils.preLoadForumData(GuideActivity.this, new DoSomeThing() {
            @Override
            public void execute(Object... object) {
            ZogUtils.printLog(GuideActivity.class, "initForumData initForumData initForumData");
            MessageUtils.sendMessage(mHander, INIT_FORUM_DATA_END);
            }
        });
    }

    private void initHomePageConfig() {
        //初始化首页界面配置
        InitUtils.initHomePageConfig(GuideActivity.this, new DoSomeThing() {
            @Override
            public void execute(Object... objects) {
            ZogUtils.printLog(GuideActivity.class, "initHomePageConfig initHomePageConfig initHomePageConfig");
            MessageUtils.sendMessage(mHander, INIT_HOME_PAGE_CONFIG_END);
            }
        });
    }

    private void initContentConfig() {
        //初始化内容显示配置项
        InitUtils.initContentConfig(GuideActivity.this, new DoSomeThing() {
            @Override
            public void execute(Object... objects) {
            ZogUtils.printLog(GuideActivity.class, "initContentConfig initContentConfig initContentConfig");
            MessageUtils.sendMessage(mHander, INIT_CONTENT_CONFIG_END);
            }
        });
    }

    private void progressBar() {
        progressBar.setReachedBarColor(ThemeUtils.getThemeColor(this));
        progressBar.setProgressTextColor(ThemeUtils.getThemeColor(this));
        progressBar.setProgressTextSize(20);
        new Thread(new Runnable() {
            @Override
            public void run() {
            long time = 0;
            while (!isInitError) {
                int i = progressBar.getProgress();
                int max = progressBar.getMax();

                if (i == 99 && initData < 4) {
                    //进度快走完了，但是数据结构还没有都完成
                    //不进行进度增加
                    ZogUtils.printError(GuideActivity.class,"time="+time);
                } else {
                    i++;
                }
                int v;
                if (i / 25 > initData) {
                    if (i == 99) {
                        v = 5;
                    } else if (i > 75) {
                        v = 20;
                    } else if (i > 50) {
                        v = 15;
                    } else {
                        v = 10;
                    }
                } else {
                    if (initData >= 4) {
                        i++;
                        v = 1;
                    } else {
                        v = 5;
                    }
                }

                progressBar.setProgress(i);
                if (i >= max) {
                    long delayTime = showTime * 1000;
                    if (time > delayTime) {
                        delayTime = 0;
                    } else {
                        delayTime -= time;
                    }

                    mHander.postDelayed(mToMainRunnable, delayTime);
                    break;
                }

                try {
                    time += 50 * v;
                    Thread.sleep(50 * v);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            }
        }).start();
    }

    private void toMain() {
        if (mProfileVariables == null || mProfileVariables.getMemberUid().equals("0")) {
            AppSPUtils.setLoginInfo(this, false, "0", "");
        }
        ZogUtils.printLog(GuideActivity.class, "toMain toMain toMain");
        toMainActivity(ClanUtils.getMain(this));
    }

    private void toMainActivity(Class clazz) {
        AppConfig.isNewLaunch = true;
        Intent intent = new Intent(GuideActivity.this, clazz);
        intent.putExtra(Key.KEY_PROFILE, mProfileVariables);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int arg0, int arg1, Intent arg2) {
        super.onActivityResult(arg0, arg1, arg2);
        toMain();
        finish();
    }

    /*
     *动态获取splash
     */
    public static Bitmap getHttpBitmap(String url){
        URL myFileURL;
        Bitmap bitmap=null;
        try{
            myFileURL = new URL(url);
            HttpURLConnection conn=(HttpURLConnection)myFileURL.openConnection();
            conn.setConnectTimeout(10000);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }
}
