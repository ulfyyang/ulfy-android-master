package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.ui.cell.ImagePreviewCell;

public class ImagePreviewCM extends BaseCM {
    public String imageUrl;

    public ImagePreviewCM(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override public Class<? extends IView> getViewClass() {
        return ImagePreviewCell.class;
    }
}