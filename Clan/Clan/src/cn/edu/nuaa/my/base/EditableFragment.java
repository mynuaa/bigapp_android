package cn.edu.nuaa.my.base;

import cn.edu.nuaa.my.base.widget.list.OnEditListener;
import cn.edu.nuaa.my.base.widget.list.RefreshListView;

public abstract class EditableFragment extends BaseFragment implements OnEditListener{
	public abstract RefreshListView getListView();
	
}
