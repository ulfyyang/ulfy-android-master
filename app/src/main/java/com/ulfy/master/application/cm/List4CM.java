package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.ui.cell.List4Cell;

public class List4CM extends BaseCM {
    public String url;

    public List4CM(String url) {
        this.url = url;
    }

    @Override public Class<? extends IView> getViewClass() {
        return List4Cell.class;
    }
}