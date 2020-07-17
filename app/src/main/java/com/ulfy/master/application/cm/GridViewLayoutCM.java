package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.ui.cell.GridViewLayoutCell;

public class GridViewLayoutCM extends BaseCM {
    public String content;

    public GridViewLayoutCM(String content) {
        this.content = content;
    }

    @Override public Class<? extends IView> getViewClass() {
        return GridViewLayoutCell.class;
    }
}