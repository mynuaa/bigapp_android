package cn.edu.nuaa.my.base.json.search;

import java.util.ArrayList;
import java.util.List;

import com.youzu.android.framework.json.annotation.JSONField;
import cn.edu.nuaa.my.base.json.model.PagedVariables;

public class SearchUserVariables extends PagedVariables{
	private static final long serialVersionUID = 7596446775686810626L;
	@JSONField(name="user_list")
	private ArrayList<User> userList;
	
	public ArrayList<User> getUserList() {
		return userList;
	}
	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}

    @Override
    public List getList() {
        return userList;
    }
}
