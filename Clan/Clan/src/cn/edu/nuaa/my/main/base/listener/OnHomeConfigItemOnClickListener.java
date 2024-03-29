package cn.edu.nuaa.my.main.base.listener;

import android.content.Context;
import android.view.View;

import cn.edu.nuaa.my.base.json.homeconfig.HomeConfigItem;

/**
 * Created by Zhao on 15/7/1.
 */
public class OnHomeConfigItemOnClickListener implements View.OnClickListener {
    Context context;
    HomeConfigItem item ;

    public OnHomeConfigItemOnClickListener(Context context,HomeConfigItem item) {
        this.context = context;
        this.item = item;
    }

    @Override
    public void onClick(View v) {
        DoHomeCofigOnClick.doClick(context, item);
    }
}
