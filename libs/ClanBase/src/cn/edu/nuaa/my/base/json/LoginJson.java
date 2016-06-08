package cn.edu.nuaa.my.base.json;

import com.youzu.android.framework.json.annotation.JSONField;
import cn.edu.nuaa.my.base.json.login.LoginVariables;
import cn.edu.nuaa.my.base.json.profile.Space;

public class LoginJson extends BaseJson {
	
	private static final long serialVersionUID = 8143901382248306122L;
	private LoginVariables variables;
	private int errorCode;
	private String errorMsg;
	private Space data;

	public int getErrorCode() {
		return errorCode;
	}

	@JSONField(name = "error_code")
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	@JSONField(name = "error_msg")
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public LoginVariables getVariables() {
		return variables;
	}
	@JSONField(name="Variables")
	public void setVariables(LoginVariables variables) {
		this.variables = variables;
	}


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Space getData() {
		return data;
	}

	public void setData(Space data) {
		this.data = data;
	}
}
