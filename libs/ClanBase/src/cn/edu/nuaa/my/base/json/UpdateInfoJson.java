package cn.edu.nuaa.my.base.json;

import cn.edu.nuaa.my.base.json.update.AutoUpdateInfo;

public class UpdateInfoJson extends BaseResponse {
	
	private AutoUpdateInfo data;

	@Override
	public AutoUpdateInfo getData() {
		return data;
	}

	public void setData(AutoUpdateInfo data) {
		this.data = data;
	}
}
