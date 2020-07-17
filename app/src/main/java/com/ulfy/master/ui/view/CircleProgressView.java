package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task_extension.UiTimer;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.CircleProgressVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_circle_progress)
public class CircleProgressView extends BaseView {
    @ViewById(id = R.id.circleCPV) private com.ulfy.master.ui.custom.CircleProgressView circleCPV;
    private UiTimer uiTimer = new UiTimer(20);
    private CircleProgressVM vm;

    public CircleProgressView(Context context) {
        super(context);
        init(context, null);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        uiTimer.setTimerDriver(new UiTimer.NumberTimerDriver(0, 1000, 1, true, true));
        uiTimer.setUiTimerExecuteBody(new UiTimer.UiTimerExecuteBody() {
            @Override public void onExecute(UiTimer timer, UiTimer.TimerDriver timerDriver) {
                circleCPV.setProgress(((UiTimer.NumberTimerDriver)timerDriver).getCurrentNumber());
            }
        });
    }

    @Override public void bind(IViewModel model) {
        vm = (CircleProgressVM) model;
        initViews();
        uiTimer.schedule();
    }

    private void initViews() {
        circleCPV.setProgress(80);
        circleCPV.setTextTop("Text1");
        circleCPV.setTextBottom("Text2");
    }

    @Override protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        uiTimer.cancel();
    }
}