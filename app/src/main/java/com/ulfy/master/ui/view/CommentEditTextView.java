package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ulfy.android.dialog.DialogUtils;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.system.ActivityUtils;
import com.ulfy.android.system.AppUtils;
import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_transponder.SilentProcesser;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.utils.StringUtils;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.CommentEditTextVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_comment_edit_text)
public class CommentEditTextView extends BaseView {
    @ViewById(id = R.id.openCommentBT) private Button openCommentBT;
    @ViewById(id = R.id.replyCommentBT) private Button replyCommentBT;
    @ViewById(id = R.id.sendLL) private LinearLayout sendLL;
    @ViewById(id = R.id.replyLL) private LinearLayout replyLL;
    @ViewById(id = R.id.replyNameTV) private TextView replyNameTV;
    @ViewById(id = R.id.messageET) private EditText messageET;
    @ViewById(id = R.id.sendTV) private TextView sendTV;
    private CommentEditTextVM vm;

    public CommentEditTextView(Context context) {
        super(context);
        init(context, null);
    }

    public CommentEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        // 输入法打开和关闭时设置相应的页面状态
        AppUtils.setOnSoftKeyBoardChangeListener(ActivityUtils.getTopActivity(), new AppUtils.OnSoftKeyBoardChangeListener() {
            @Override public void keyBoardShow(int height) {
                sendLL.setVisibility(View.VISIBLE);
            }
            @Override public void keyBoardHide(int height) {
                DialogUtils.dismissDialog();
            }
        });
    }

    @Override public void bind(IViewModel model) {
        vm = (CommentEditTextVM) model;
    }

    @ViewClick(ids = {R.id.openCommentBT, R.id.replyCommentBT})
    private void showCommentEditText(View v) {
        switch (v.getId()) {
            case R.id.openCommentBT:
                showCommentEditor(false, null);
                break;
            case R.id.replyCommentBT:
                showCommentEditor(true, "小明");
                break;
        }
    }

    @ViewClick(ids = R.id.sendTV) private void sendTV(View v) {
        if (UiUtils.isEmpty(messageET)) {
            UiUtils.show("请输入评论内容");
        } else {
            // 定制一个处理响应器
            class CommentProcesser extends SilentProcesser {
                @Override protected void onStart(Object data) {
                    sendTV.setEnabled(false);           // 执行过程中不可重复点击
                    sendTV.setText(data.toString());
                }
                @Override protected void onSuccess(Object data) {
                    UiUtils.show(data);
                }
                @Override protected void onFail(Object data) {
                    UiUtils.show(data);
                }
                @Override protected void onFinish(Object data) {
                    sendTV.setEnabled(true);            // 执行完成后恢复可以点击
                    sendTV.setText("发送");
                    DialogUtils.dismissDialog();
                }
            }
            TaskUtils.loadData(getContext(), vm.commentOnExe(), new CommentProcesser());
        }
    }

    /**
     * 显示评论输入框，如果是回复的话会显示回复的人
     */
    private void showCommentEditor(boolean reply, String name) {
        messageET.setText("");      // 清空输入框
        replyLL.setVisibility(reply ? View.VISIBLE : View.GONE);
        if (!StringUtils.isEmpty(name)) {
            replyNameTV.setText(name);
        }
        // 通过弹窗的形式打开输入框并稍微延迟后打开输入法（无延迟无法打开输入法键盘）
        DialogUtils.showBottomDialog(getContext(), sendLL);
        postDelayed(new Runnable() {
            @Override public void run() {
                AppUtils.showSoftInput(messageET);
            }
        }, 10);
    }
}