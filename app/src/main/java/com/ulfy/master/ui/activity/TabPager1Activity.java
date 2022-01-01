package com.ulfy.master.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.ulfy.android.system.ActivityUtils;
import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_transponder.ContentDataLoader;
import com.ulfy.android.task_transponder.OnReloadListener;
import com.ulfy.master.application.vm.TabPager1VM;
import com.ulfy.master.ui.base.TitleContentActivity;
import com.ulfy.master.ui.view.TabPager1View;

public class TabPager1Activity extends TitleContentActivity {
    private TabPager1VM vm;
    private TabPager1View view;

    public static void startActivity() {
        ActivityUtils.startActivity(TabPager1Activity.class);
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
        vm = new TabPager1VM();
    }

    /**
     * 初始化界面的数据
     */
    private void initContent(final Bundle savedInstanceState) {
        TaskUtils.loadData(getContext(), vm.loadDataOnExe(), new ContentDataLoader(contentFL, vm, false) {
                    @Override protected void onCreateView(ContentDataLoader loader, View createdView) {
                        view = (TabPager1View) createdView;
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
        titleTV.setText("标签页分页");
    }
}