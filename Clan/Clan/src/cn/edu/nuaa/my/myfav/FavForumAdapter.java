package cn.edu.nuaa.my.myfav;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.kit.utils.ZogUtils;
import cn.edu.nuaa.my.R;
import cn.edu.nuaa.my.base.json.FavForumJson;
import cn.edu.nuaa.my.base.json.favforum.Forum;
import cn.edu.nuaa.my.base.net.ClanHttpParams;
import cn.edu.nuaa.my.base.util.LoadImageUtils;
import cn.edu.nuaa.my.base.util.StringUtils;
import cn.edu.nuaa.my.base.widget.ViewHolder;
import cn.edu.nuaa.my.base.widget.list.BaseRefreshAdapter;
import cn.edu.nuaa.my.main.base.forumnav.ForumnavFragment;

public class FavForumAdapter extends BaseRefreshAdapter<FavForumJson> {

	private Context context;
	public FavForumAdapter(Context context, ClanHttpParams params) {
		super(params);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.item_fav_forum, null);
		}

		TextView forumName = ViewHolder.get(convertView, R.id.forum_name);
		TextView tvThreadNum = ViewHolder.get(convertView, R.id.tvThreadNum);
		TextView tvPostNum = ViewHolder.get(convertView, R.id.tvPostNum);
		TextView countText = ViewHolder.get(convertView, R.id.count);

		ImageView iconView = ViewHolder.get(convertView, R.id.icon);
		CheckBox checkbox = ViewHolder.get(convertView, R.id.checkbox);



		Forum forum = (Forum) getItem(position);

		String icon = forum.getIcon();
//                    if (!TextUtils.isEmpty(icon)) {
//                        BitmapUtils.display(iconView, icon, R.drawable.ic_forum_default);
//                    }

		forumName.setText(forum.getName());
		tvThreadNum.setText(forum.getThreads());
		tvPostNum.setText(forum.getPosts());

		ZogUtils.printError(ForumnavFragment.class, "icon::::::::" + icon);

		LoadImageUtils.displayNoHolder(context, iconView, icon, R.drawable.ic_forum_default);
		String numTodayPost = context.getString(R.string.num_today_post, StringUtils.get(forum.getTodayposts()));
		countText.setText(numTodayPost);

		String numThread = context.getString(R.string.num_thread, StringUtils.get(forum.getThreads()));
		tvThreadNum.setText(numThread);

		String numPost = context.getString(R.string.num_post, StringUtils.get(forum.getPosts()));
		tvPostNum.setText(numPost);

		checkbox.setVisibility(isEditable() ? View.VISIBLE : View.GONE);


		return convertView;

	}
}