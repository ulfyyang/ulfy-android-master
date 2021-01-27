package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.ui.cell.VideoReplyCommentCell;

public class VideoReplyCommentCM extends BaseCM {

    @Override public Class<? extends IView> getViewClass() {
        return VideoReplyCommentCell.class;
    }
}