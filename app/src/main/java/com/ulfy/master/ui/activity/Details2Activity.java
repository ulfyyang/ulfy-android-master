package com.ulfy.master.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.ulfy.android.system.ActivityUtils;
import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_transponder.ContentDataLoader;
import com.ulfy.android.task_transponder.OnReloadListener;
import com.ulfy.master.application.vm.Details2VM;
import com.ulfy.master.ui.base.TitleContentActivity;
import com.ulfy.master.ui.view.Details2View;

public class Details2Activity extends TitleContentActivity {
    private Details2VM vm;
    private Details2View view;

    /**
     * 启动Activity
     */
    public static void startActivity() {
        ActivityUtils.startActivity(Details2Activity.class);
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
        vm = new Details2VM();
    }

    /**
     * 初始化界面的数据
     */
    private void initContent(final Bundle savedInstanceState) {
        /*
         showFirst 参数用于控制是否优先显示内容 View，
            1. 如果为 true 则先显示内容 view，随后加载数据（不显示加载动画），随后将数据渲染到内容 view 上
            2. 如果为 false 则先显示加载动画，随后加载数据，随后将数据渲染到内容 view 上，最后显示渲染好的 view
         */
        TaskUtils.loadData(getContext(), vm.loadDataOnExe(), new ContentDataLoader(contentFL, vm, false) {
                    @Override protected void onCreatView(ContentDataLoader loader, View createdView) {
                        view = (Details2View) createdView;
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