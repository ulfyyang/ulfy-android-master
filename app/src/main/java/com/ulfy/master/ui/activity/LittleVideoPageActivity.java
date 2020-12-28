package com.ulfy.master.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.ulfy.android.system.ActivityUtils;
import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_transponder.ContentDataLoader;
import com.ulfy.android.task_transponder.OnReloadListener;
import com.ulfy.master.application.vm.LittleVideoPageVM;
import com.ulfy.master.ui.base.TitleContentActivity;
import com.ulfy.master.ui.view.LittleVideoPageView;

public class LittleVideoPageActivity extends TitleContentActivity {
    private LittleVideoPageVM vm;
    private LittleVideoPageView view;

    /**
     * 启动Activity
     */
    public static void startActivity() {
        ActivityUtils.startActivity(LittleVideoPageActivity.class);
    }

    /**
     * 初始化方法
     */
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initModel(savedInstanceState);
        initContent(savedInstanceState);
        initActivity(savedInstanceState);
    }

    /**
     * 初始化模型和界面
     */
    private void initModel(Bundle savedInstanceState) {
        vm = new LittleVideoPageVM();
    }

    /**
     * 初始化界面的数据
     */
    private void initContent(final Bundle savedInstanceState) {
        TaskUtils.loadData(getContext(), vm.videoTaskInfo, vm.loadDataPerPageOnExe(), new ContentDataLoader(contentFL, vm, false) {
                    @Override protected void onCreatView(ContentDataLoader loader, View createdView) {
                        view = (LittleVideoPageView) createdView;
                    }
                }.setOnReloadListener(new OnReloadListener() {
                    @Override public void onReload() {
                        initContent(savedInstanceState);
                    }
                })
        );
    }

    /**
     * 初始化Activity的数据
     */
    private void initActivity(Bundle savedInstanceState) {
        titleTV.setText("小视频列表");
    }
}