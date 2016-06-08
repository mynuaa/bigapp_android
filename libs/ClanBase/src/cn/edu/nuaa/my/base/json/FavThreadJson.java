package cn.edu.nuaa.my.base.json;

import cn.edu.nuaa.my.base.json.favthread.FavThreadVariables;

public class FavThreadJson extends BaseJson {
	
	private static final long serialVersionUID = -2375782355111698214L;
	private FavThreadVariables variables;

	public FavThreadVariables getVariables() {
		return variables;
	}

	public void setVariables(FavThreadVariables variables) {
		this.variables = variables;
	}
	
	
}
