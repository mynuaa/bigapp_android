package cn.edu.nuaa.my.message.pm;

import android.os.Bundle;
import android.view.View;

import com.youzu.android.framework.view.annotation.ContentView;
import com.youzu.android.framework.view.annotation.ViewInject;
import cn.edu.nuaa.my.R;
import cn.edu.nuaa.my.base.EditableActivity;
import cn.edu.nuaa.my.base.widget.list.RefreshListView;

/**
 * 消息
 *
 * @author wangxi
 */

@ContentView(R.layout.activity_mypm)
public class MyPMActivity extends EditableActivity {

    @ViewInject(value = R.id.list)
    RefreshListView mListView;

    private MyPMAdatper mAdapter;
    MyPMFragment myPMFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListView.setVisibility(View.GONE);

        myPMFragment = new MyPMFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.repalce_frame, myPMFragment).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

        mAdapter = myPMFragment.getAdapter();
        mAdapter.setOnDataSetChangedObserver(mObserver);
    }

    @Override
    public RefreshListView getListView() {
        mListView = myPMFragment.getListView();
        return mListView;
    }


}
