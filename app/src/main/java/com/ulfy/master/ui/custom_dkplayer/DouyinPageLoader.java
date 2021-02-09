package com.ulfy.master.ui.custom_dkplayer;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.ulfy.android.task.LoadListPageUiTask;
import com.ulfy.android.task.TaskExecutor;
import com.ulfy.android.task.Transponder;

public class DouyinPageLoader extends Transponder {
    private RecyclerView recyclerView;                              // ViewPager2内部的RecyclerView
    private LoadListPageUiTask loadListPageUiTask;                  // 分页加载的任务
    private LoadListPageUiTask.LoadListPageUiTaskInfo taskInfo;     // 任务执行的信息

    public DouyinPageLoader(ViewPager2 viewPager2) {
        this.recyclerView = (RecyclerView) viewPager2.getChildAt(0);
        loadListPageUiTask = new LoadListPageUiTask(recyclerView.getContext(), this);
        recyclerView.addOnScrollListener(new OnScrollImpl());
    }

    public void updateExecuteBody(LoadListPageUiTask.LoadListPageUiTaskInfo taskInfo, LoadListPageUiTask.OnLoadListPage executeBody) {
        this.taskInfo = taskInfo;
        loadListPageUiTask.setTaskInfo(taskInfo);
        loadListPageUiTask.setLoadListPageBody(executeBody);
    }

    @Override public void onSuccess(Object data) {
        if (recyclerView.getAdapter() != null) {
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    public class OnScrollImpl extends RecyclerView.OnScrollListener {

        @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                    getLastVisiableItemPosition() >= recyclerView.getLayoutManager().getItemCount() - 5) {
                if (!taskInfo.isLoadedEndPage() && !loadListPageUiTask.isRunning()) {
                    taskInfo.loadNextPage();
                    TaskExecutor.defaultSingleTaskExecutor().post(loadListPageUiTask);
                }
            }
        }

        private int getLastVisiableItemPosition() {
            int lastVisibleItemPosition = 0;
            if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager())
                        .findLastVisibleItemPosition();
            }
            return lastVisibleItemPosition;
        }

    }
}