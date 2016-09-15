package cn.edu.nuaa.my.base.json.mypm;

import java.util.ArrayList;

import cn.edu.nuaa.my.base.json.model.PagedVariables;

/**
 * Created by laigq on 2016/9/3.
 */
public class NotifyVariables extends PagedVariables {
    private static final long serialVersionUID = -1927982129926443352L;
    private ArrayList<Notify> list;
    private String count;
    private String perpage;
    private String page;
    private String id;
    public ArrayList<Notify> getList() {
        return list;
    }
    public void setList(ArrayList<Notify> list) {
        this.list = list;
    }
    public String getCount() {
        return count;
    }
    public void setCount(String count) {
        this.count = count;
    }
    public String getPerpage() {
        return perpage;
    }
    public void setPerpage(String perpage) {
        this.perpage = perpage;
    }
    public String getPage() {
        return page;
    }
    public void setPage(String page) {
        this.page = page;
    }
}
