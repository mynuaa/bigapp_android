package cn.edu.nuaa.my.base.json.friends;

import cn.edu.nuaa.my.base.json.search.User;

/**
 * Created by tangh on 2015/7/15.
 */
public class Friends extends User {

    private String isfriends ;

    public String getIsfriends() {
        return isfriends;
    }

    public void setIsfriends(String isfriends) {
        this.isfriends = isfriends;
    }

    public boolean isFriends(){
        return "1".equals(isfriends);
    }

}
