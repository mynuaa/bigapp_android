package cn.edu.nuaa.my.base.json.threadview;

import com.youzu.android.framework.json.annotation.JSONField;
import cn.edu.nuaa.my.base.json.BaseJson;

/**
 *
 * 主题详情
 * Created by Zhao on 15/5/5.
 */
public class ThreadDetailJson extends BaseJson {

    private ThreadDetailVariables variables;

    public ThreadDetailVariables getVariables() {
        return variables;
    }

    @JSONField(name = "Variables")
    public void setVariables(ThreadDetailVariables variables) {
        this.variables = variables;
    }
}
