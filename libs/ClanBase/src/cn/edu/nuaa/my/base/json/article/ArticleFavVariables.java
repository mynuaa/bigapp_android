package cn.edu.nuaa.my.base.json.article;

import com.youzu.android.framework.json.annotation.JSONField;
import cn.edu.nuaa.my.base.json.model.Message;
import cn.edu.nuaa.my.base.json.model.PagedVariables;

import java.util.ArrayList;

/**
 * Created by tangh on 2015/8/26.
 */
public class ArticleFavVariables extends PagedVariables {

    private Message message;
    private ArrayList<ArticleFav> list;

    public Message getMessage() {
        return message;
    }

    @JSONField(name = "Message")
    public void setMessage(Message message) {
        this.message = message;
    }

    public void setList(ArrayList<ArticleFav> list) {
        this.list = list;
    }

    @Override
    public ArrayList<ArticleFav> getList() {
        return list;
    }
}
