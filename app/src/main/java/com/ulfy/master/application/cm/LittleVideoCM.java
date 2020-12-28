package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.ui.cell.LittleVideoCell;

public class LittleVideoCM extends BaseCM {
    public String imageUrl, videoUrl;

    public LittleVideoCM(String imageUrl, String videoUrl) {
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
    }

    @Override public Class<? extends IView> getViewClass() {
        return LittleVideoCell.class;
    }
}