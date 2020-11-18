package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ulfy.android.dialog.DialogConfig;
import com.ulfy.android.dialog.DialogUtils;
import com.ulfy.android.dialog.NormalDialog;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_transponder.SilentProcesser;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.DialogProcessVM;
import com.ulfy.master.ui.base.BaseView;
import com.ulfy.master.ui.custom.CircleProgressView;

@Layout(id = R.layout.view_dialog_process)
public class DialogProcessView extends BaseView {
    @ViewById(id = R.id.startBT) private Button startBT;
    @ViewById(id = R.id.progressDialogFL) private FrameLayout progressDialogFL;
    @ViewById(id = R.id.statusCPV) private CircleProgressView statusCPV;
    @ViewById(id = R.id.closeTV) private TextView closeTV;
    private DialogProcessVM vm;

    public DialogProcessView(Context context) {
        super(context);
        init(context, null);
    }

    public DialogProcessView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (DialogProcessVM) model;
    }

    @ViewClick(ids = R.id.startBT) private void startBT(View v) {
        vm.cancel = false;
        TaskUtils.loadData(getContext(), vm.processOnExe(), new SilentProcesser() {
                    @Override protected void onStart(Object data) {
                        statusCPV.setProgress(0);
                        new NormalDialog.Builder(getContext(), progressDialogFL)
                                .setDialogId(DialogConfig.ULFY_MAIN_DIALOG_ID)
                                .setTouchOutsideDismiss(false)
                                .setCancelable(false)
                                .build().show();
                    }
                    @Override protected void onUpdate(Object data) {
                        statusCPV.setProgress((int) data);
                    }
                    @Override protected void onFinish(Object data) {
                        DialogUtils.dismissDialog();
                    }
                }
        );
    }

    @ViewClick(ids = R.id.closeTV) private void closeTV(View v) {
        vm.cancel = true;
    }
}