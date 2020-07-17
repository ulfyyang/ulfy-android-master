package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.TaskExtensionVM;
import com.ulfy.master.ui.activity.BackgrounderActivity;
import com.ulfy.master.ui.activity.TimerActivity;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_task_extension)
public class TaskExtensionView extends BaseView {
    @ViewById(id = R.id.timerTV) private TextView timerTV;
    @ViewById(id = R.id.backgrounderTV) private TextView backgrounderTV;
    private TaskExtensionVM vm;

    public TaskExtensionView(Context context) {
        super(context);
        init(context, null);
    }

    public TaskExtensionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (TaskExtensionVM) model;
    }
    
    /**
     * click: timerTV
     * 定时器
     */
    @ViewClick(ids = R.id.timerTV) private void timerTV(View v) {
        TimerActivity.startActivity();
    }
    
    /**
     * click: backgrounderTV
     * 后台任务
     */
    @ViewClick(ids = R.id.backgrounderTV) private void backgrounderTV(View v) {
        BackgrounderActivity.startActivity();
    }
}