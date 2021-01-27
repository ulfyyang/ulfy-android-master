package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ulfy.android.adapter.RecyclerAdapter;
import com.ulfy.android.dialog.DialogUtils;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.system.ActivityUtils;
import com.ulfy.android.system.AppUtils;
import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_transponder.DialogProcesser;
import com.ulfy.android.task_transponder.RecyclerViewPageLoader;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.utils.RecyclerViewUtils;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.VideoCommentCM;
import com.ulfy.master.application.vm.VideoCommentVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_video_comment)
public class VideoCommentView extends BaseView {
    @ViewById(id = R.id.totalCommentTV) private TextView totalCommentTV;
    @ViewById(id = R.id.closeMessageIV) private ImageView closeMessageIV;
    @ViewById(id = R.id.messageRLV) private RecyclerView messageRLV;
    @ViewById(id = R.id.commentTV) private TextView commentTV;
    @ViewById(id = R.id.sendLL) private LinearLayout sendLL;
    @ViewById(id = R.id.replyLL) private LinearLayout replyLL;
    @ViewById(id = R.id.replyNameTV) private TextView replyNameTV;
    @ViewById(id = R.id.messageET) private EditText messageET;
    @ViewById(id = R.id.sendTV) private TextView sendTV;
    public static final String DIALOG_ID = "VideoCommentView";
    private RecyclerAdapter<VideoCommentCM> commentAdapter = new RecyclerAdapter<>();
    private RecyclerViewPageLoader recyclerViewPageLoader;
    private VideoCommentVM vm;

    public VideoCommentView(Context context) {
        super(context);
        init(context, null);
    }

    public VideoCommentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        AppUtils.setOnSoftKeyBoardChangeListener(ActivityUtils.getTopActivity(), new AppUtils.OnSoftKeyBoardChangeListener() {
            @Override public void keyBoardShow(int height) {
                sendLL.setVisibility(View.VISIBLE);
            }
            @Override public void keyBoardHide(int height) {
                DialogUtils.dismissDialog();
            }
        });
        RecyclerViewUtils.linearLayout(messageRLV).vertical();
        messageRLV.setAdapter(commentAdapter);
        commentAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener<VideoCommentCM>() {
            @Override public void onItemClick(ViewGroup parent, View view, int position, VideoCommentCM model) {
                showCommentEditor(true, model);
            }
        });
        recyclerViewPageLoader = new RecyclerViewPageLoader(messageRLV, commentAdapter, null);
    }

    @Override public void bind(IViewModel model) {
        vm = (VideoCommentVM) model;
        recyclerViewPageLoader.updateExecuteBody(vm.taskInfo, vm.loadCommentDataPerPageOnExe());
        totalCommentTV.setText(String.format("%d条评论", vm.comment_count));
        commentAdapter.setData(vm.commentCMList);
        commentAdapter.notifyDataSetChanged();
    }

    @ViewClick(ids = {R.id.closeMessageIV, R.id.commentTV})
    private void clickComment(View v) {
        switch (v.getId()) {
            case R.id.closeMessageIV:
                DialogUtils.dismissDialog(DIALOG_ID);
                break;
            case R.id.commentTV:
                showCommentEditor(false, null);
                break;
        }
    }

    private void showCommentEditor(boolean reply, VideoCommentCM videoCommentCM) {
        vm.pickedVideoCommentCM = reply ? videoCommentCM : null;
        messageET.setText("");
        replyLL.setVisibility(reply ? View.VISIBLE : View.GONE);
        if (videoCommentCM != null) {
            replyNameTV.setText("回复的人");        // 这里应该填写真实的接口数据
        }
        DialogUtils.showBottomDialog(getContext(), sendLL);
        postDelayed(new Runnable() {
            @Override public void run() {
                AppUtils.showSoftInput(messageET);
            }
        }, 100);
    }

    @ViewClick(ids = R.id.sendTV) private void sendTV(View v) {
        if (UiUtils.isEmpty(messageET)) {
            UiUtils.show("请输入评论内容");
        } else {
            TaskUtils.loadData(getContext(), vm.publisCommentOnExe(UiUtils.toString(messageET)), new DialogProcesser(getContext()) {
                        @Override public void onSuccess(Object tipData) {
                            totalCommentTV.setText(String.format("%d条评论", vm.comment_count));
                            commentAdapter.notifyDataSetChanged();
                            DialogUtils.dismissDialog();
                        }
                    }
            );
        }
    }
}