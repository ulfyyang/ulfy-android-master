package com.ulfy.master.application.vm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadListPageUiTask;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.application.cm.VideoCM;
import com.ulfy.master.ui.view.VideoView;

import java.util.ArrayList;
import java.util.List;

public class VideoVM extends BaseVM {
    public List<VideoCM> videoCMList = new ArrayList<>();
    public LoadListPageUiTask.LoadListPageUiTaskInfo<VideoCM> videoTaskInfo = new LoadListPageUiTask.LoadListPageUiTaskInfo<>(videoCMList);

    public LoadListPageUiTask.OnLoadListPage loadDataPerPageOnExe() {
        return new LoadListPageUiTask.OnLoadSimpleListPage() {
            @Override protected void loadSimplePage(LoadListPageUiTask task, List<Object> modelList, List<Object> tempList, int page, int pageSize) throws Exception {
                for (int i = 0; i < 10; i++) {
                    tempList.add(new VideoCM());
                }
            }
        };
    }

    @Override public Class<? extends IView> getViewClass() {
        return VideoView.class;
    }
}