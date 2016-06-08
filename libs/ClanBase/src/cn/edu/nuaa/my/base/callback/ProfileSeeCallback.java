package cn.edu.nuaa.my.base.callback;

import android.support.v4.app.FragmentActivity;

import cn.edu.nuaa.my.base.json.ProfileJson;

public abstract class ProfileSeeCallback extends ProgressCallback<ProfileJson>{
    public ProfileSeeCallback(FragmentActivity activity) {
        super(activity);
    }
}
