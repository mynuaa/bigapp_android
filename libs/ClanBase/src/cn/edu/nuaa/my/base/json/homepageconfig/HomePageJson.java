package cn.edu.nuaa.my.base.json.homepageconfig;

import com.youzu.android.framework.json.annotation.JSONField;
import cn.edu.nuaa.my.base.json.BaseJson;

/**
 * Created by tangh on 2015/9/22.
 */
public class HomePageJson extends BaseJson {
    private HomepageVariables variables;


    @Override
    public HomepageVariables getVariables() {
        return variables;
    }

    @JSONField(name = "Variables")
    public void setVariables(HomepageVariables variables) {
        this.variables = variables;
    }
}
