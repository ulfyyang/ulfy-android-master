package com.ulfy.master.application.vm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.application.cm.PagerAdapterCM;
import com.ulfy.master.ui.view.PagerAdapterView;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapterVM extends BaseVM {
    public List<PagerAdapterCM> pagerCMList = new ArrayList<>();

    public LoadDataUiTask.OnExecute loadDataOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在加载...");
                    for (int i = 0; i < 10; i++) {
                        pagerCMList.add(new PagerAdapterCM(String.format("页面 %d", i + 1)));
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
        return PagerAdapterView.class;
    }
}