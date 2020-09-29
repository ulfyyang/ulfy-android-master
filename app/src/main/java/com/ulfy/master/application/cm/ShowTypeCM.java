package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.ui.cell.ShowType1Cell;
import com.ulfy.master.ui.cell.ShowType2Cell;

public class ShowTypeCM extends BaseCM {
    public boolean vertical;        // 是否是纵向的布局（默认为否）
    public int index;

    public ShowTypeCM(boolean vertical, int index) {
        this.vertical = vertical;
        this.index = index;
    }

    @Override public Class<? extends IView> getViewClass() {
        return !vertical ? ShowType1Cell.class : ShowType2Cell.class;
    }
}