package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.ulfy.android.dialog.DialogUtils;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task_extension.UiTimer;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.ProgressDialogVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_progress_dialog)
public class ProgressDialogView extends BaseView {
    @ViewById(id = R.id.progressBT) private Button progressBT;
    private UiTimer uiTimer = new UiTimer(100);
    private ProgressDialogVM vm;

    public ProgressDialogView(Context context) {
        super(context);
        init(context, null);
    }

    public ProgressDialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        uiTimer.setUiTimerExecuteBody(new UiTimer.UiTimerExecuteBody() {
            @Override public void onExecute(UiTimer timer, UiTimer.TimerDriver timerDriver) {
                int current = ((UiTimer.NumberTimerDriver) timerDriver).getCurrentNumber();
                DialogUtils.showProgressDialog(getContext(), "执行进度", 100, current);
            }
        }).setOnTimerFinishListener(new UiTimer.OnTimerFinishListener() {
            @Override public void onTimerFinish(UiTimer timer, UiTimer.TimerDriver timerDriver) {
                DialogUtils.dismissProgressDialog();
            }
        });
    }

    @Override public void bind(IViewModel model) {
        vm = (ProgressDialogVM) model;
    }

    @ViewClick(ids = R.id.progressBT) private void progressBT(View v) {
        uiTimer.setTimerDriver(new UiTimer.NumberTimerDriver(0, 100, 1, false, false));
        uiTimer.schedule();
    }
}