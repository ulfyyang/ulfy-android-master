package com.ulfy.master.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_transponder.ContentDataInsideLoader;
import com.ulfy.android.task_transponder.OnReloadListener;
import com.ulfy.android.system.ActivityUtils;
import com.ulfy.master.application.vm.ContentDataInsideLoaderVM;
import com.ulfy.master.ui.base.TitleContentActivity;
import com.ulfy.master.ui.view.ContentDataInsideLoaderView;


public class ContentDataInsideLoaderActivity extends TitleContentActivity {
    private ContentDataInsideLoaderVM vm;
    private ContentDataInsideLoaderView view;

    /**
     * 启动Activity
     */
    public static void startActivity() {
        ActivityUtils.startActivity(ContentDataInsideLoaderActivity.class);
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
        vm = new ContentDataInsideLoaderVM();
    }

    /**
     * 初始化界面的数据
     */
    private void initContent(final Bundle savedInstanceState) {
        TaskUtils.loadData(getContext(), vm.loadDataOnExe(), new ContentDataInsideLoader(contentFL, vm) {
                    @Override protected void onCreatView(ContentDataInsideLoader loader, View createdView) {
                        view = (ContentDataInsideLoaderView) createdView;
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
        titleTV.setText("内部容器加载");
    }
}