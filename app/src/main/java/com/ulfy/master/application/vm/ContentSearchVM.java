package com.ulfy.master.application.vm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.task.LoadListPageUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.application.cm.ContentSearchCM;
import com.ulfy.master.application.cm.ContentSearchHistoryCM;
import com.ulfy.master.domain.entity.ContentSearch;
import com.ulfy.master.domain.cache.SearchHistory;
import com.ulfy.master.ui.view.ContentSearchView;

import java.util.ArrayList;
import java.util.List;

public class ContentSearchVM extends BaseVM {
    public List<ContentSearchHistoryCM> searchHistoryCMList = new ArrayList<>();
    public List<ContentSearchCM> searchResultCMList = new ArrayList<>();
    public LoadListPageUiTask.LoadListPageUiTaskInfo<ContentSearchCM> searchResultTaskInfo = new LoadListPageUiTask.LoadListPageUiTaskInfo<>(searchResultCMList);
    public String keyword;      // 记录从输入框传入的需要搜索的关键字，用于给搜索结果加载时候使用

    public LoadDataUiTask.OnExecute loadDataOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在加载...");
                    loadSearchHistory();
                    task.notifySuccess("加载完成");
                } catch (Exception e) {
                    LogUtils.log("加载失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }

    public LoadListPageUiTask.OnLoadListPage loadSearchResultDataPerPageOnExe() {
        return new LoadListPageUiTask.OnLoadSimpleListPage() {
            @Override protected void loadSimplePage(LoadListPageUiTask task, List<Object> modelList, List<Object> tempList, int page, int pageSize) throws Exception {
                Thread.sleep(1000);     // 这里模拟使用搜索关键字进行后台加载
                for (int i = 0; i < 10; i++) {
                    tempList.add(new ContentSearchCM(new ContentSearch()));
                }
            }
        };
    }

    ///////////////////////////////////////////////////////////////////////////
    // 搜索历史记录 start
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 加载搜索历史
     */
    public void loadSearchHistory() {
        for (String keyword : SearchHistory.getInstance().searchHistory) {
            searchHistoryCMList.add(new ContentSearchHistoryCM(keyword));
        }
    }

    /**
     * 添加一个搜索历史
     */
    public void addSearchHistory(String content) {
        if (SearchHistory.getInstance().addSearchHistory(content)) {
            searchHistoryCMList.add(0, new ContentSearchHistoryCM(content));
        }
    }

    /**
     * 清除全部搜索历史
     */
    public void clearSearchHistory() {
        SearchHistory.clearCache();
        searchHistoryCMList.clear();
    }

    ///////////////////////////////////////////////////////////////////////////
    // 搜索历史记录 end
    ///////////////////////////////////////////////////////////////////////////

    @Override public Class<? extends IView> getViewClass() {
        return ContentSearchView.class;
    }
}