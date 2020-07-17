package com.ulfy.master.application.vm;

import com.ulfy.android.multi_domain_picker.MultiDomainPicker;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.ui.view.MultiDomainPickerView;
import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.utils.LogUtils;

public class MultiDomainPickerVM extends BaseVM {
    public String targetDomainUrl;

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

    public LoadDataUiTask.OnExecute domainPickOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在选择可用域名...");
                    Thread.sleep(2000);
                    targetDomainUrl = MultiDomainPicker.getInstance().getTargetDomainUrl(true);
                    task.notifySuccess("选择完成");
                } catch (Exception e) {
                    LogUtils.log("选择失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }

    @Override public Class<? extends IView> getViewClass() {
        return MultiDomainPickerView.class;
    }
}