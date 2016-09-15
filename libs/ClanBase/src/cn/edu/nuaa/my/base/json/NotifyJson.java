package cn.edu.nuaa.my.base.json;

import cn.edu.nuaa.my.base.json.mypm.NotifyVariables;

/**
 * 消息 会话列表
 * @author wangxi
 *
 */
public class NotifyJson extends BaseJson {
    private static final long serialVersionUID = 8924613163934673398L;
    private NotifyVariables variables;

    public NotifyVariables getVariables() {
        return variables;
    }

    public void setVariables(NotifyVariables variables) {
        this.variables = variables;
    }

}
