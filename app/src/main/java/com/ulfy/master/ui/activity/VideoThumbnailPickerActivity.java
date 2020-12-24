package com.ulfy.master.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.ulfy.android.system.ActivityUtils;
import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_transponder.ContentDataLoader;
import com.ulfy.android.task_transponder.OnReloadListener;
import com.ulfy.master.application.vm.VideoThumbnailPickerVM;
import com.ulfy.master.ui.base.TitleContentActivity;
import com.ulfy.master.ui.view.VideoThumbnailPickerView;

import java.io.File;

public class VideoThumbnailPickerActivity extends TitleContentActivity {
    private VideoThumbnailPickerVM vm;
    private VideoThumbnailPickerView view;

    /**
     * 启动Activity
     */
    public static void startActivity(Activity activity, int requestCode, File video) {
        Bundle data = new Bundle();
        data.putSerializable("video", video);
        ActivityUtils.startActivity(activity, VideoThumbnailPickerActivity.class, requestCode, data);
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
        vm = new VideoThumbnailPickerVM();
        vm.video = (File) ActivityUtils.getData().getSerializable("video");
    }

    /**
     * 初始化界面的数据
     */
    private void initContent(final Bundle savedInstanceState) {
        TaskUtils.loadData(getContext(), vm.loadDataOnExe(), new ContentDataLoader(contentFL, vm, false) {
                    @Override protected void onCreatView(ContentDataLoader loader, View createdView) {
                        view = (VideoThumbnailPickerView) createdView;
                        view.handleThumbnailThenRefreshUI();
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
        titleTV.setText("截图选择");
    }

    @Override public void onBackPressed() {
        ActivityUtils.returnData("thumbnail", vm.pickedThumbnail);
    }
}