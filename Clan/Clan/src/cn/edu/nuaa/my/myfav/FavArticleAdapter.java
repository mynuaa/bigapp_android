package cn.edu.nuaa.my.myfav;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import cn.edu.nuaa.my.R;
import cn.edu.nuaa.my.base.json.article.ArticleFav;
import cn.edu.nuaa.my.base.json.article.ArticleFavJson;
import cn.edu.nuaa.my.base.net.ClanHttpParams;
import cn.edu.nuaa.my.base.util.StringUtils;
import cn.edu.nuaa.my.base.widget.ViewHolder;
import cn.edu.nuaa.my.base.widget.list.BaseRefreshAdapter;

public class FavArticleAdapter extends BaseRefreshAdapter<ArticleFavJson> {
	private Context context;

	public FavArticleAdapter(Context context, ClanHttpParams params) {
		super(params);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.item_fav_thread, null);
		}
		ArticleFav thread = (ArticleFav) getItem(position);
		TextView contentText = ViewHolder.get(convertView,
				R.id.content);
		TextView nameText = ViewHolder.get(convertView, R.id.name);
		CheckBox checkbox = ViewHolder.get(convertView, R.id.checkbox);

		checkbox.setVisibility(isEditable() ? View.VISIBLE : View.GONE);
		contentText.setText(StringUtils.get(thread.getTitle()));
		String timestamp = thread.getDateline();
		nameText.setText(timestamp);
		nameText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_clock, 0, 0, 0);
		nameText.setCompoundDrawablePadding((int) context.getResources().getDimension(R.dimen.margin_default_medium));
		return convertView;
	}

}