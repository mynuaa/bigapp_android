package cn.edu.nuaa.my.main.bottomtab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.LinearLayout;

import com.kit.utils.DialogUtils;
import com.kit.utils.ListUtils;
import com.kit.utils.ZogUtils;
import com.youzu.android.framework.view.annotation.ContentView;
import com.youzu.android.framework.view.annotation.ViewInject;
import cn.edu.nuaa.my.R;
import cn.edu.nuaa.my.app.config.BuildType;
import cn.edu.nuaa.my.app.constant.Key;
import cn.edu.nuaa.my.base.common.Action;
import cn.edu.nuaa.my.base.enums.BottomButtonType;
import cn.edu.nuaa.my.base.json.ProfileJson;
import cn.edu.nuaa.my.base.json.homepageconfig.ButtonConfig;
import cn.edu.nuaa.my.base.json.homepageconfig.HomepageVariables;
import cn.edu.nuaa.my.base.json.homepageconfig.ViewTabConfig;
import cn.edu.nuaa.my.base.json.profile.ProfileVariables;
import cn.edu.nuaa.my.base.json.profile.Space;
import cn.edu.nuaa.my.base.util.AppSPUtils;
import cn.edu.nuaa.my.base.util.ClanUtils;
import cn.edu.nuaa.my.base.util.ServiceUtils;
import cn.edu.nuaa.my.base.util.StringUtils;
import cn.edu.nuaa.my.base.util.ToastUtils;
import cn.edu.nuaa.my.base.util.view.MainTopUtils;
import cn.edu.nuaa.my.base.util.view.ViewDisplayUtils;
import cn.edu.nuaa.my.common.WebFragment;
import cn.edu.nuaa.my.main.base.activity.BaseMainActivity;
import cn.edu.nuaa.my.main.wechatstyle.MineProfileFragment;
import cn.edu.nuaa.my.message.MessageFragment;

import java.util.ArrayList;

/**
 * Created by Zhao on 15/6/24.
 */
@ContentView(R.layout.activity_main_bottom_tab)
public class BottomTabMainActivity extends BaseMainActivity {
    public static int MESSAGE_POSITION = 3;
    public static int NOW_POSITION_IN_VIEWPAGER = 0;

    @ViewInject(R.id.top)
    private LinearLayout mTop;


    private static ArrayList<Fragment> fragments = new ArrayList<Fragment>();

    private long exitTime;

    private static TabholderFragment tabholderFragment;
    private HomepageVariables homepageVariables;
    private ProfileVariables mProfileVariables;

//    private Skeleton skeleton;

    private SharedPreferences.OnSharedPreferenceChangeListener mPreferenceListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (key.equals(Key.KEY_NEW_MESSAGE)) {

                int messageCount = sharedPreferences.getInt(key, 0);
                ZogUtils.printError(BottomTabMainActivity.class, "messageCount:" + messageCount);

                if (messageCount > 0) {
                    setRedDot(messageCount);

                    for (Fragment f : fragments) {
                        if (f instanceof MessageFragment) {
                            ((MessageFragment) f).refresh();
                        }
                    }
                } else {
                    tabholderFragment.getTabLayout().setNotifyText(MESSAGE_POSITION, "");
                }
            } else if (key.equals(Key.KEY_AVATAR)) {
                final String avatar = sharedPreferences.getString(Key.KEY_AVATAR, "");
//                displayMineAvatar(avatar);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainTopUtils.setActivityTopbar(this, mTop, R.layout.top_main, getString(R.string.forum_name), null);

//        ibSearch = (ImageButton) views.get(8);

        fragments.clear();

        homepageVariables = AppSPUtils.getHomePageConfigJson(this).getVariables();
        ArrayList<ButtonConfig> buttonConfigs = homepageVariables.getButtonConfigs();

        for (int i = 0; i < buttonConfigs.size(); i++) {
            ButtonConfig bc = buttonConfigs.get(i);
            ZogUtils.printError(BottomTabMainActivity.class, bc.getButtonName() + " bc.getButtonType():" + bc.getButtonType());

            switch (bc.getButtonType()) {
                case BottomButtonType.HOME_PAGE:
                    ViewTabConfig viewTabConfigs = bc.getViewTabConfig();
                    fragments.add(ClanUtils.getChangeableHomeFragment(viewTabConfigs));
                    break;
                case BottomButtonType.FORUM_NAV:
                    fragments.add(ClanUtils.getForumNav(this));
                    break;
                case BottomButtonType.THREAD_PUBLISH:
//                    fragments.add(ClanUtils.getIndexFragment(this));
                    break;
                case BottomButtonType.MESSAGE:
                    fragments.add(new MessageFragment());
                    MESSAGE_POSITION = i;
                    break;
                case BottomButtonType.MINE:
                    MineProfileFragment mineProfileFragment = new MineProfileFragment();
                    fragments.add(mineProfileFragment);
//                    mineProfileFragment.setIbSignIn(MainTopUtils.ibSignIn);
                    break;
            }


        }

        ZogUtils.printError(BottomTabMainActivity.class, "fragments.size():" + fragments.size());

        tabholderFragment = TabholderFragment.getInstance(MainTopUtils.tvPreDo, MainTopUtils.tvDo, fragments);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(com.kit.bottomtabui.R.id.main_container, tabholderFragment)
                    .commit();
        }


//        PollingUtils.startCheckNewMessage(this, mPreferenceListener);

