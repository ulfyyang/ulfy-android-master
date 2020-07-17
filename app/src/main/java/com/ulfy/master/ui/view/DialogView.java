package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.DialogVM;
import com.ulfy.master.ui.activity.AlertDialogActivity;
import com.ulfy.master.ui.activity.DisableTouchActivity;
import com.ulfy.master.ui.activity.NormalDialogActivity;
import com.ulfy.master.ui.activity.PopupDialogActivity;
import com.ulfy.master.ui.activity.ProgressDialogActivity;
import com.ulfy.master.ui.activity.QuickPickActivity;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_dialog)
public class DialogView extends BaseView {
    private DialogVM vm;

    public DialogView(Context context) {
        super(context);
        init(context, null);
    }

    public DialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (DialogVM) model;
    }
    
    /**
     * click: normalDialogTV
     * 普通弹出框
     */
    @ViewClick(ids = R.id.normalDialogTV) private void normalDialogTV(View v) {
        NormalDialogActivity.startActivity();
    }

    /**
     * click: popupDialogTV
     * 锚点弹出框
     */
    @ViewClick(ids = R.id.popupDialogTV) private void popupDialogTV(View v) {
        PopupDialogActivity.startActivity();
    }

    /**
     * click: alertDialogTV
     * 提示弹出框
     */
    @ViewClick(ids = R.id.alertDialogTV) private void alertDialogTV(View v) {
        AlertDialogActivity.startActivity();
    }

    /**
     * click: progressDialogTV
     * 进度弹出框
     */
    @ViewClick(ids = R.id.progressDialogTV) private void progressDialogTV(View v) {
        ProgressDialogActivity.startActivity();
    }

    /**
     * click: disableTouchTV
     * 禁用触摸
     */
    @ViewClick(ids = R.id.disableTouchTV) private void disableTouchTV(View v) {
        DisableTouchActivity.startActivity();
    }

    /**
     * click: quickPickTV
     * 快速选择弹窗
     */
    @ViewClick(ids = R.id.quickPickTV) private void quickPickTV(View v) {
        QuickPickActivity.startActivity();
    }
}