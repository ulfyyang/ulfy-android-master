package com.ulfy.master.application.vm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadListPageUiTask;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.application.cm.VideoListCM;
import com.ulfy.master.ui.view.VideoListPageView;

import java.util.ArrayList;
import java.util.List;

public class VideoListPageVM extends BaseVM {
    public List<VideoListCM> videoCMList = new ArrayList<>();
    public LoadListPageUiTask.LoadListPageUiTaskInfo<VideoListCM> videoTaskInfo = new LoadListPageUiTask.LoadListPageUiTaskInfo<>(videoCMList);

    public LoadListPageUiTask.OnLoadListPage loadDataPerPageOnExe() {
        return new LoadListPageUiTask.OnLoadSimpleListPage() {
            @Override protected void loadSimplePage(LoadListPageUiTask task, List<Object> modelList, List<Object> tempList, int page, int pageSize) throws Exception {
                Thread.sleep(1000);
                for (int i = 0; i < 10; i++) {
                    tempList.add(new VideoListCM());
                }
            }
        };
    }

    @Override public Class<? extends IView> getViewClass() {
        return VideoListPageView.class;
    }
}