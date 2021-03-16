package com.ulfy.master.ui.cell;

import android.content.Context;
import android.util.AttributeSet;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.AppbarBehaviorCM;
import com.ulfy.master.ui.base.BaseCell;

@Layout(id = R.layout.cell_appbar_behavior)
public class AppbarBehaviorCell extends BaseCell {
    private AppbarBehaviorCM cm;

    public AppbarBehaviorCell(Context context) {
        super(context);
        init(context, null);
    }

    public AppbarBehaviorCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        cm = (AppbarBehaviorCM) model;
    }
}