package com.ulfy.master.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.ulfy.android.system.ActivityUtils;
import com.ulfy.android.task.LoadListPageUiTask;
import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_transponder.ContentDataLoader;
import com.ulfy.android.task_transponder.OnReloadListener;
import com.ulfy.master.BuildConfig;
import com.ulfy.master.application.cm.LittleVideoCM;
import com.ulfy.master.application.vm.DouyinVM;
import com.ulfy.master.ui.base.ContentActivity;
import com.ulfy.master.ui.custom_dkplayer.VideoViewRepository;
import com.ulfy.master.ui.custom_dkplayer.cache.PreloadManager;
import com.ulfy.master.ui.view.DouyinView;

import java.util.List;


public class DouyinActivity extends ContentActivity {
    private static List<LittleVideoCM> videoCMList;
    private static LoadListPageUiTask.LoadListPageUiTaskInfo taskInfo;
    private static int enterPosition;
    private DouyinVM vm;
    private DouyinView view;

    /**
     * 启动Activity
     */
    public static void startActivity(List<LittleVideoCM> videoCMList, LoadListPageUiTask.LoadListPageUiTaskInfo taskInfo, int enterPosition) {
        DouyinActivity.videoCMList = videoCMList;
        DouyinActivity.taskInfo = taskInfo;
        DouyinActivity.enterPosition = enterPosition;
        ActivityUtils.startActivity(DouyinActivity.class);
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
        vm = new DouyinVM();
        vm.init(videoCMList, taskInfo, enterPosition);
        DouyinActivity.videoCMList = null;
        DouyinActivity.taskInfo = null;
        DouyinActivity.enterPosition = 0;
    }

    /**
     * 初始化界面的数据
     */
    private void initContent(final Bundle savedInstanceState) {
        TaskUtils.loadData(getContext(), vm.loadDataOnExe(), new ContentDataLoader(contentFL, vm, true) {
                    @Override protected void onCreatView(ContentDataLoader loader, View createdView) {
                        view = (DouyinView) createdView;
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

    }

    @Override protected void onPause() {
        super.onPause();
        VideoViewRepository.getInstance().pause(this);
    }
    @Override protected void onResume() {
        super.onResume();
        VideoViewRepository.getInstance().resume(this);
    }
    @Override protected void onDestroy() {
        super.onDestroy();
        VideoViewRepository.getInstance().releaseVideoView(this, true);
        if (BuildConfig.VIDEO_PRE_LOAD) {
            PreloadManager.getInstance(getContext()).removeAllPreloadTask();
        }
    }
    @Override public void onBackPressed() {
        if (!VideoViewRepository.getInstance().onBackPressed(this)) {
            super.onBackPressed();
        }
    }
}