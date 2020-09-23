package com.ulfy.master.application.vm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.ui.view.VideoListView;

import java.util.ArrayList;
import java.util.List;

public class VideoListVM extends BaseVM {
    public List<String> categoryList = new ArrayList<>();

    public LoadDataUiTask.OnExecute loadDataOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在加载...");
                    Thread.sleep(1000);
                    categoryList.add("关注");
                    categoryList.add("推荐");
                    categoryList.add("最新");
                    categoryList.add("今日头条");
                    for (int i = 0; i < 10; i++) {
                        categoryList.add("分类" + i);
                    }
                    task.notifySuccess("加载完成");
                } catch (Exception e) {
                    LogUtils.log("加载失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }

    @Override public Class<? extends IView> getViewClass() {
        return VideoListView.class;
    }
}