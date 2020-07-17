package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.ui.cell.List5ChildCell;

public class List5ChildCM extends BaseCM {
    public String name;

    public List5ChildCM(String name) {
        this.name = name;
    }

    @Override public Class<? extends IView> getViewClass() {
        return List5ChildCell.class;
    }
}