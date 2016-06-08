package cn.edu.nuaa.my.act.apply;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;

import com.kit.utils.FragmentUtils;
import com.kit.utils.ZogUtils;
import com.kit.utils.intentutils.BundleData;
import com.kit.utils.intentutils.IntentUtils;
import com.youzu.android.framework.view.annotation.ContentView;
import cn.edu.nuaa.my.R;
import cn.edu.nuaa.my.app.constant.Key;
import cn.edu.nuaa.my.base.BaseActivity;
import cn.edu.nuaa.my.base.json.threadview.ThreadDetailJson;


/**
 *  参加活动
 */
@ContentView(R.layout.activity_fragment_replace)
public class ActApplyActivity extends BaseActivity implements Handler.Callback {

    ThreadDetailJson detail;

    @Override
    public boolean handleMessage(Message msg) {
        int what = msg.what;

        switch (what) {

        }
        return false;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActApplyFragment actApplyFragment = new ActApplyFragment();
        BundleData bundleData = new BundleData();
        bundleData.put(Key.CLAN_DATA, detail.getVariables().getSpecialActivity());
        ZogUtils.printObj(ActApplyActivity.class, detail, "SpecialActivity");

        FragmentUtils.replace(getSupportFragmentManager(), R.id.repalce, actApplyFragment, bundleData);
    }


    @Override
    protected boolean getExtra() {
        BundleData bundleData = IntentUtils.getData(getIntent());
        ZogUtils.printObj(ActApplyActivity.class, bundleData, "getExtra");
        detail = bundleData.getObject(Key.CLAN_DATA, ThreadDetailJson.class);
        ZogUtils.printObj(ActApplyActivity.class, detail, "detail");
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_act_apply, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_commit:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
