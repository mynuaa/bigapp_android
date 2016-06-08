package cn.edu.nuaa.my.base.json;

import com.youzu.android.framework.json.annotation.JSONField;
import cn.edu.nuaa.my.base.json.blog.BlogListVariables;

/**
 * Created by wjwu on 2015/12/18.
 */
public class BlogListJson extends BaseJson {

    @JSONField(name = "Variables")
    private BlogListVariables variables;

    public BlogListVariables getVariables() {
        return variables;
    }

    public void setVariables(BlogListVariables variables) {
        this.variables = variables;
    }

}