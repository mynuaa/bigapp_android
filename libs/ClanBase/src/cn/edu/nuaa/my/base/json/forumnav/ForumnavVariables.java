package cn.edu.nuaa.my.base.json.forumnav;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nuaa.my.base.json.model.PagedVariables;

public class ForumnavVariables extends PagedVariables {
	private static final long serialVersionUID = -1168535909216278892L;
	private ArrayList<NavForum> forums;

	public ArrayList<NavForum> getForums() {
		return forums;
	}

	public void setForums(ArrayList<NavForum> forums) {
		this.forums = forums;
	}

	@Override
	public List getList() {
		return forums;
	}
	
}
