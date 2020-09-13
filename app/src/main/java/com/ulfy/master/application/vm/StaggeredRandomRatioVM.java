package com.ulfy.master.application.vm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadListPageUiTask;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.application.cm.StaggeredRandomRatioCM;
import com.ulfy.master.infrastructure.AppConfig;
import com.ulfy.master.ui.view.StaggeredRandomRatioView;

import java.util.ArrayList;
import java.util.List;

public class StaggeredRandomRatioVM extends BaseVM {
    public List<StaggeredRandomRatioCM> staggeredCMList = new ArrayList<>();
    public LoadListPageUiTask.LoadListPageUiTaskInfo<StaggeredRandomRatioCM> staggeredTaskInfo = new LoadListPageUiTask.LoadListPageUiTaskInfo<>(staggeredCMList);

    public LoadListPageUiTask.OnLoadListPage loadDataPerPageOnExe() {
        return new LoadListPageUiTask.OnLoadSimpleListPage() {
            @Override protected void loadSimplePage(LoadListPageUiTask task, List<Object> modelList, List<Object> tempList, int page, int pageSize) throws Exception {
                Thread.sleep(1000);
                for (int i = 0; i < 20; i++) {
                    tempList.add(new StaggeredRandomRatioCM(page * 20 + i, AppConfig.getPictureUrlRandom()));
                }
            }
        };
    }

    @Override public Class<? extends IView> getViewClass() {
        return StaggeredRandomRatioView.class;
    }
}