package cn.edu.nuaa.my.base.json.search;

import java.util.ArrayList;
import java.util.List;

import com.youzu.android.framework.json.annotation.JSONField;
import cn.edu.nuaa.my.base.json.model.PagedVariables;

public class SearchForumVariables extends PagedVariables{
	private static final long serialVersionUID = 7596446775686810626L;
	@JSONField(name="forum_list")
	private ArrayList<SearchForum> forumList;
	
	public ArrayList<SearchForum> getForumList() {
		return forumList;
	}
	public void setForumList(ArrayList<SearchForum> forumList) {
		this.forumList = forumList;
	}

    @Override
    public List getList() {
        return forumList;
    }
}
