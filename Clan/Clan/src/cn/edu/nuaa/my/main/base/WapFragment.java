package cn.edu.nuaa.my.main.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.kit.utils.WebViewUtils;
import cn.edu.nuaa.my.base.BaseFragment;

/**
 * Created by tangh on 2015/9/25.
 */
public class WapFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        WebView view=new WebView(getActivity());
        WebViewUtils.loadUrl(getActivity(),view,"http://www.baidu.com",true);
        return view;
    }
}
