package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task_extension.UiTimer;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.views.ShapeLayout;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.ShapeLayoutVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_shape_layout)
public class ShapeLayoutView extends BaseView {
    @ViewById(id = R.id.shapeSL) private ShapeLayout shapeSL;
    private UiTimer uiTimer = new UiTimer();
    private ShapeLayoutVM vm;

    public ShapeLayoutView(Context context) {
        super(context);
        init(context, null);
    }

    public ShapeLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        uiTimer.setDelay(1);
        uiTimer.setUiTimerExecuteBody(new UiTimer.UiTimerExecuteBody() {
            @Override public void onExecute(UiTimer timer, UiTimer.TimerDriver timerDriver) {
                // 通过代码修改了 ShapeLayout 之后样式会自动更新
                shapeSL.setShapeRect(((UiTimer.NumberTimerDriver)timerDriver).getCurrentNumber());
            }
        }).setTimerDriver(new UiTimer.NumberTimerDriver(0, (int) UiUtils.dp2px(60), 4, true, true));
    }

    @Override public void bind(IViewModel model) {
        vm = (ShapeLayoutVM) model;
    }

    @Override protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        uiTimer.schedule();
    }

    @Override protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        uiTimer.cancel();
    }
}