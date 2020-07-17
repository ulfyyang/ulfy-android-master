package com.ulfy.master.ui.base;

import android.widget.FrameLayout;

import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;

@Layout(id = R.layout.fragment_container)
public class ContainerFragment extends BaseFragment {
    /*
    声明为protected类型，便于子类直接访问
     */
    @ViewById(id = R.id.contentFL) protected FrameLayout contentFL;
}
