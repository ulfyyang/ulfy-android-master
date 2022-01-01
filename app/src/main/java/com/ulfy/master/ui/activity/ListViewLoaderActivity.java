package com.ulfy.master.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_transponder.ContentDataLoader;
import com.ulfy.android.task_transponder.OnReloadListener;
import com.ulfy.android.system.ActivityUtils;
import com.ulfy.master.application.vm.ListViewLoaderVM;
import com.ulfy.master.ui.base.TitleContentActivity;
import com.ulfy.master.ui.view.ListViewLoaderView;


public class ListViewLoaderActivity extends TitleContentActivity {
    private ListViewLoaderVM vm;
    private ListViewLoaderView view;

    /**
     * 启动Activity
     */
    public static void startActivity() {
        ActivityUtils.startActivity(ListViewLoaderActivity.class);
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
        vm = new ListViewLoaderVM();
    }

    /**
     * 初始化界面的数据
     */
    private void initContent(final Bundle savedInstanceState) {
        TaskUtils.loadData(getContext(), vm.dataTaskInfo, vm.loadDataPerPageOnExe(), new ContentDataLoader(contentFL, vm, false) {
                    @Override protected void onCreateView(ContentDataLoader loader, View createdView) {
                        view = (ListViewLoaderView) createdView;
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
        titleTV.setText("ListView上拉加载");
    }
}