package com.ulfy.master.application.vm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadListPageUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.application.cm.ShowTypeCM;
import com.ulfy.master.ui.view.ShowTypeView;

import java.util.ArrayList;
import java.util.List;

public class ShowTypeVM extends BaseVM {
    public List<ShowTypeCM> showCMList = new ArrayList<>();
    public LoadListPageUiTask.LoadListPageUiTaskInfo<ShowTypeCM> showTaskInfo = new LoadListPageUiTask.LoadListPageUiTaskInfo<>(showCMList);
    public boolean vertical;        // 是否是纵向布局

    public LoadListPageUiTask.OnLoadListPage loadDataPerPageOnExe() {
        return new LoadListPageUiTask.OnLoadSimpleListPage() {
            @Override protected void loadSimplePage(LoadListPageUiTask task, List<Object> modelList, List<Object> tempList, int page, int pageSize) throws Exception {
                Thread.sleep(1000);
                for (int i = 0; i < 10; i++) {
                    tempList.add(new ShowTypeCM(vertical, (page - 1) * 10 + i));
                }
            }
        };
    }

    /**
     * 修改单元方向
     * @return 修改了返回true，如果没有修改则返回false
     */
    public boolean changeCellDirection(boolean vertical) {
        if (this.vertical != vertical) {
            this.vertical = vertical;
            for (ShowTypeCM cm : showCMList) {
                cm.vertical = vertical;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override public Class<? extends IView> getViewClass() {
        return ShowTypeView.class;
    }
}