package cn.edu.nuaa.my.base;

import android.widget.TextView;

import com.kit.utils.ZogUtils;
import cn.edu.nuaa.my.base.interfaces.IRefresh;
import cn.edu.nuaa.my.base.util.view.MainTopUtils;
import cn.edu.nuaa.my.base.util.view.MenuJumpTopUtils;
import cn.edu.nuaa.my.main.bottomtab.MenuJumpActivity;

import cn.sharesdk.analysis.MobclickAgent;

public class BaseFragment extends com.kit.ui.BaseFragment implements IRefresh {
    public TextView tvPreDo, tvDo;

    public void onResume() {
        super.onResume();
        String tag = getClass().getName();
        ZogUtils.printLog(getClass(), tag + " start");

        if (getActivity() instanceof MenuJumpActivity) {
            tvPreDo = MenuJumpTopUtils.tvPreDo;
            tvDo = MenuJumpTopUtils.tvDo;
        } else {
            tvPreDo = MainTopUtils.tvPreDo;
            tvDo = MainTopUtils.tvDo;
        }


        MobclickAgent.onPageStart(tag); //统计页面
    }

    public void onPause() {
        super.onPause();
        String tag = getClass().getName();
        ZogUtils.printLog(getClass(), tag + " end");

        MobclickAgent.onPageEnd(tag);
    }

    public void refresh(){
    }
}
