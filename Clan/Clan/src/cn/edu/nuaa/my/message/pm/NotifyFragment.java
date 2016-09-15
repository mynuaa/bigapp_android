package cn.edu.nuaa.my.message.pm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.kit.utils.ZogUtils;
import com.youzu.android.framework.ViewUtils;
import com.youzu.android.framework.view.annotation.ViewInject;
import com.youzu.android.framework.view.annotation.event.OnItemClick;

import java.util.ArrayList;

import cn.edu.nuaa.my.R;
import cn.edu.nuaa.my.app.InjectDo;
import cn.edu.nuaa.my.base.EditableFragment;
import cn.edu.nuaa.my.base.callback.StringCallback;
import cn.edu.nuaa.my.base.config.Url;
import cn.edu.nuaa.my.base.json.BaseJson;
import cn.edu.nuaa.my.base.json.mypm.Notify;
import cn.edu.nuaa.my.base.net.BaseHttp;
import cn.edu.nuaa.my.base.net.ClanHttpParams;
import cn.edu.nuaa.my.base.util.AppSPUtils;
import cn.edu.nuaa.my.base.util.ClanBaseUtils;
import cn.edu.nuaa.my.base.util.ClanUtils;
import cn.edu.nuaa.my.base.util.StringUtils;
import cn.edu.nuaa.my.base.widget.list.BaseRefreshAdapter;
import cn.edu.nuaa.my.base.widget.list.OnDataSetChangedObserver;
import cn.edu.nuaa.my.base.widget.list.OnEditListener;
import cn.edu.nuaa.my.base.widget.list.RefreshListView;

public class  NotifyFragment extends EditableFragment implements OnEditListener {

    @ViewInject(value = R.id.list)
    RefreshListView mListView;


    private NotifyAdatper mAdatper;

    private static NotifyFragment fragment;
    private OnDataSetChangedObserver mObserver;


    public NotifyFragment() {
    }

    public void setObserver(OnDataSetChangedObserver mObserver) {
        this.mObserver = mObserver;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notify, container, false);
//        RefreshListView listView = (RefreshListView) view.findViewById(R.id.list);
        ViewUtils.inject(this, view);


        ClanHttpParams params = new ClanHttpParams(getActivity());
        params.addQueryStringParameter("module", "mynotelist");
        params.addQueryStringParameter("iyzmobile", "1");
        mAdatper = new NotifyAdatper(getActivity(), this, params);
        mAdatper.setOnDataSetChangedObserver(mObserver);
        mListView.setAdapter(mAdatper);
        AppSPUtils.saveNewMessage(getActivity(), 0);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public RefreshListView getListView() {
        return mListView;
    }

    @OnItemClick(R.id.list)
    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id) {
        Notify notify = (Notify) mAdatper.getItem(position);

        ZogUtils.printLog(Notify.class, "notify.getTousername():" + notify.getNote());
    }


    /**
     * 为长按单个删除准备 已经无用了
     *
     * @param id
     * @param adapter
     * @param position
     */
    @Deprecated
    private void showDialog(int id, final BaseRefreshAdapter adapter, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.is_delete);
        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Notify notify = (Notify) mAdatper.getItem(position - 1);

                doDelete(notify.getUid(), mAdatper, position);

            }
        });
        builder.setNegativeButton(R.string.cancel, null);
        Dialog dialog = builder.create();
        dialog.show();
    }


    /**
     * 当传入position,adapter的时候为单个长按删除
     *
     * @param str
     * @param adapter
     * @param position
     */
    private void doDelete(String str, final BaseRefreshAdapter adapter, final Integer position) {

        ClanHttpParams params = new ClanHttpParams(getActivity());
        params.addQueryStringParameter("iyzmobile", "1");
        params.addQueryStringParameter("module", "deletepl");

        params.addBodyParameter("deletepm_deluid", str);
        params.addBodyParameter("deletepmsubmit_btn", "true");
        params.addBodyParameter("deletesubmit", "true");

        if (!StringUtils.isEmptyOrNullOrNullStr(ClanBaseUtils.getFormhash(getActivity())))
            params.addBodyParameter("formhash", ClanBaseUtils.getFormhash(getActivity()));

        BaseHttp.post(Url.DOMAIN, params, new StringCallback(getActivity()) {
            @Override
            public void onSuccess(Context ctx, String s) {
                super.onSuccess(ctx, s);
//                BaseJson t = ClanUtils.parseObject(s, BaseJson.class);
                ClanUtils.dealMsg(getActivity(), s, "delete_pm_success", R.string.delete_success, R.string.delete_failed, this, true, true, new InjectDo<BaseJson>() {
                    @Override
                    public boolean doSuccess(BaseJson baseJson) {
                        mListView.deleteChoices();
                        if (adapter != null && position != null) {
                            ArrayList list = adapter.getData();
                            list.remove(position);
                            adapter.setData(list);
                        }
                        return true;
                    }

                    @Override
                    public boolean doFail(BaseJson baseJson, String msgVal) {
                        return true;
                    }
                });
            }

            @Override
            public void onFailed(Context cxt, int errorCode, String msg) {
                super.onFailed(getActivity(), errorCode, msg);

            }
        });
    }

    public NotifyAdatper getAdapter() {
        return mAdatper;
    }


    @Override
    public void onDelete() {

    }
    public void refresh() {
        mListView.refresh();
    }
}
