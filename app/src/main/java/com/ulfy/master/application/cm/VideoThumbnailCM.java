package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.ui.cell.VideoThumbnailCell;

import java.io.File;

public class VideoThumbnailCM extends BaseCM {
    public File thumbnail;

    public VideoThumbnailCM(File thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override public Class<? extends IView> getViewClass() {
        return VideoThumbnailCell.class;
    }
}