package com.ulfy.master.application.vm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.task.LoadListPageUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.ui.view.TaskView;

import java.util.ArrayList;
import java.util.List;

public class TaskVM extends BaseVM {
    /**
     * 加载普通任务的数据
     */
    public String data;
    /**
     * 加载分页数据的任务
     */
    public List<String> listPageDataList = new ArrayList<>();
    public LoadListPageUiTask.LoadListPageUiTaskInfo<String> loadPageDataTaskInfo = new LoadListPageUiTask.LoadListPageUiTaskInfo<>(listPageDataList);


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

    public LoadDataUiTask.OnExecute loadDataTaskOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在加载...");
                    Thread.sleep(1000);
                    data = "LoadDataTask数据";
                    task.notifySuccess("加载完成");
                } catch (Exception e) {
                    LogUtils.log("加载失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }

    public LoadListPageUiTask.OnLoadListPage loadListDataTaskPerPageOnExe() {
        return new LoadListPageUiTask.OnLoadSimpleListPage() {
            @Override protected void loadSimplePage(LoadListPageUiTask task, List<Object> modelList, List<Object> tempList, int page, int pageSize) throws Exception {
                Thread.sleep(1000);
                for (int i = 0; i < 3; i++) {
                    tempList.add(String.format("LoadListDataTask数据：页 %d - %d", page, i));
                }
            }
        };
    }

    @Override public Class<? extends IView> getViewClass() {
        return TaskView.class;
    }
}