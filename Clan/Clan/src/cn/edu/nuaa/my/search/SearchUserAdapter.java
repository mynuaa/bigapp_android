package cn.edu.nuaa.my.search;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kit.imagelib.widget.imageview.circleimageview.CircleImageView;
import cn.edu.nuaa.my.R;
import cn.edu.nuaa.my.base.json.mypm.Mypm;
import cn.edu.nuaa.my.base.json.search.SearchUserJson;
import cn.edu.nuaa.my.base.json.search.User;
import cn.edu.nuaa.my.base.net.ClanHttpParams;
import cn.edu.nuaa.my.base.util.AppSPUtils;
import cn.edu.nuaa.my.base.util.LoadImageUtils;
import cn.edu.nuaa.my.base.util.jump.JumpChatUtils;
import cn.edu.nuaa.my.base.widget.ViewHolder;
import cn.edu.nuaa.my.base.widget.list.BaseRefreshAdapter;

import java.util.ArrayList;

public class SearchUserAdapter extends BaseRefreshAdapter<SearchUserJson> {
	public FragmentActivity act;
	ArrayList<User> users;
	public SearchUserAdapter(FragmentActivity act,ClanHttpParams params) {
        super(params);
		this.act = act;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
            convertView = View.inflate(act, R.layout.item_search_user, null);
        }
        TextView userName = ViewHolder.get(convertView, R.id.userName);
        TextView userPosition = ViewHolder.get(convertView, R.id.userPosition);
        TextView sendMessage = ViewHolder.get(convertView, R.id.sendMessage);
        CircleImageView avatar = ViewHolder.get(convertView, R.id.avatar);

		final User user=(User)getItem(position);
		userName.setText(user.getUsername());
		userPosition.setText(user.getGroupname());
        LoadImageUtils.displayNoHolder(act, avatar, user.getAvatar(), R.drawable.ic_profile_nologin_default);
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mypm mypm = new Mypm();
                mypm.setMsgfromidAvatar(AppSPUtils.getAvatartUrl(act));
                mypm.setMsgtoidAvatar(user.getAvatar());
                mypm.setTousername(user.getUsername());
                mypm.setTouid(user.getUid());

                JumpChatUtils.gotoChat(act, mypm);

            }
        });
		return convertView;
	}
}
