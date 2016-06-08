package cn.edu.nuaa.my.base.json.act;

import cn.edu.nuaa.my.base.json.model.PagedVariables;

import java.util.ArrayList;
import java.util.List;

public class ActPlayerVariables extends PagedVariables {
    private static final long serialVersionUID = -1168535909216278892L;
    private ArrayList<ActPlayer> applylist;

    public ArrayList<ActPlayer> getApplylist() {
        return applylist;
    }

    public void setApplylist(ArrayList<ActPlayer> applylist) {
        this.applylist = applylist;
    }

    @Override
    public List getList() {
        return applylist;
    }

}
