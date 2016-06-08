package cn.edu.nuaa.my.base.json;

import com.youzu.android.framework.json.annotation.JSONField;
import cn.edu.nuaa.my.base.json.upload.UploadVariables;

public class UploadJson extends BaseJson {
	
	private static final long serialVersionUID = -2375782355111698214L;
	private UploadVariables variables;

	@Override
	public UploadVariables getVariables() {
		return variables;
	}

	@JSONField(name="Variables")
	public void setVariables(UploadVariables variables) {
		this.variables = variables;
	}
	
	
}
