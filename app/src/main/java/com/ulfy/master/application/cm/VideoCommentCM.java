package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.ui.cell.VideoCommentCell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VideoCommentCM extends BaseCM {
    public List<VideoReplyCommentCM> replyCommentCMList = new ArrayList<>();
    public int replys_count;
    public boolean hasShowMore;        // 是否已经显示了更多的数据

    public VideoCommentCM() {
        replys_count = new Random().nextInt() % 3 == 0 ? 0 : 5;
        for (int i = 0; i < 3; i++) {
            replyCommentCMList.add(new VideoReplyCommentCM());
        }
    }

    public void addReply(Object reply) {        // 这里模拟一下回复评论的数据
        replyCommentCMList.add(0, new VideoReplyCommentCM());
    }

    public LoadDataUiTask.OnExecute loadMoreReplyCommentOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在加载...");
                    Thread.sleep(1000);
                    replyCommentCMList.clear();
                    for (int i = 0; i < 5; i++) {
                        replyCommentCMList.add(new VideoReplyCommentCM());
                    }
                    hasShowMore = true;
                    task.notifySuccess("加载完成");
                } catch (Exception e) {
                    LogUtils.log("加载失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }


    @Override public Class<? extends IView> getViewClass() {
        return VideoCommentCell.class;
    }
}