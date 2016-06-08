package cn.edu.nuaa.my.profile.thread;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cn.edu.nuaa.my.R;
import cn.edu.nuaa.my.base.json.MyReplyJson;
import cn.edu.nuaa.my.base.json.mythread2.Detail;
import cn.edu.nuaa.my.base.json.mythread2.MyThread;
import cn.edu.nuaa.my.base.json.mythread2.Reply;
import cn.edu.nuaa.my.base.net.ClanHttpParams;
import cn.edu.nuaa.my.base.util.LoadImageUtils;
import cn.edu.nuaa.my.base.util.StringUtils;
import cn.edu.nuaa.my.base.util.view.threadandarticle.ThreadAndArticleItemUtils;
import cn.edu.nuaa.my.base.widget.ViewHolder;
import cn.edu.nuaa.my.base.widget.list.BaseRefreshAdapter;
import cn.edu.nuaa.my.base.json.forumdisplay.Thread;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyReplyAdapter extends BaseRefreshAdapter<MyReplyJson> {
		
		private Context context;
		public MyReplyAdapter(Context context, ClanHttpParams params) {
			super(params);
			this.context = context;
		}

		@Override
		protected List getData(MyReplyJson json) {
			if (json == null) {
				return null;
			}
			List<MyThread> threads = json.getData();
			if (threads == null || threads.size() < 1) {
				return Collections.EMPTY_LIST;
			}

			ArrayList<Reply> replys = new ArrayList<Reply>();
			for (MyThread thread : threads) {
				List<Detail> details = thread.getDetails();
				if (details == null || details.size() < 1) {
					continue;
				}
				Reply reply = null;
				for (Detail detail : details) {
					reply = new Reply();
					reply.setAvatar(json.getAvatar());
					reply.setDetail(detail);
					reply.setThread(thread);

					if (reply != null) {
						replys.add(reply);
					}
				}
			}
			return replys;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = View.inflate(context, R.layout.item_my_reply, null);
			}
			Reply reply = (Reply) getItem(position);
			Thread thread = reply.getThread();
			Detail detail = reply.getDetail();
			ImageView photoImage = ViewHolder.get(convertView, R.id.icon);
			TextView nameText = ViewHolder.get(convertView, R.id.name);
			TextView dateText = ViewHolder.get(convertView, R.id.date);
			TextView contentText = ViewHolder.get(convertView, R.id.content);
			TextView subjectText = ViewHolder.get(convertView, R.id.subject);
			TextView forumText = ViewHolder.get(convertView, R.id.forum);
			TextView viewText = ViewHolder.get(convertView, R.id.view);
			TextView replyText = ViewHolder.get(convertView, R.id.reply);

			nameText.setText(StringUtils.get(detail.getAuthor()));
			dateText.setText(StringUtils.get(detail.getDateline()));
			contentText.setText(StringUtils.get(detail.getMessage()));
			subjectText.setText(StringUtils.get(thread.getSubject()));
			forumText.setText(StringUtils.get(thread.getForumName()));
			viewText.setText(StringUtils.get(thread.getViews()));
			replyText.setText(StringUtils.get(thread.getReplies()));

			LoadImageUtils.displayMineAvatar(context, photoImage, reply.getAvatar());
			String tid = thread.getTid();
			boolean hasRead = ThreadAndArticleItemUtils.hasRead(context, tid);
			int colorRes = context.getResources().getColor(hasRead?R.color.text_black_selected:R.color.text_black_ta_title);
			nameText.setTextColor(colorRes);
			
			return convertView;

		}

	}