package com.ulfy.master.application.cm;

import com.ulfy.android.download_manager.DownloadTaskWrapper;
import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.domain.entity.DownloadMovie;
import com.ulfy.master.ui.cell.DownloadManagerDownloadedCell;

public class DownloadManagerDownloadedCM extends BaseCM {
    public DownloadTaskWrapper<DownloadMovie> downloadTask;

    public DownloadManagerDownloadedCM(DownloadTaskWrapper<DownloadMovie> downloadTask) {
        this.downloadTask = downloadTask;
    }

    @Override public Class<? extends IView> getViewClass() {
        return DownloadManagerDownloadedCell.class;
    }
}