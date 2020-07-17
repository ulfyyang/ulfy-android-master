package com.ulfy.master.application.cm;

import com.ulfy.android.download_manager.DownloadTask;
import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.domain.entity.DownloadMovie;
import com.ulfy.master.ui.cell.DownloadManagerDownloadingCell;

public class DownloadManagerDownloadingCM extends BaseCM {
    public DownloadTask<DownloadMovie> downloadTask;

    public DownloadManagerDownloadingCM(DownloadTask<DownloadMovie> downloadTask) {
        this.downloadTask = downloadTask;
    }

    @Override public Class<? extends IView> getViewClass() {
        return DownloadManagerDownloadingCell.class;
    }
}