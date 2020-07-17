package com.ulfy.master.application.vm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadListPageUiTask;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.application.cm.List3CM;
import com.ulfy.master.ui.view.List3View;

import java.util.ArrayList;
import java.util.List;

public class List3VM extends BaseVM {
    public List<List3CM> contentCMList = new ArrayList<>();              // 存放列表数据的模型列表
    // 用于跟踪分页数据的信息，如果不需要分页可以删除该成员
    public LoadListPageUiTask.LoadListPageUiTaskInfo<List3CM> contentTaskInfo = new LoadListPageUiTask.LoadListPageUiTaskInfo<>(contentCMList);

    /**
     * 分页的方式加载列表数据，加载出来的数据必须要放到参数中的临时列表中，最终的数据合并将会在框架内完成
     *      是否分页要看后台的要求
     */
    public LoadListPageUiTask.OnLoadListPage loadContentDataPerPageOnExe() {
        return new LoadListPageUiTask.OnLoadSimpleListPage() {
            /**
             * 加载单页数据的实现
             * @param modelList     最终被加载出来的数据列表，该列表会自动从 tempList 中合并
             * @param tempList      用户存放加载出来的数据
             * @param page          维护的页码，页码的变动由框架维护，起始页为 1
             * @param pageSize      每页的大小，框架默认值为 20
             */
            @Override protected void loadSimplePage(LoadListPageUiTask task, List<Object> modelList, List<Object> tempList, int page, int pageSize) throws Exception {
                Thread.sleep(1000);
                // 在第一页时可以额外加载一些其它的数据
                if (page == LoadListPageUiTask.DEFAULT_START_PAGE) {

                }
                // 正常的按照分页来了加载数据
                for (int i = 0; i < 50; i++) {
                    tempList.add(new List3CM((page - 1) * 30 + i));
                }
            }
        };
    }

    @Override public Class<? extends IView> getViewClass() {
        return List3View.class;
    }
}