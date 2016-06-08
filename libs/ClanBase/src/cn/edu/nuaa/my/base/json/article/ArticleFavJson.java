package cn.edu.nuaa.my.base.json.article;

import cn.edu.nuaa.my.base.json.BaseJson;

/**
 * Created by tangh on 2015/8/26.
 */
public class ArticleFavJson extends BaseJson {
    private ArticleFavVariables variables;

    @Override
    public ArticleFavVariables getVariables() {
        return variables;
    }

    public void setVariables(ArticleFavVariables variables) {
        this.variables = variables;
    }
}
