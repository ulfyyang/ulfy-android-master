package com.ulfy.master.application.vm;

import com.ulfy.android.data_pre_loader.DataPreLoaderManager;
import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.domain.pre_load.DataPreLoaderEntity;
import com.ulfy.master.ui.view.DataPreLoaderView;

public class DataPreLoaderVM extends BaseVM {
    public String content;

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

    /**
     * 预加载的执行体
     */
    public LoadDataUiTask.OnExecute dataPreLoaderEntityPreLoadOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在加载...");
                    // 加载指定的预加载信息，如果存在则直接返回，如果不存在则开始加载
                    DataPreLoaderManager.getInstance().loadData(DataPreLoaderEntity.class);
                    task.notifySuccess("加载完成");
                } catch (Exception e) {
                    LogUtils.log("加载失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }

    /**
     * 等待预加载完成的执行体
     *      如果预加载任务失败了则该任务会重新执行对应的流程
     */
    public LoadDataUiTask.OnExecute getPreLoadDataOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在加载...");
                    // 这里获取的方式是回去预加载然后丢弃，以后会重新执行预加载的过程。该方法由很多不同策略的加载方法
                    DataPreLoaderEntity dataPreLoaderEntity = DataPreLoaderManager.getInstance().loadDataThenInvalidate(DataPreLoaderEntity.class);
                    content = dataPreLoaderEntity.content;
                    task.notifySuccess("加载完成");
                } catch (Exception e) {
                    LogUtils.log("加载失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }

    @Override public Class<? extends IView> getViewClass() {
        return DataPreLoaderView.class;
    }
}