package cn.edu.nuaa.my.base.json.uploadavatar;

import com.youzu.android.framework.json.annotation.JSONField;
import cn.edu.nuaa.my.base.json.model.Variables;

public class UploadAvatarVariables extends Variables {

    private String uploadAvatar;

    public String getUploadAvatar() {
        return uploadAvatar;
    }

    @JSONField(name = "uploadavatar")
    public void setUploadAvatar(String uploadAvatar) {
        this.uploadAvatar = uploadAvatar;
    }
}