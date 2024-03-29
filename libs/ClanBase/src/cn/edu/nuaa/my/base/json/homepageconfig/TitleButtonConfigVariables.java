package cn.edu.nuaa.my.base.json.homepageconfig;

import com.youzu.android.framework.json.annotation.JSONField;
import cn.edu.nuaa.my.base.json.model.Variables;

import java.io.Serializable;

/**
 *
 * 界面的详细配置
 * 
 */
public class TitleButtonConfigVariables extends Variables implements Serializable{

    private ViewTabConfig viewTabConfig;

    public ViewTabConfig getViewTabConfig() {
        return viewTabConfig;
    }

    @JSONField(name = "tab_cfg")
    public void setViewTabConfig(ViewTabConfig viewTabConfig) {
        this.viewTabConfig = viewTabConfig;
    }
}
