package cn.edu.nuaa.my.base.json.article;

import cn.edu.nuaa.my.base.json.BaseJson;

/**
 * Created by tangh on 2015/8/26.
 */
public class ArticleAddFavJson extends BaseJson {
    private ArticleAddFavVariables variables;

    @Override
    public ArticleAddFavVariables getVariables() {
        return variables;
    }

    public void setVariables(ArticleAddFavVariables variables) {
        this.variables = variables;
    }
}
