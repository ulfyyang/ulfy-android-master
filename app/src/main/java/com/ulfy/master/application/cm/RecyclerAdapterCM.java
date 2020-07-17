package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.ui.cell.RecyclerAdapterCell;

public class RecyclerAdapterCM extends BaseCM {
    public String content;

    public RecyclerAdapterCM(String content) {
        this.content = content;
    }

    @Override public Class<? extends IView> getViewClass() {
        return RecyclerAdapterCell.class;
    }
}