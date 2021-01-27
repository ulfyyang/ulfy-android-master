package com.ulfy.master.application.vm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.task.LoadListPageUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.application.cm.VideoCommentCM;
import com.ulfy.master.ui.view.VideoCommentView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VideoCommentVM extends BaseVM {
    public int comment_count;
    public VideoCommentCM pickedVideoCommentCM;
    public List<VideoCommentCM> commentCMList = new ArrayList<>();
    public LoadListPageUiTask.LoadListPageUiTaskInfo<VideoCommentCM> taskInfo = new LoadListPageUiTask.LoadListPageUiTaskInfo<>(commentCMList);

    public VideoCommentVM() {
        comment_count = new Random().nextInt(200);
    }

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

    public LoadListPageUiTask.OnLoadListPage loadCommentDataPerPageOnExe() {
        return new LoadListPageUiTask.OnLoadSimpleListPage() {
            @Override protected void loadSimplePage(LoadListPageUiTask task, List<Object> modelList, List<Object> tempList, int page, int pageSize) throws Exception {
                Thread.sleep(1000);
                for (int i = 0; i < 10; i++) {
                    tempList.add(new VideoCommentCM());
                }
            }
        };
    }

    public LoadDataUiTask.OnExecute publisCommentOnExe(final String content) {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在发布...");
                    // 这里调用发布评论接口，为了将刚发布的评论插入到页面中，后台必须把刚插入的评论完整的返回（因为自己拼的数据缺少id等一些无法生成的信息）
                    if (pickedVideoCommentCM == null) {
                        comment_count++;
                        commentCMList.add(0, new VideoCommentCM());
                    } else {
                        pickedVideoCommentCM.addReply(new Object());
                    }
                    task.notifySuccess("发布成功");
                } catch (Exception e) {
                    LogUtils.log("操作失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }

    @Override public Class<? extends IView> getViewClass() {
        return VideoCommentView.class;
    }
}