package com.ulfy.master.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_transponder.ContentDataLoader;
import com.ulfy.android.task_transponder.OnReloadListener;
import com.ulfy.master.application.vm.ChargeVM;
import com.ulfy.master.ui.base.ContainerFragment;
import com.ulfy.master.ui.view.ChargeView;

public class ChargeFragment extends ContainerFragment {
    private ChargeVM vm;
    private ChargeView view;

    /**
     * 初始化方法
     */
    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        // initModel(savedInstanceState);
        // initContent(savedInstanceState);
    }

    /**
     * 用户首次可见
     */
    @Override public void onVisibleFirstToUser() {
        initModel(null);
        initContent(null);
    }

    /**
     * 初始化模型和界面
     */
    private void initModel(Bundle savedInstanceState) {
        vm = new ChargeVM();
    }

    /**
     * 初始化界面的数据
     */
    private void initContent(final Bundle savedInstanceState) {
        TaskUtils.loadData(getContext(), vm.loadDataOnExe(), new ContentDataLoader(contentFL, vm, false) {
                    @Override protected void onCreateView(ContentDataLoader loader, View createdView) {
                        view = (ChargeView) createdView;
                    }
                }.setOnReloadListener(new OnReloadListener() {
                    @Override public void onReload() {
                        initContent(savedInstanceState);
                    }
                })
        );
    }
}
