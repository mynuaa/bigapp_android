package cn.edu.nuaa.my.base.json;

import com.youzu.android.framework.json.annotation.JSONField;
import cn.edu.nuaa.my.base.json.login.QQLoginVariables;

public class QQLoginJson extends BaseJson {
	
	private static final long serialVersionUID = -2375782355111698214L;
	private QQLoginVariables variables;

	@Override
	public QQLoginVariables getVariables() {
		return variables;
	}

	@JSONField(name="Variables")
	public void setVariables(QQLoginVariables variables) {
		this.variables = variables;
	}
	
	
}
