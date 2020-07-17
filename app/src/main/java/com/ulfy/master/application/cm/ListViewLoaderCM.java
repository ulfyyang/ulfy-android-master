package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.ui.cell.ListViewLoaderCell;

public class ListViewLoaderCM extends BaseCM {
    public String content;

    public ListViewLoaderCM(String content) {
        this.content = content;
    }

    @Override public Class<? extends IView> getViewClass() {
        return ListViewLoaderCell.class;
    }
}