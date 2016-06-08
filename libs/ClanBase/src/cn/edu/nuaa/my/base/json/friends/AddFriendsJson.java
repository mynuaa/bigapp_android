package cn.edu.nuaa.my.base.json.friends;

import cn.edu.nuaa.my.base.json.BaseJson;

/**
 * Created by tangh on 2015/7/15.
 */
public class AddFriendsJson extends BaseJson {
    private AddFriendsVariables variables;

    @Override
    public AddFriendsVariables getVariables() {
        return variables;
    }

    public void setVariables(AddFriendsVariables variables) {
        this.variables = variables;
    }
}
