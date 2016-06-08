//package cn.edu.nuaa.my.main.qqstyle;
//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.youzu.android.framework.ViewUtils;
//import com.youzu.android.framework.view.annotation.event.OnItemClick;
//import cn.edu.nuaa.my.R;
//import cn.edu.nuaa.my.base.BaseFragment;
//import cn.edu.nuaa.my.base.json.threadview.ThreadJson;
//import cn.edu.nuaa.my.base.json.forum.Thread;
//import cn.edu.nuaa.my.base.net.ClanHttpParams;
//import cn.edu.nuaa.my.base.util.LoadImageUtils;
//import cn.edu.nuaa.my.base.util.StringUtils;
//import cn.edu.nuaa.my.base.util.jump.JumpThreadUtils;
//import cn.edu.nuaa.my.base.util.view.threadandarticle.ThreadAndArticleItemUtils;
//import cn.edu.nuaa.my.base.widget.ViewHolder;
//import cn.edu.nuaa.my.base.widget.list.BaseRefreshAdapter;
//import cn.edu.nuaa.my.base.widget.list.RefreshListView;
//
///**
// * 热点
// *
// * @author wangxi
// */
//public class HotThreadFragment extends BaseFragment {
//    private HotThreadAdapter mAdapter;
//    private RefreshListView mListView;
//    private OnEmptyDataListener mListener;
//
//    private static HotThreadFragment fragment;
//
//    public static HotThreadFragment getInstance(OnEmptyDataListener listener) {
//        if (fragment == null) {
//            fragment = new HotThreadFragment(listener);
//        }
//        return fragment;
//    }
//
//    @SuppressLint("ValidFragment")
//    public HotThreadFragment(OnEmptyDataListener listener) {
//        mListener = listener;
//    }
//
//    public HotThreadFragment() {
//    }
//
//
//    public void setOnEmptyDataListener(OnEmptyDataListener listener) {
//        mListener = listener;
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        mListView = (RefreshListView) inflater.inflate(R.layout.fragment_list, container, false);
//        ViewUtils.inject(this, mListView);
//        ClanHttpParams params = new ClanHttpParams(getActivity());
//        params.addQueryStringParameter("module", "hotthread");
//        HotThreadAdapter adapter = new HotThreadAdapter(params);
//        mListView.setAdapter(adapter);
//        mAdapter = adapter;
//        return mListView;
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        mAdapter.notifyDataSetChanged();
//    }
//
//    @OnItemClick(R.id.list)
//    public void itemClick(AdapterView<?> parent, View view, int position, long id) {
//        Thread thread = (Thread) mAdapter.getItem(position);
////        ClanUtils.showDetail(getActivity(), thread.getTid());
//        JumpThreadUtils.gotoThreadDetail(getActivity(), thread.getTid());
//    }
//
//
//    private class HotThreadAdapter extends BaseRefreshAdapter<ThreadJson> {
//        public HotThreadAdapter(ClanHttpParams params) {
//            super(params);
//        }
//
//        @Override
//        protected void loadSuccess(int page, ThreadJson result) {
//            super.loadSuccess(page, result);
//            if (mListener != null && page <= 1 && mData.isEmpty()) {
//                mListener.onEmpty();
//            }
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            if (convertView == null) {
//                convertView = View.inflate(getActivity(), R.layout.item_hot_thread, null);
//            }
//            TextView subjectText = ViewHolder.get(convertView, R.id.subject);
//            TextView authorText = ViewHolder.get(convertView, R.id.author);
//            ImageView photoImage = ViewHolder.get(convertView, R.id.photo);
//            TextView num = ViewHolder.get(convertView, R.id.num);
//            TextView tvForumName = ViewHolder.get(convertView, R.id.forum_name);
//            ImageView iconImage = ViewHolder.get(convertView, R.id.icon);
//
//
//            Thread thread = (Thread) getItem(position);
//            subjectText.setText(StringUtils.get(thread.getSubject()));
//            authorText.setText(StringUtils.get(thread.getAuthor()));
//            LoadImageUtils.displayMineAvatar(getActivity(), photoImage, thread.getAvatar());
//
//            String forumName = StringUtils.get("forum_name");
//            String views = StringUtils.get(thread.getViews());
//            String replies = StringUtils.get(thread.getReplies());
//
//            if (thread.getAttachment().equals("2")) {
//                iconImage.setVisibility(View.VISIBLE);
//            } else iconImage.setVisibility(View.GONE);
//
//            tvForumName.setText(forumName);
//            num.setText(replies + "/" + views);
//
//
//            String tid = thread.getTid();
//            boolean hasRead = ThreadAndArticleItemUtils.hasRead(getActivity(), tid);
//            int colorRes = getActivity().getResources().getColor(hasRead ? R.color.text_black_selected : R.color.text_black_ta_title);
//            subjectText.setTextColor(colorRes);
//
//            return convertView;
//        }
//
//    }
//
//
//}
