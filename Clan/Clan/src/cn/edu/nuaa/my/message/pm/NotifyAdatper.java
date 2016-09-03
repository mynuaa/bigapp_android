package cn.edu.nuaa.my.message.pm;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.keyboard.utils.DefEmoticons;

import cn.edu.nuaa.my.R;
import cn.edu.nuaa.my.base.BaseFragment;
import cn.edu.nuaa.my.base.json.NotifyJson;
import cn.edu.nuaa.my.base.json.mypm.Notify;
import cn.edu.nuaa.my.base.net.ClanHttpParams;
import cn.edu.nuaa.my.base.util.StringUtils;
import cn.edu.nuaa.my.base.widget.ViewHolder;
import cn.edu.nuaa.my.base.widget.list.BaseRefreshAdapter;

public class NotifyAdatper extends BaseRefreshAdapter<NotifyJson> {

    private Context context;
    private BaseFragment fragment;

    public NotifyAdatper(Context context, ClanHttpParams params) {
        super(params);
        this.context = context;
    }


    public NotifyAdatper(Context context, BaseFragment fragment, ClanHttpParams params) {
        super(params);
        this.context = context;
        this.fragment = fragment;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_mypm, null);
        }

       Notify notify = (Notify) getItem(position);
//
//        CheckBox checkbox = ViewHolder.get(convertView, R.id.checkbox);
//        checkbox.setVisibility(isEditable() ? View.VISIBLE : View.GONE);
//
//        ImageView photoImage = ViewHolder.get(convertView, R.id.icon);
//        TextView nameText = ViewHolder.get(convertView, R.id.name);
        TextView contentText = ViewHolder.get(convertView, R.id.content);
//        contentText.setText(DefEmoticons.replaceUnicodeByEmoji(context, StringUtils.get(notify.getNote())));
        contentText.setText(DefEmoticons.replaceUnicodeByEmoji(context, StringUtils.get(notify.getNote())));

        return convertView;
    }

}
