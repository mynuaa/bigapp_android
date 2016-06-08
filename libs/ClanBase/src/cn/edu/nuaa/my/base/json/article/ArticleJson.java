package cn.edu.nuaa.my.base.json.article;

import com.youzu.android.framework.json.annotation.JSONField;
import cn.edu.nuaa.my.base.json.BaseJson;

/**
 * Created by tangh on 2015/8/26.
 */
public class ArticleJson extends BaseJson {
    private ArticleVariables variables;

    @Override
    public ArticleVariables getVariables() {
        return variables;
    }

    @JSONField(name = "Variables")
    public void setVariables(ArticleVariables variables) {
        this.variables = variables;
    }
}
