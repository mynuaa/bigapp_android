package cn.edu.nuaa.my.base.json.threadview.rate;

import com.youzu.android.framework.json.annotation.JSONField;
import cn.edu.nuaa.my.base.json.BaseJson;


/**
 * 点评帖子前置检查返回值
 */
public class RateCheckJson extends BaseJson {
	private RateCheckVariables variables;

	public RateCheckVariables getVariables() {
		return variables;
	}

	@JSONField(name="Variables")
	public void setVariables(RateCheckVariables variables) {
		this.variables = variables;
	}
}
