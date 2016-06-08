package cn.edu.nuaa.my.base.json.homepageconfig;

import com.youzu.android.framework.json.annotation.JSONField;
import cn.edu.nuaa.my.base.json.BaseJson;

/**
 *
 * 界面的详细配置
 * 
 */
public class TitleButtonConfigJson extends BaseJson {
    private TitleButtonConfigVariables variables;


    @Override
    public TitleButtonConfigVariables getVariables() {
        return variables;
    }

    @JSONField(name = "Variables")
    public void setVariables(TitleButtonConfigVariables variables) {
        this.variables = variables;
    }
}
