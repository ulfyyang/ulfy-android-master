package com.ulfy.master.application.cm;


import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.ui.cell.PickImgCell;

import java.io.Serializable;

public class PickImgCM extends BaseCM implements Serializable {
    public int id;
    public String filePath;

    public PickImgCM(int id, String filePath) {
        this.id = id;
        this.filePath = filePath;
    }

    @Override public Class<? extends IView> getViewClass() {
        return PickImgCell.class;
    }
}