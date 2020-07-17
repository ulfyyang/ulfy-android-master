package com.ulfy.master.application.vm;

import com.ulfy.android.download_manager.DownloadManager;
import com.ulfy.android.download_manager.DownloadTask;
import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.application.cm.DownloadManagerDownloadedCM;
import com.ulfy.master.application.cm.DownloadManagerDownloadingCM;
import com.ulfy.master.domain.entity.DownloadMovie;
import com.ulfy.master.ui.view.DownloadManagerView;

import java.util.ArrayList;
import java.util.List;

public class DownloadManagerVM extends BaseVM {
    public List<DownloadManagerDownloadingCM> downloadingCMList = new ArrayList<>();
    public List<DownloadManagerDownloadedCM> downloadedCMList = new ArrayList<>();

    public LoadDataUiTask.OnExecute loadDataOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在加载...");
                    reloadDownloadingTaskInfo();
                    reloadDownloadedTaskInfo();
                    task.notifySuccess("加载完成");
                } catch (Exception e) {
                    LogUtils.log("加载失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }

    public void reloadDownloadingTaskInfo() {
        downloadingCMList.clear();
        for (DownloadTask<DownloadMovie> downloadTask : DownloadManager.getInstance().provideAllDownloadingTask()) {
            downloadingCMList.add(new DownloadManagerDownloadingCM(downloadTask));
        }
    }

    public void reloadDownloadedTaskInfo() {
        downloadedCMList.clear();
        for (DownloadTask<DownloadMovie> downloadTask : DownloadManager.getInstance().provideAllDownloadedTaskInfo()) {
            downloadedCMList.add(new DownloadManagerDownloadedCM(downloadTask));
        }
    }

    @Override public Class<? extends IView> getViewClass() {
        return DownloadManagerView.class;
    }
}