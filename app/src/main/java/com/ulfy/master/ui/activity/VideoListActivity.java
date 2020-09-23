package com.ulfy.master.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.ulfy.android.system.ActivityUtils;
import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_transponder.ContentDataLoader;
import com.ulfy.android.task_transponder.OnReloadListener;
import com.ulfy.android.utils.StatusBarUtils;
import com.ulfy.master.application.vm.VideoListVM;
import com.ulfy.master.ui.base.ContentActivity;
import com.ulfy.master.ui.view.VideoListView;

public class VideoListActivity extends ContentActivity {
    private VideoListVM vm;
    private VideoListView view;

    /**
     * 启动Activity
     */
    public static void startActivity() {
        ActivityUtils.startActivity(VideoListActivity.class);
    }

    /**
     * 初始化方法
     */
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.translucent(this);
        initModel(savedInstanceState);
        initContent(savedInstanceState);
    }

    /**
     * 初始化模型和界面
     */
    private void initModel(Bundle savedInstanceState) {
        vm = new VideoListVM();
    }

    /**
     * 初始化界面的数据
     */
    private void initContent(final Bundle savedInstanceState) {
        TaskUtils.loadData(getContext(), vm.loadDataOnExe(), new ContentDataLoader(contentFL, vm, false) {
                    @Override protected void onCreatView(ContentDataLoader loader, View createdView) {
                        view = (VideoListView) createdView;
                    }
                }.setOnReloadListener(new OnReloadListener() {
                    @Override public void onReload() {
                        initContent(savedInstanceState);
                    }
                })
        );
    }
}