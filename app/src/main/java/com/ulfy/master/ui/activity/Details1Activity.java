package com.ulfy.master.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.ulfy.android.system.ActivityUtils;
import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_transponder.ContentDataInsideLoader;
import com.ulfy.android.task_transponder.OnReloadListener;
import com.ulfy.master.application.vm.Details1VM;
import com.ulfy.master.ui.base.TitleContentActivity;
import com.ulfy.master.ui.view.Details1View;

public class Details1Activity extends TitleContentActivity {
    private Details1VM vm;
    private Details1View view;

    /**
     * 启动Activity
     */
    public static void startActivity() {
        ActivityUtils.startActivity(Details1Activity.class);
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
        vm = new Details1VM();
    }

    /**
     * 初始化界面的数据
     */
    private void initContent(final Bundle savedInstanceState) {
        // 需要采用 ContentDataInsideLoader 加载器来实现内部加载的效果
        TaskUtils.loadData(getContext(), vm.loadDataOnExe(), new ContentDataInsideLoader(contentFL, vm) {
                    @Override protected void onCreatView(ContentDataInsideLoader loader, View createdView) {
                        view = (Details1View) createdView;
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
        titleTV.setText("详情页");
    }
}