package cn.edu.nuaa.my.search;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cn.edu.nuaa.my.R;
import cn.edu.nuaa.my.base.json.search.SearchForum;
import cn.edu.nuaa.my.base.json.search.SearchForumJson;
import cn.edu.nuaa.my.base.net.ClanHttpParams;
import cn.edu.nuaa.my.base.util.LoadImageUtils;
import cn.edu.nuaa.my.base.util.StringUtils;
import cn.edu.nuaa.my.base.widget.ViewHolder;
import cn.edu.nuaa.my.base.widget.list.BaseRefreshAdapter;

import java.util.ArrayList;

public class SearchForumAdapter extends BaseRefreshAdapter<SearchForumJson> {
    private Activity act;
    private ArrayList<SearchForum> forums;

    public SearchForumAdapter(Activity act,ClanHttpParams params) {
        super(params);this.act = act;
    }

//    public void setData(SearchForumJson searchForumJson) {
//        if (searchForumJson!=null&searchForumJson.getVariables() != null) {
//            forums = searchForumJson.getVariables().getForumList();
//        }else{
//            forums=null;
//        }
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public int getCount() {
//        if (forums == null) {
//            return 0;
//        }
//        return forums.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SearchForum forum =(SearchForum) getItem(position);
        if (convertView == null) {
            convertView = View.inflate(act, R.layout.item_forum_child, null);
        }
        TextView forumName = ViewHolder.get(convertView, R.id.forum_name);
        TextView tvThreadNum = ViewHolder.get(convertView, R.id.tvThreadNum);
        TextView tvPostNum = ViewHolder.get(convertView, R.id.tvPostNum);
        TextView countText = ViewHolder.get(convertView, R.id.count);
        ImageView iconView = ViewHolder.get(convertView, R.id.icon);


        forumName.setText(forum.getName());
        tvThreadNum.setText(forum.getThreads());
        tvPostNum.setText(forum.getPosts());
        LoadImageUtils.displayNoHolder(act, iconView, forum.getIcon(), R.drawable.ic_forum_default);

        String numTodayPost = act.getString(R.string.num_today_post, StringUtils.get(forum.getTodayposts()));
        countText.setText(numTodayPost);

        String numThread = act.getString(R.string.num_thread, StringUtils.get(forum.getThreads()));
        tvThreadNum.setText(numThread);

        String numPost = act.getString(R.string.num_post, StringUtils.get(forum.getPosts()));
        tvPostNum.setText(numPost);

        return convertView;
    }
}
