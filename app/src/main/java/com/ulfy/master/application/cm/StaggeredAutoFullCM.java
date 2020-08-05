package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.ui.cell.StaggeredAutoFullCell;

public class StaggeredAutoFullCM extends BaseCM {
    public String url;

    public StaggeredAutoFullCM(String url) {
        this.url = url;
    }

    @Override public Class<? extends IView> getViewClass() {
        return StaggeredAutoFullCell.class;
    }
}