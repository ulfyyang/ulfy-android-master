package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.ui.cell.StaggeredAutoFullCell;

import java.io.Serializable;

public class StaggeredAutoFullCM extends BaseCM implements Serializable {
    public int index;
    public String url;

    public StaggeredAutoFullCM(int index, String url) {
        this.index = index;
        this.url = url;
    }

    public boolean contentSame(StaggeredAutoFullCM cm) {
        return this.url.equals(cm.url);
    }

    @Override public Class<? extends IView> getViewClass() {
        return StaggeredAutoFullCell.class;
    }
}