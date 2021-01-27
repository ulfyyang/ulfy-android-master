package com.ulfy.master.ui.cell;

import android.content.Context;
import android.util.AttributeSet;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.VideoReplyCommentCM;
import com.ulfy.master.ui.base.BaseCell;

@Layout(id = R.layout.cell_video_reply_comment)
public class VideoReplyCommentCell extends BaseCell {
    
    private VideoReplyCommentCM cm;

    public VideoReplyCommentCell(Context context) {
        super(context);
        init(context, null);
    }

    public VideoReplyCommentCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        cm = (VideoReplyCommentCM) model;

    }
}