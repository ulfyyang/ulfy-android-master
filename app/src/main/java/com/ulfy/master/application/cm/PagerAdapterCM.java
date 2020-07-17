package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.ui.cell.PagerAdapterCell;

public class PagerAdapterCM extends BaseCM {
    public String page;

    public PagerAdapterCM(String page) {
        this.page = page;
    }

    @Override public Class<? extends IView> getViewClass() {
        return PagerAdapterCell.class;
    }
}