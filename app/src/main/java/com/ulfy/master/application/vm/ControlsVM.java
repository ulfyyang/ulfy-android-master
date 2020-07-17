package com.ulfy.master.application.vm;

import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.ui.view.ControlsView;
import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;

public class ControlsVM extends BaseVM {

    /**
     * 加载数据：
     */
    public LoadDataUiTask.OnExecute loadDataOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在加载...");

                    task.notifySuccess("加载完成");
                } catch (Exception e) {
                    e.printStackTrace();
                    task.notifyFail(e);
                }
            }
        };
    }

    @Override
    public Class<? extends IView> getViewClass() {
        return ControlsView.class;
    }
}