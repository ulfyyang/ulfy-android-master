package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.ui.cell.ContentSearchHistoryCell;

public class ContentSearchHistoryCM extends BaseCM {
    public String text;

    public ContentSearchHistoryCM(String text) {
        this.text = text;
    }

    @Override public Class<? extends IView> getViewClass() {
        return ContentSearchHistoryCell.class;
    }
}