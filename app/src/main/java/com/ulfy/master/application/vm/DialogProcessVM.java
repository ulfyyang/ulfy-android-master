package com.ulfy.master.application.vm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.ui.view.DialogProcessView;

public class DialogProcessVM extends BaseVM {
    public boolean cancel;

    public LoadDataUiTask.OnExecute loadDataOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在加载...");

                    task.notifySuccess("加载完成");
                } catch (Exception e) {
                    LogUtils.log("加载失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }

    public LoadDataUiTask.OnExecute processOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在处理...");
                    for (int i = 0; i < 100; i++) {
                        if (cancel) {
                            throw new IllegalStateException("任务取消");
                        }
                        Thread.sleep(100);
                        task.notifyUpdate(i + 1);
                    }
                    task.notifySuccess("处理完成");
                } catch (Exception e) {
                    LogUtils.log("处理失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }

    @Override public Class<? extends IView> getViewClass() {
        return DialogProcessView.class;
    }
}