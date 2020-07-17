package com.ulfy.master.application.vm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadListPageUiTask;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.application.cm.List5ChildCM;
import com.ulfy.master.application.cm.List5GroupCM;
import com.ulfy.master.ui.view.List5View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class List5VM extends BaseVM {
    public List<List5GroupCM> list5GroupCMList = new ArrayList<>();
    public LoadListPageUiTask.LoadListPageUiTaskInfo list5GroupTaskInfo = new LoadListPageUiTask.LoadListPageUiTaskInfo<>(list5GroupCMList);

    public LoadListPageUiTask.OnLoadListPage loadDataPerPageOnExe() {
        return new LoadListPageUiTask.OnLoadSimpleListPage() {
            @Override protected void loadSimplePage(LoadListPageUiTask task, List<Object> modelList, List<Object> tempList, int page, int pageSize) throws Exception {
                Thread.sleep(1000);
                for (int i = 0; i < 5; i++) {
                    List5GroupCM groupCM = new List5GroupCM(String.format("分组：第 %d 组", i));
                    int childCount = new Random().nextInt(10) + 1;
                    for (int j = 0; j < childCount; j++) {
                        groupCM.childCMList.add(new List5ChildCM(String.format("分组项：第 %d 组，第 %d 项", i, j)));
                    }
                    tempList.add(groupCM);
                }
            }
        };
    }

    @Override public Class<? extends IView> getViewClass() {
        return List5View.class;
    }
}