package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.ui.cell.RecyclerViewLoaderCell;

public class RecyclerViewLoaderCM extends BaseCM {
    public String content;

    public RecyclerViewLoaderCM(String content) {
        this.content = content;
    }

    @Override public Class<? extends IView> getViewClass() {
        return RecyclerViewLoaderCell.class;
    }
}