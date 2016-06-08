package cn.edu.nuaa.my.base.json.config.content;

import cn.edu.nuaa.my.base.json.BaseResponse;

/**
 * Created by Zhao on 15/6/16.
 */
public class ContentConfigJson extends BaseResponse {


    private ContentConfig config;

    public ContentConfig getConfig() {
        return config;
    }

    public void setConfig(ContentConfig config) {
        this.config = config;
    }
}
