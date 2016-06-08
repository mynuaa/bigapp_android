package cn.edu.nuaa.my.base.json;

import cn.edu.nuaa.my.base.json.profile.ProfileVariables;
import cn.edu.nuaa.my.base.json.profile.Space;

/**
 * 个人中心
 * @author wangxi
 *
 */
public class ProfileJson extends BaseJson {

	private static final long serialVersionUID = -9124542162087122769L;
	private ProfileVariables variables;
	private Space data;
	@Override
	public ProfileVariables getVariables() {
		return variables;
	}
	
	public void setVariables(ProfileVariables variables) {
		this.variables = variables;
	}


	public Space getData() {
		return data;
	}

	public void setData(Space data) {
		this.data = data;
	}


}
