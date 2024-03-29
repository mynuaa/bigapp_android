package cn.edu.nuaa.my.base.json.friends;

import com.youzu.android.framework.json.annotation.JSONField;
import cn.edu.nuaa.my.base.json.model.Variables;

/**
 * Created by tangh on 2015/7/15.
 */
public class AddFriendsVariables extends Variables {

    private String  status;
    @JSONField(name = "show_message")
    private String showMessage;
    private String groups;

    private String messageval;

    public String getMessageval() {
        return messageval;
    }

    public void setMessageval(String messageval) {
        this.messageval = messageval;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShowMessage() {
        return showMessage;
    }

    public void setShowMessage(String showMessage) {
        this.showMessage = showMessage;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }
}
