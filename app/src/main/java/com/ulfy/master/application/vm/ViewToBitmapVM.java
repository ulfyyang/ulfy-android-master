package com.ulfy.master.application.vm;

import com.ulfy.android.image.ImageUtils;
import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.ui.view.ViewToBitmapView;

public class ViewToBitmapVM extends BaseVM {
    public String pictureUrl;

    public LoadDataUiTask.OnExecute loadDataOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在加载...");
                    pictureUrl = "http://img02file.tooopen.com/images/20160509/tooopen_sy_161967094653.jpg";
                    ImageUtils.download(pictureUrl);            // 因为图片加载是需要时间的，所以这里先预先下载下来
                    task.notifySuccess("加载完成");
                } catch (Exception e) {
                    LogUtils.log("加载失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }

    @Override public Class<? extends IView> getViewClass() {
        return ViewToBitmapView.class;
    }
}