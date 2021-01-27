package com.ulfy.master.ui.cell;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ulfy.android.adapter.RecyclerAdapter;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_transponder.DialogProcesser;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.utils.RecyclerViewUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.VideoCommentCM;
import com.ulfy.master.application.cm.VideoReplyCommentCM;
import com.ulfy.master.ui.base.BaseCell;

@Layout(id = R.layout.cell_video_comment)
public class VideoCommentCell extends BaseCell {
    @ViewById(id = R.id.replyCommentRLV) private RecyclerView replyCommentRLV;
    @ViewById(id = R.id.showMoreLL) private LinearLayout showMoreLL;
    @ViewById(id = R.id.showMoreTV) private TextView showMoreTV;
    private RecyclerAdapter<VideoReplyCommentCM> adapter = new RecyclerAdapter<>();
    private VideoCommentCM cm;

    public VideoCommentCell(Context context) {
        super(context);
        init(context, null);
    }

    public VideoCommentCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        RecyclerViewUtils.linearLayout(replyCommentRLV).vertical().dividerDp(Color.TRANSPARENT, 10, 0, 0);
        replyCommentRLV.setAdapter(adapter);
    }

    @Override public void bind(IViewModel model) {
        cm = (VideoCommentCM) model;
        updateMoreReplyCommentUI();
    }

    private void updateMoreReplyCommentUI() {
        replyCommentRLV.setVisibility(cm.replyCommentCMList.size() > 0 ? View.VISIBLE : View.GONE);
        adapter.setData(cm.replyCommentCMList);
        adapter.notifyDataSetChanged();
        showMoreLL.setVisibility(cm.replys_count == 0 || cm.hasShowMore ? View.GONE : View.VISIBLE);
        showMoreTV.setText(String.format("展开%d条回复", cm.replys_count));
    }

    @ViewClick(ids = {R.id.showMoreLL})private void showMoreLL(View view) {
        TaskUtils.loadData(getContext(), cm.loadMoreReplyCommentOnExe(), new DialogProcesser(getContext()) {
                    @Override public void onSuccess(Object tipData) {
                        updateMoreReplyCommentUI();
                    }
                }
        );
    }
}