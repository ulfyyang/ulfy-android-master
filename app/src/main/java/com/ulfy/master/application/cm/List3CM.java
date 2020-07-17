package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.ui.cell.List3Cell;

public class List3CM extends BaseCM {
    public int index;

    public List3CM(int index) {
        this.index = index;
    }

    @Override public Class<? extends IView> getViewClass() {
        return List3Cell.class;
    }
}