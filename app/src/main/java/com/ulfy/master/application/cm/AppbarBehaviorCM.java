package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.ui.cell.AppbarBehaviorCell;

public class AppbarBehaviorCM extends BaseCM {

    @Override public Class<? extends IView> getViewClass() {
        return AppbarBehaviorCell.class;
    }
}