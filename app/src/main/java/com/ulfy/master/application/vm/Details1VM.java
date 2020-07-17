package com.ulfy.master.application.vm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.ui.view.Details1View;

public class Details1VM extends BaseVM {

    public LoadDataUiTask.OnExecute loadDataOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在加载...");
                    Thread.sleep(3000);
                    task.notifySuccess("加载完成");
                } catch (Exception e) {
                    LogUtils.log("加载失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }

    /**
     * 该方法返回与数据模型关联的 View，用于动态的生成 View
     *      该方法并不一定只能以写死的方式固定编码，可以根据模型中的数据动态的切换显示的 View
     *      通过动态的切换显示的 View 可以采用一个数据模型适配一套不同的页面
     * 而 ViewModel 是 IViewModel 接口的实现类，因此通过多态来动态切换数据模型可以做出非常复杂的灵活页面
     *      可以把数据模型理解为一个模型关联了一簇页面，一个容器中可以动态切换数据模型
     *      即多条线牵出多条线的概念
     * 这些用在列表相关的复杂页面极其方便
     */
    @Override public Class<? extends IView> getViewClass() {
        return Details1View.class;
    }
}