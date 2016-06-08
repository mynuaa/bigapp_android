package cn.edu.nuaa.my.base.util.view;

import android.content.Context;

import com.youzu.android.framework.JsonUtils;
import cn.edu.nuaa.my.base.json.ProfileJson;
import cn.edu.nuaa.my.base.json.profile.AdminGroup;
import cn.edu.nuaa.my.base.json.profile.Space;
import cn.edu.nuaa.my.base.util.AppSPUtils;
import cn.edu.nuaa.my.base.util.StringUtils;

/**
 * Created by Zhao on 15/10/19.
 */
public class ProfileUtils {

    /**
     * 从保存的sharedperference中获取用户信息
     *
     * @param context
     */
    public static ProfileJson getProfile(final Context context) {

        String ps = AppSPUtils.getProfile(context);
        if (!StringUtils.isEmptyOrNullOrNullStr(ps)) {
            ProfileJson profileJson = JsonUtils.parseObject(ps, ProfileJson.class);
            return profileJson;
        }
        return null;
    }


    /**
     * 获取分组
     * @param space
     * @return
     */
    public static String getGroupName(Space space) {
        String groupName = "";
        AdminGroup group = space.getGroup();
        if (group != null) {
            groupName = group.getGrouptitle();
        }
        return StringUtils.get(groupName);
    }

}
