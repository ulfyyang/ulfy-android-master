package com.ulfy.master.application.vm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.application.cm.FlowLayoutCM;
import com.ulfy.master.ui.view.FlowLayoutView;

import java.util.ArrayList;
import java.util.List;

public class FlowLayoutVM extends BaseVM {
    public List<FlowLayoutCM> dataCMList = new ArrayList<>();

    public LoadDataUiTask.OnExecute loadDataOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在加载...");
                    dataCMList.add(new FlowLayoutCM("小姐姐"));
                    dataCMList.add(new FlowLayoutCM( "社会"));
                    dataCMList.add(new FlowLayoutCM("拨浪鼓"));
                    dataCMList.add(new FlowLayoutCM("鼓浪屿水会所"));
                    dataCMList.add(new FlowLayoutCM("大红门"));
                    dataCMList.add(new FlowLayoutCM("好"));
                    dataCMList.add(new FlowLayoutCM("吃嘛嘛香就是要吃"));
                    dataCMList.add(new FlowLayoutCM("逢年瑞雪"));
                    dataCMList.add(new FlowLayoutCM("好样的"));
                    dataCMList.add(new FlowLayoutCM("你"));
                    dataCMList.add(new FlowLayoutCM("蹦极"));
                    dataCMList.add(new FlowLayoutCM("我爱吃雪糕"));
                    dataCMList.add(new FlowLayoutCM("你到底是谁"));
                    task.notifySuccess("加载完成");
                } catch (Exception e) {
                    LogUtils.log("加载失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }

    @Override public Class<? extends IView> getViewClass() {
        return FlowLayoutView.class;
    }
}