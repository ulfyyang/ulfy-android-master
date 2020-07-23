package com.ulfy.master.application.vm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.android.utils.VideoUtils;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.application.cm.VideoThumbnailCM;
import com.ulfy.master.ui.view.VideoThumbnailView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VideoThumbnailVM extends BaseVM {
    public List<VideoThumbnailCM> thumbnailCMList = new ArrayList<>();

    public LoadDataUiTask.OnExecute loadDataOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在加载...");

                    task.notifySuccess("加载完成");
                } catch (Exception e) {
                    LogUtils.log("加载失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }

    public LoadDataUiTask.OnExecute handleThumbnailOnExe(File video) {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在处理...");
                    List<File> thumbnailList = VideoUtils.videoToBitmapListToTempFile(video, 6, new VideoUtils.OnVideoToBitmapListener() {
                        @Override public void onVideoToBitmap(File destFile, int count, int index) {
                            task.notifyUpdate(String.format("正在处理%.2f%%...", (index + 1) * 1.0f / count * 100));
                        }
                    });
                    thumbnailCMList.clear();
                    if (thumbnailList != null && thumbnailList.size() > 0) {
                        for (File thumbnail : thumbnailList) {
                            thumbnailCMList.add(new VideoThumbnailCM(thumbnail));
                        }
                    }
                    task.notifySuccess("处理完成");
                } catch (Exception e) {
                    LogUtils.log("处理失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }

    @Override public Class<? extends IView> getViewClass() {
        return VideoThumbnailView.class;
    }
}