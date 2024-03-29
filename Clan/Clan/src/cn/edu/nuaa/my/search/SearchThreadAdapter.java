package cn.edu.nuaa.my.search;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cn.edu.nuaa.my.R;
import cn.edu.nuaa.my.base.json.search.SearchThread;
import cn.edu.nuaa.my.base.json.search.SearchThreadJson;
import cn.edu.nuaa.my.base.net.ClanHttpParams;
import cn.edu.nuaa.my.base.util.ClanUtils;
import cn.edu.nuaa.my.base.util.DateUtils;
import cn.edu.nuaa.my.base.util.LoadImageUtils;
import cn.edu.nuaa.my.base.util.StringUtils;
import cn.edu.nuaa.my.base.util.view.threadandarticle.ThreadAndArticleItemUtils;
import cn.edu.nuaa.my.base.widget.ViewHolder;
import cn.edu.nuaa.my.base.widget.list.BaseRefreshAdapter;

public class SearchThreadAdapter extends BaseRefreshAdapter<SearchThreadJson> {

	private  Activity context;
//	private ArrayList<SearchThread> threads;

	public SearchThreadAdapter(Activity act, ClanHttpParams params) {
        super(params);
        this.context = act;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_search_thread, null);
        }
        TextView subjectText = ViewHolder.get(convertView, R.id.subject);
        TextView nameText = ViewHolder.get(convertView, R.id.name);

        ImageView image1 = ViewHolder.get(convertView, R.id.image1);
        ImageView image2 = ViewHolder.get(convertView, R.id.image2);
        ImageView image3 = ViewHolder.get(convertView, R.id.image3);

//        TextView tvForumName = ViewHolder.get(convertView, R.id.forum_name);
        ImageView iconImage = ViewHolder.get(convertView, R.id.icon);

        TextView viewText = ViewHolder.get(convertView, R.id.view);
        TextView replyText = ViewHolder.get(convertView, R.id.reply);
        TextView imageNumText = ViewHolder.get(convertView, R.id.image_num);
        TextView dateText = ViewHolder.get(convertView, R.id.date);
        SearchThread thread = (SearchThread)getItem(position);
        if (thread != null) {
            subjectText.setText(StringUtils.get(thread.getSubject()));
            nameText.setText(StringUtils.get(thread.getAuthor()));
//            int size = Integer.parseInt(thread.getAttachment());
//
//            PicassoUtils.display(context, image1, urls[0]);
//            image2.setVisibility(size >= 2 ? View.VISIBLE : View.GONE);
//            image3.setVisibility(size >= 3 ? View.VISIBLE : View.GONE);
//
//            if (size > 1) {
//                PicassoUtils.display(context, image2, urls[1]);
//                if (size > 2) {
//                    PicassoUtils.display(context, image3, urls[2]);
//                }
//            }


//            String forumName = StringUtils.get(thread.getForumName());
            String views = StringUtils.get(thread.getViews());
            String replies = StringUtils.get(thread.getReplies());
            dateText.setText(ClanUtils.computeThreadTime(DateUtils.getDate2Long(thread.getDateline(), "yyyy-M-dd")));

//
//            if (thread.getAttachment().equals("2")) {
//                iconImage.setVisibility(View.VISIBLE);
//            } else iconImage.setVisibility(View.GONE);


            LoadImageUtils.displayMineAvatar(context, iconImage, thread.getAvatar());

            imageNumText.setText(thread.getAttachment());
//            tvForumName.setText(forumName);
            replyText.setText(replies);
            viewText.setText(views);


            String tid = thread.getTid();
            boolean hasRead = ThreadAndArticleItemUtils.hasRead(context, tid);
            int colorRes = context.getResources().getColor(hasRead ? R.color.text_black_selected : R.color.text_black_ta_title);
            subjectText.setTextColor(colorRes);
        }
		return convertView;
	}
}
