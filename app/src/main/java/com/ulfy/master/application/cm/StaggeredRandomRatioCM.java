package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.ui.cell.StaggeredRandomRatioCell;

import java.io.Serializable;
import java.util.Random;

public class StaggeredRandomRatioCM extends BaseCM implements Serializable {
    public int index;
    public String url;
    public int widthRatio, heightRatio;

    public StaggeredRandomRatioCM(int index, String url) {
        this.index = index;
        this.url = url;
        widthRatio = 1 + new Random().nextInt(3);
        heightRatio = 1 + new Random().nextInt(3);
    }

    public boolean contentSame(StaggeredRandomRatioCM cm) {
        return this.url.equals(cm.url) && this.widthRatio == cm.widthRatio && this.heightRatio == cm.heightRatio;
    }

    @Override public Class<? extends IView> getViewClass() {
        return StaggeredRandomRatioCell.class;
    }
}