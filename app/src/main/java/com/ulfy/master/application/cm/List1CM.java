package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.ui.cell.List1Cell;

public class List1CM extends BaseCM {
    public int index;

    public List1CM(int index) {
        this.index = index;
    }

    @Override public Class<? extends IView> getViewClass() {
        return List1Cell.class;
    }
}