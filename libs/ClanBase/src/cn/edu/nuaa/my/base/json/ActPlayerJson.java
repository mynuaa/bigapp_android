package cn.edu.nuaa.my.base.json;

import com.youzu.android.framework.json.annotation.JSONField;
import cn.edu.nuaa.my.base.json.act.ActPlayerVariables;

public class ActPlayerJson extends BaseJson {

    private static final long serialVersionUID = 894045894026317236L;
    @JSONField(name = "Variables")
    private ActPlayerVariables variables;

    @Override
    public ActPlayerVariables getVariables() {
        return variables;
    }

    public void setVariables(ActPlayerVariables variables) {
        this.variables = variables;
    }
}
