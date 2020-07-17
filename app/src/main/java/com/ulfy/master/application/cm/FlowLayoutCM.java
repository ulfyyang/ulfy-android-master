package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.ui.cell.FlowLayoutCell;

public class FlowLayoutCM extends BaseCM {
    public String content;

    public FlowLayoutCM(String content) {
        this.content = content;
    }

    @Override public Class<? extends IView> getViewClass() {
        return FlowLayoutCell.class;
    }
}