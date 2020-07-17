package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.DashLineVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_dash_line)
public class DashLineView extends BaseView {
    private DashLineVM vm;

    public DashLineView(Context context) {
        super(context);
        init(context, null);
    }

    public DashLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (DashLineVM) model;
    }
}