package com.ulfy.master.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_transponder.ContentDataLoader;
import com.ulfy.android.task_transponder.OnReloadListener;
import com.ulfy.android.system.ActivityUtils;
import com.ulfy.master.application.vm.RecyclerViewLoaderVM;
import com.ulfy.master.ui.base.TitleContentActivity;
import com.ulfy.master.ui.view.RecyclerViewLoaderView;


public class RecyclerViewLoaderActivity extends TitleContentActivity {
    private RecyclerViewLoaderVM vm;
    private RecyclerViewLoaderView view;

    /**
     * 启动Activity
     */
    public static void startActivity() {
        ActivityUtils.startActivity(RecyclerViewLoaderActivity.class);
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
        vm = new RecyclerViewLoaderVM();
    }

    /**
     * 初始化界面的数据
     */
    private void initContent(final Bundle savedInstanceState) {
        TaskUtils.loadData(getContext(), vm.loadDataOnExe(), new ContentDataLoader(contentFL, vm, false) {
                    @Override protected void onCreateView(ContentDataLoader loader, View createdView) {
                        view = (RecyclerViewLoaderView) createdView;
                        view.loadData();
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
        titleTV.setText("RecyclerView上拉加载");
    }
}