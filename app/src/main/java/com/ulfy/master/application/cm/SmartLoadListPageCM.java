package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.ui.cell.SmartLoadListPageCell;

public class SmartLoadListPageCM extends BaseCM {
    public String content;

    public SmartLoadListPageCM(String content) {
        this.content = content;
    }

    @Override public Class<? extends IView> getViewClass() {
        return SmartLoadListPageCell.class;
    }
}