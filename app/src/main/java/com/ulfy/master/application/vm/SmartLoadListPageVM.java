package com.ulfy.master.application.vm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadListPageUiTask;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.application.cm.SmartLoadListPageCM;
import com.ulfy.master.ui.view.SmartLoadListPageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SmartLoadListPageVM extends BaseVM {
    public List<SmartLoadListPageCM> dataCMList = new ArrayList<>();
    public LoadListPageUiTask.LoadListPageUiTaskInfo<SmartLoadListPageCM> dataTaskInfo = new LoadListPageUiTask.LoadListPageUiTaskInfo<>(dataCMList);

    public LoadListPageUiTask.OnLoadListPage loadDataPerPageOnExe() {
        return new LoadListPageUiTask.OnLoadSimpleListPage() {
            @Override protected void loadSimplePage(LoadListPageUiTask task, List<Object> modelList, List<Object> tempList, int page, int pageSize) throws Exception {
                Thread.sleep(2000);
                for (int i = 0; i < 20; i++) {
                    tempList.add(new SmartLoadListPageCM(String.format("页：%d 项：%d 内容：%d", page, i, new Random().nextInt(10))));
                }
            }
        };
    }

    @Override public Class<? extends IView> getViewClass() {
        return SmartLoadListPageView.class;
    }
}