package cn.edu.nuaa.my.base.json;

import com.youzu.android.framework.json.annotation.JSONField;
import cn.edu.nuaa.my.base.json.favforum.FavForumVariables;

public class FavForumJson extends BaseJson {
	
	private static final long serialVersionUID = -2375782355111698214L;
	private FavForumVariables variables;

	@Override
	public FavForumVariables getVariables() {
		return variables;
	}

	@JSONField(name="Variables")
	public void setVariables(FavForumVariables variables) {
		this.variables = variables;
	}
	
	
}
