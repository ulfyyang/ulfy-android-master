package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.ui.cell.List2Cell;

public class List2CM extends BaseCM {
    public int index;

    public List2CM(int index) {
        this.index = index;
    }

    @Override public Class<? extends IView> getViewClass() {
        return List2Cell.class;
    }
}