        AppSPUtils.setSPListener(this, mPreferenceListener);


    }


    private void setRedDot(int count) {
        tabholderFragment.getTabLayout().setNotifyText(MESSAGE_POSITION, count + "");
        ViewDisplayUtils.setBadgeCount(this, count);

    }

    public void showDialog() {
        if (getString(R.string.build_type).equals(BuildType.TEST)) {
            DialogUtils.showDialog(BottomTabMainActivity.this, getString(R.string.tips), getString(R.string.notice_test_build), getString(R.string.confirm), getString(R.string.cancel), true, true, null, null);
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        ZogUtils.printError(BottomTabMainActivity.class, "onRestart onRestart onRestart");


    }

    @Override
    protected void onResume() {
        super.onResume();
        ZogUtils.printError(BottomTabMainActivity.class, "onResume");
        ServiceUtils.startClanService(this, Action.ACTION_CHECK_NEW_MESSAGE);

        MainTopUtils.setActivityTopbar(this, mTop, R.layout.top_main, getString(R.string.forum_name), null);

        setTopbar(true, NOW_POSITION_IN_VIEWPAGER);

        homepageVariables = AppSPUtils.getHomePageConfigJson(this).getVariables();

    }

    @Override
    protected void onDestroy() {

        ZogUtils.printError(BottomTabMainActivity.class, "onDestroy onDestroy onDestroy onDestroy onDestroy onDestroy ");
        super.onDestroy();
        AppSPUtils.unsetSPListener(this, mPreferenceListener);
//        PollingUtils.stopCheckNewMessage(this, mPreferenceListener);
    }


    public static ArrayList<Fragment> getFragments() {
        return fragments;
    }

    /**
     * 更新界面
     *
     * @param t
     */
    private void updateUi(ProfileJson t) {
        if (t == null) {
            return;
        }
        ProfileVariables variables = t.getVariables();
        if (variables == null) {
            return;
        }
        mProfileVariables = variables;
        Space space = variables.getSpace();
        if (space == null) {
            return;
        }
        final String avatar = space.getAvatar();

        AppSPUtils.saveAvatarUrl(this, avatar);
        if (!StringUtils.isEmptyOrNullOrNullStr(variables.getMemberUsername()))
            AppSPUtils.saveUsername(this, variables.getMemberUsername());

    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
        int positionInViewPager = tabholderFragment.getCurrInViewPager();
        if (!ListUtils.isNullOrContainEmpty(fragments)
                && (fragments.get(positionInViewPager) instanceof WebFragment)) {
            MainTopUtils.tvTitle.setText(title);
        }

    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        setTitle(title.toString());
    }


    /**
     * 设置主页顶部的按钮
     *
     * @param isUse               是否启用
     * @param positionInViewPager
     */
    public void setTopbar(boolean isUse, int positionInViewPager) {

        int position = tabholderFragment.getTabLayout().getRealPositionInBottomUILayout(positionInViewPager);

        NOW_POSITION_IN_VIEWPAGER = positionInViewPager;

//        ArrayList<ButtonConfig> buttonConfigs = homepageVariables.getButtonConfigs();
        ButtonConfig buttonConfig = homepageVariables.getButtonConfigs().get(position);


        MainTopUtils.setMainTopbar(BottomTabMainActivity.this, buttonConfig, fragments, positionInViewPager);
    }


    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - exitTime > 2000) {
            ToastUtils.show(this, R.string.click_to_exit);
            exitTime = currentTime;
            return;
        }
        this.finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ZogUtils.printError(BottomTabMainActivity.class, "onActivityResult onActivityResult onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);


    }
}