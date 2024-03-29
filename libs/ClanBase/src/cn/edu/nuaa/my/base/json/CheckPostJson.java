package cn.edu.nuaa.my.base.json;

import com.youzu.android.framework.json.annotation.JSONField;
import cn.edu.nuaa.my.base.json.checkpost.CheckPostVariables;

/**
 *
 * 主题详情
 * Created by Zhao on 15/5/5.
 */
public class CheckPostJson extends BaseJson {

    private CheckPostVariables variables;

    public CheckPostVariables getVariables() {
        return variables;
    }

    @JSONField(name = "Variables")
    public void setVariables(CheckPostVariables variables) {
        this.variables = variables;
    }
}
