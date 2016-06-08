package cn.edu.nuaa.my.base.json;

import com.youzu.android.framework.json.annotation.JSONField;
import cn.edu.nuaa.my.base.json.uploadavatar.UploadAvatarVariables;

public class UploadAvatarJson extends BaseJson {
	
	private static final long serialVersionUID = -2375782355111698214L;
	private UploadAvatarVariables variables;

	@Override
	public UploadAvatarVariables getVariables() {
		return variables;
	}

	@JSONField(name="Variables")
	public void setVariables(UploadAvatarVariables variables) {
		this.variables = variables;
	}

}
