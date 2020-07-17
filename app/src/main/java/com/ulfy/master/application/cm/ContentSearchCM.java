package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.domain.entity.ContentSearch;
import com.ulfy.master.ui.cell.ContentSearchCell;

public class ContentSearchCM extends BaseCM {
    public ContentSearch contentSearch;

    public ContentSearchCM(ContentSearch contentSearch) {
        this.contentSearch = contentSearch;
    }

    @Override public Class<? extends IView> getViewClass() {
        return ContentSearchCell.class;
    }
}