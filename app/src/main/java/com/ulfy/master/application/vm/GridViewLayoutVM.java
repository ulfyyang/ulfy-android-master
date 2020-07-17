package com.ulfy.master.application.vm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.application.cm.GridViewLayoutCM;
import com.ulfy.master.ui.view.GridViewLayoutView;

import java.util.ArrayList;
import java.util.List;

public class GridViewLayoutVM extends BaseVM {
    public List<GridViewLayoutCM> dataCMList = new ArrayList<>();

    public LoadDataUiTask.OnExecute loadDataOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在加载...");
                    for (int i = 0; i < 10; i++) {
                        dataCMList.add(new GridViewLayoutCM(String.format("显示内容：%d", i)));
                    }
                    task.notifySuccess("加载完成");
                } catch (Exception e) {
                    LogUtils.log("加载失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }

    @Override public Class<? extends IView> getViewClass() {
        return GridViewLayoutView.class;
    }
}