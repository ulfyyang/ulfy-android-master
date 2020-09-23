package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.infrastructure.AppConfig;
import com.ulfy.master.ui.cell.MinePageVideoCell;

public class MinePageVideoCM extends BaseCM {
    public String cover;

    public MinePageVideoCM() {
        cover = AppConfig.getPictureUrlRandom();
    }

    @Override public Class<? extends IView> getViewClass() {
        return MinePageVideoCell.class;
    }
}