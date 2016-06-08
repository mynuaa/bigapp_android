package cn.edu.nuaa.my.base.json;

import cn.edu.nuaa.my.base.json.mypm.ChatVariables;

public class ChatJson extends BaseJson {
	
	private ChatVariables variables;

	@Override
	public ChatVariables getVariables() {
		return variables;
	}

	public void setVariables(ChatVariables variables) {
		this.variables = variables;
	}
}
