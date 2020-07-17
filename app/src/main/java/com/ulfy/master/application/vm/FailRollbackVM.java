package com.ulfy.master.application.vm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.ui.view.FailRollbackView;

public class FailRollbackVM extends BaseVM {
    public int number;

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

    public LoadDataUiTask.OnExecute oprationExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    // 先加1然后刷新页面
                    number ++;
                    task.notifyStart("正在操作...");
                    Thread.sleep(500);
                    throw new Exception("操作失败了");
//                    task.notifySuccess("操作完成");
                } catch (Exception e) {
                    // 失败后还原为原来的
                    number --;
                    LogUtils.log("操作失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }

    @Override public Class<? extends IView> getViewClass() {
        return FailRollbackView.class;
    }
}