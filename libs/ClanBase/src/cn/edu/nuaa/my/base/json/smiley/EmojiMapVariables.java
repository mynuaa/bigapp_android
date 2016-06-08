package cn.edu.nuaa.my.base.json.smiley;

import cn.edu.nuaa.my.base.json.model.Variables;

import java.util.ArrayList;

/**
 * Created by tangh on 2015/8/10.
 */
public class EmojiMapVariables extends Variables {
    private ArrayList<SmiliesItem> smilies;


    public ArrayList<SmiliesItem> getSmilies() {
        return smilies;
    }

    public void setSmilies(ArrayList<SmiliesItem> smilies) {
        this.smilies = smilies;
    }
}
