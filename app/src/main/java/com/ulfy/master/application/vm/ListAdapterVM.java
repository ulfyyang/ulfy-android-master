package com.ulfy.master.application.vm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.application.cm.ListAdapterCM;
import com.ulfy.master.ui.view.ListAdapterView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapterVM extends BaseVM {
    public List<ListAdapterCM> dataCMList = new ArrayList<>();

    public LoadDataUiTask.OnExecute loadDataOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在加载...");
                    dataCMList.clear();
                    for (int i = 0; i < 20; i++) {
                        dataCMList.add(new ListAdapterCM(String.format("项：%d", i)));
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
        return ListAdapterView.class;
    }
}