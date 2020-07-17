package com.ulfy.master.application.vm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadListPageUiTask;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.application.cm.ListViewLoaderCM;
import com.ulfy.master.ui.view.ListViewLoaderView;

import java.util.ArrayList;
import java.util.List;

public class ListViewLoaderVM extends BaseVM {
    public List<ListViewLoaderCM> dataCMList = new ArrayList<>();
    public LoadListPageUiTask.LoadListPageUiTaskInfo<ListViewLoaderCM> dataTaskInfo = new LoadListPageUiTask.LoadListPageUiTaskInfo<>(dataCMList);

    public LoadListPageUiTask.OnLoadListPage loadDataPerPageOnExe() {
        return new LoadListPageUiTask.OnLoadSimpleListPage() {
            @Override protected void loadSimplePage(LoadListPageUiTask task, List<Object> modelList, List<Object> tempList, int page, int pageSize) throws Exception {
                Thread.sleep(2000);
                // 这里表示只有三页数据，当加载不到数据后框架会默认已经到底了。起始页默认是1，可以通过修改LoadListPageUiTask.DEFAULT_START_PAGE来改变默认起始值
                if (page <= 3) {
                    for (int i = 0; i < 20; i++) {
                        tempList.add(new ListViewLoaderCM(String.format("页：%d 项：%d", page, i)));
                    }
                }
            }
        };
    }

    @Override public Class<? extends IView> getViewClass() {
        return ListViewLoaderView.class;
    }
}