package cn.edu.nuaa.my.main.base.activity;

import android.os.Bundle;

import com.kit.utils.DialogUtils;
import cn.edu.nuaa.my.R;
import cn.edu.nuaa.my.app.config.BuildType;
import cn.edu.nuaa.my.base.BaseActivity;
import cn.edu.nuaa.my.base.net.LoadEmojiUtils;
import cn.edu.nuaa.my.base.util.theme.ThemeUtils;

/**
 * Created by Zhao on 15/9/15.
 */
public class BaseMainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ThemeUtils.printAppStyle(this);

        LoadEmojiUtils.startLoadSmiley(this);

        showDialog();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void showDialog() {
        if (getString(R.string.build_type).equals(BuildType.TEST)) {
            DialogUtils.showDialog(this, getString(R.string.tips), getString(R.string.notice_test_build), getString(R.string.confirm), getString(R.string.cancel), true, true, null, null);
        }
    }







}
