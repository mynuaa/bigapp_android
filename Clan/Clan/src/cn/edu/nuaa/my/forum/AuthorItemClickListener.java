package cn.edu.nuaa.my.forum;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.kit.utils.ZogUtils;
import cn.edu.nuaa.my.base.json.model.Moderator;
import cn.edu.nuaa.my.base.util.jump.JumpProfileUtils;

import java.util.ArrayList;

public class AuthorItemClickListener implements OnItemClickListener {

    private ArrayList<Moderator> moderators;
    private FragmentActivity activity;

    public AuthorItemClickListener(FragmentActivity activity,
                                   ArrayList<Moderator> moderators) {
        this.activity = activity;
        this.moderators = moderators;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, final View view,
                            int position, long id) {
        try {
            final Moderator moderator = moderators.get(position);
            final String uid = moderator.getUid();
            JumpProfileUtils.gotoProfilePage(activity, uid);

        } catch (Exception e) {
            ZogUtils.showException(e);
        }
    }

}
