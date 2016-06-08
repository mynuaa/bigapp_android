package cn.edu.nuaa.my.base.json.favforum;

import cn.edu.nuaa.my.base.json.model.Variables;

public class AddFavForumVariables extends Variables{
	private static final long serialVersionUID = -4765381150075849265L;
	private String favid;
	public String getFavid() {
		return favid;
	}
	public void setFavid(String favid) {
		this.favid = favid;
	}
}
