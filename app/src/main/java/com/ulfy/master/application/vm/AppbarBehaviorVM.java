package com.ulfy.master.application.vm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.application.cm.AppbarBehaviorCM;
import com.ulfy.master.ui.view.AppbarBehaviorView;

import java.util.ArrayList;
import java.util.List;

public class AppbarBehaviorVM extends BaseVM {
    public List<AppbarBehaviorCM> rvNormalCMList = new ArrayList<>();
    public List<AppbarBehaviorCM> rvRefreshCMList = new ArrayList<>();

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

    public LoadDataUiTask.OnExecute refreshOnExe() {        // 模拟一下下拉刷新
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在刷新...");

                    task.notifySuccess("刷新完成");
                } catch (Exception e) {
                    LogUtils.log("刷新失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }

    public void increase() {
        rvNormalCMList.add(new AppbarBehaviorCM());
        rvRefreshCMList.add(new AppbarBehaviorCM());
    }

    public void minus() {
        if (rvNormalCMList.size() > 0) {
            rvNormalCMList.remove(rvNormalCMList.size() - 1);
        }
        if (rvRefreshCMList.size() > 0) {
            rvRefreshCMList.remove(rvRefreshCMList.size() - 1);
        }
    }

    @Override public Class<? extends IView> getViewClass() {
        return AppbarBehaviorView.class;
    }
}