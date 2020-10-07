package com.ulfy.master.ui.custom_dkplayer;

import android.support.v4.view.ViewPager;

import com.ulfy.android.task.LoadListPageUiTask;
import com.ulfy.android.task.TaskExecutor;
import com.ulfy.android.task.Transponder;

public class DouyinPageLoader extends Transponder {
    private ViewPager viewPager;
    private LoadListPageUiTask loadListPageUiTask;                  // 分页加载的任务
    private LoadListPageUiTask.LoadListPageUiTaskInfo taskInfo;     // 任务执行的信息

    public DouyinPageLoader(ViewPager viewPager) {
        this.viewPager = viewPager;
        loadListPageUiTask = new LoadListPageUiTask(viewPager.getContext(), this);
        this.viewPager.addOnPageChangeListener(new OnPageChangeImpl());
    }

    public void updateExecuteBody(LoadListPageUiTask.LoadListPageUiTaskInfo taskInfo, LoadListPageUiTask.OnLoadListPage executeBody) {
        this.taskInfo = taskInfo;
        loadListPageUiTask.setTaskInfo(taskInfo);
        loadListPageUiTask.setLoadListPageBody(executeBody);
    }

    @Override public void onSuccess(Object data) {
        if (viewPager.getAdapter() != null) {
            viewPager.getAdapter().notifyDataSetChanged();
        }
    }

    public class OnPageChangeImpl implements ViewPager.OnPageChangeListener {
        @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
        @Override public void onPageScrollStateChanged(int state) { }
        @Override public void onPageSelected(int position) {
            if (viewPager.getAdapter() != null && position >= viewPager.getAdapter().getCount() - 3) {
                if (!taskInfo.isLoadedEndPage() && !loadListPageUiTask.isRunning()) {
                    taskInfo.loadNextPage();
                    TaskExecutor.defaultSingleTaskExecutor().post(loadListPageUiTask);
                }
            }
        }
    }
}
