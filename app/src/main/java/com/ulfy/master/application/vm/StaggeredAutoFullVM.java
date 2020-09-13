package com.ulfy.master.application.vm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadListPageUiTask;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.application.cm.StaggeredAutoFullCM;
import com.ulfy.master.infrastructure.AppConfig;
import com.ulfy.master.ui.view.StaggeredAutoFullView;

import java.util.ArrayList;
import java.util.List;

public class StaggeredAutoFullVM extends BaseVM {
    public List<StaggeredAutoFullCM> staggeredCMList = new ArrayList<>();
    public LoadListPageUiTask.LoadListPageUiTaskInfo<StaggeredAutoFullCM> staggeredTaskInfo = new LoadListPageUiTask.LoadListPageUiTaskInfo<>(staggeredCMList);

    public LoadListPageUiTask.OnLoadListPage loadDataPerPageOnExe() {
        return new LoadListPageUiTask.OnLoadSimpleListPage() {
            @Override protected void loadSimplePage(LoadListPageUiTask task, List<Object> modelList, List<Object> tempList, int page, int pageSize) throws Exception {
                Thread.sleep(1000);
                for (int i = 0; i < 30; i++) {
                    tempList.add(new StaggeredAutoFullCM(page * 30 + i, AppConfig.getPictureUrlRandom()));
                }
            }
        };
    }

    @Override public Class<? extends IView> getViewClass() {
        return StaggeredAutoFullView.class;
    }
}