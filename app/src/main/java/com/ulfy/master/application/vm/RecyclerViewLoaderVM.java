package com.ulfy.master.application.vm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.task.LoadListPageUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.application.cm.RecyclerViewLoaderCM;
import com.ulfy.master.ui.view.RecyclerViewLoaderView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewLoaderVM extends BaseVM {
    public List<RecyclerViewLoaderCM> dataCMList = new ArrayList<>();
    public LoadListPageUiTask.LoadListPageUiTaskInfo<RecyclerViewLoaderCM> dataTaskInfo = new LoadListPageUiTask.LoadListPageUiTaskInfo<>(dataCMList);

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

    public LoadListPageUiTask.OnLoadListPage loadDataPerPageOnExe() {
        return new LoadListPageUiTask.OnLoadSimpleListPage() {
            @Override protected void loadSimplePage(LoadListPageUiTask task, List<Object> modelList, List<Object> tempList, int page, int pageSize) throws Exception {
                Thread.sleep(2000);
                // 这里表示只有三页数据，当加载不到数据后框架会默认已经到底了。起始页默认是1，可以通过修改LoadListPageUiTask.DEFAULT_START_PAGE来改变默认起始值
                if (page <= 3) {
                    for (int i = 0; i < 20; i++) {
                        tempList.add(new RecyclerViewLoaderCM(String.format("页：%d 项：%d", page, i)));
                    }
                }
            }
        };
    }

    @Override public Class<? extends IView> getViewClass() {
        return RecyclerViewLoaderView.class;
    }
}