package com.ulfy.master.application.vm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.task.LoadListPageUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.application.cm.MinePageVideoCM;
import com.ulfy.master.ui.view.MinePageView;

import java.util.ArrayList;
import java.util.List;

public class MinePageVM extends BaseVM {
    public List<MinePageVideoCM> zuopinCMList = new ArrayList<>();
    public LoadListPageUiTask.LoadListPageUiTaskInfo<MinePageVideoCM> zuopinTaskInfo = new LoadListPageUiTask.LoadListPageUiTaskInfo<>(zuopinCMList);
    public List<MinePageVideoCM> shoucangCMList = new ArrayList<>();
    public LoadListPageUiTask.LoadListPageUiTaskInfo<MinePageVideoCM> shoucangTaskInfo = new LoadListPageUiTask.LoadListPageUiTaskInfo<>(shoucangCMList);

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
     * 加载分页：我的作品
     */
    public LoadListPageUiTask.OnLoadListPage loadZuopinDataPerPageOnExe() {
        return new LoadListPageUiTask.OnLoadSimpleListPage() {
            @Override protected void loadSimplePage(LoadListPageUiTask task, List<Object> modelList, List<Object> tempList, int page, int pageSize) throws Exception {
                Thread.sleep(1000);
                for (int i = 0; i < 20; i++) {
                    tempList.add(new MinePageVideoCM());
                }
            }
        };
    }

    /**
     * 加载分页：我的收藏
     */
    public LoadListPageUiTask.OnLoadListPage loadShoucangDataPerPageOnExe() {
        return new LoadListPageUiTask.OnLoadSimpleListPage() {
            @Override protected void loadSimplePage(LoadListPageUiTask task, List<Object> modelList, List<Object> tempList, int page, int pageSize) throws Exception {
                Thread.sleep(1000);
                for (int i = 0; i < 20; i++) {
                    tempList.add(new MinePageVideoCM());
                }
            }
        };
    }

    @Override public Class<? extends IView> getViewClass() {
        return MinePageView.class;
    }
}