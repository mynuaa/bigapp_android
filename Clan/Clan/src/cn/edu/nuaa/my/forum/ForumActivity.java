package cn.edu.nuaa.my.forum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;

import com.youzu.android.framework.view.annotation.ContentView;
import cn.edu.nuaa.my.R;
import cn.edu.nuaa.my.app.constant.Key;
import cn.edu.nuaa.my.base.BaseActivity;
import cn.edu.nuaa.my.base.json.favforum.Forum;
import cn.edu.nuaa.my.base.json.forumnav.NavForum;
import cn.edu.nuaa.my.base.util.StringUtils;
import cn.edu.nuaa.my.base.util.theme.ThemeUtils;


/**
 * 版块页面
 *
 * @author wangxi
 */
@ContentView(R.layout.activity_forum)
public class ForumActivity extends BaseActivity {

    private NavForum mNavForum;
    private Forum mFavForum;
    private FragmentTransaction fragmentTransaction;
    private ForumFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        try {
            mNavForum = (NavForum) intent.getSerializableExtra(Key.KEY_FORUM);
        } catch (Exception e) {
            mFavForum = (cn.edu.nuaa.my.base.json.favforum.Forum) intent.getSerializableExtra(Key.KEY_FORUM);
            mNavForum = mFavForum.toNavForum();
        }

        setTitle(StringUtils.get(mNavForum.getName()));

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragment = new ForumFragment();
        fragmentTransaction.add(R.id.frame, fragment).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_forum, menu);
        MenuItem item = menu.findItem(R.id.action_more);
        ForumMoreActionProvider provider = (ForumMoreActionProvider) MenuItemCompat.getActionProvider(item);
        provider.setCallback(new ForumMoreActionProvider.MoreActionProviderCallback() {
            @Override
            public void doReply() {
                fragment.gotoNewThread();
            }

            @Override
            public void doCreateAct() {
                fragment.gotoActPublish();
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ThemeUtils.initResource(this);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fragment.onActivityResult(requestCode, resultCode, data);
    }
}
