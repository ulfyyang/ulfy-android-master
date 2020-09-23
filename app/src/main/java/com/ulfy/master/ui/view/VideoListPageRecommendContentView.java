package com.ulfy.master.ui.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_transponder.ContentDataLoader;
import com.ulfy.android.task_transponder.OnReloadListener;
import com.ulfy.master.application.vm.VideoListPageRecommendVM;
import com.ulfy.master.ui.base.ContentView;

public class VideoListPageRecommendContentView extends ContentView {
    private VideoListPageRecommendVM vm;
    private VideoListPageRecommendView view;

    public VideoListPageRecommendContentView(Context context) {
        super(context);
        initModel(null);
        initContent(null);
    }

    /**
     * 初始化模型和界面
     */
    private void initModel(Bundle savedInstanceState) {
        vm = new VideoListPageRecommendVM();
    }

    /**
     * 初始化界面的数据
     */
    private void initContent(final Bundle savedInstanceState) {
        TaskUtils.loadData(getContext(), vm.videoTaskInfo, vm.loadDataPerPageOnExe(), new ContentDataLoader(contentFL, vm, false) {
                    @Override protected void onCreatView(ContentDataLoader loader, View createdView) {
                        view = (VideoListPageRecommendView) createdView;
                    }
                }.setOnReloadListener(new OnReloadListener() {
                    @Override public void onReload() {
                        initContent(savedInstanceState);
                    }
                })
        );
    }
}