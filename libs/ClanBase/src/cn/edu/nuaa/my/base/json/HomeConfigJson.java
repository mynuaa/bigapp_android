package cn.edu.nuaa.my.base.json;

import com.youzu.android.framework.json.annotation.JSONField;
import cn.edu.nuaa.my.base.json.homeconfig.HomeConfigVariables;

/**
 * 主题详情
 * Created by Zhao on 15/5/5.
 */
public class HomeConfigJson {

    private HomeConfigVariables variables;

    public HomeConfigVariables getVariables() {
        return variables;
    }

    @JSONField(name = "Variables")
    public void setVariables(HomeConfigVariables variables) {
        this.variables = variables;
    }
}
