package cn.edu.nuaa.my.base.json.signin;

import cn.edu.nuaa.my.base.json.BaseJson;

/**
 * Created by tangh on 2015/8/11.
 */
public class SignInJson extends BaseJson {
 private SignInVariables2 Variables;

    @Override
    public SignInVariables2 getVariables() {
        return Variables;
    }

    public void setVariables(SignInVariables2 variables) {
        Variables = variables;
    }
}
