package cn.edu.nuaa.my.base.json;

import com.youzu.android.framework.json.annotation.JSONField;
import cn.edu.nuaa.my.base.json.blog.BlogListByPersonVariables;

/**
 * Created by wjwu on 2015/12/18.
 */
public class BlogListByPersonJson extends BaseJson {

    @JSONField(name = "Variables")
    private BlogListByPersonVariables variables;

    public BlogListByPersonVariables getVariables() {
        return variables;
    }

    public void setVariables(BlogListByPersonVariables variables) {
        this.variables = variables;
    }

}