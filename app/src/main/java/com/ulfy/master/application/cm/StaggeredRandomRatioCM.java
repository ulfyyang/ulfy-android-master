package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.ui.cell.StaggeredRandomRatioCell;

import java.util.Random;

public class StaggeredRandomRatioCM extends BaseCM {
    public int widthRatio, heightRatio;
    public String url;

    public StaggeredRandomRatioCM(String url) {
        this.url = url;
        widthRatio = 1 + new Random().nextInt(3);
        heightRatio = 1 + new Random().nextInt(3);
    }

    @Override public Class<? extends IView> getViewClass() {
        return StaggeredRandomRatioCell.class;
    }
}