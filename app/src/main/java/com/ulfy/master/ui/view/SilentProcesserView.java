package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_transponder.SilentProcesser;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.SilentProcesserVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_silent_processer)
public class SilentProcesserView extends BaseView {
    @ViewById(id = R.id.silentProcesserBT) private Button silentProcesserBT;
    @ViewById(id = R.id.contentTV) private TextView contentTV;
    private SilentProcesserVM vm;

    public SilentProcesserView(Context context) {
        super(context);
        init(context, null);
    }

    public SilentProcesserView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (SilentProcesserVM) model;
    }
    
    /**
     * click: silentProcesserBT
     * 静默处理
     */
    @ViewClick(ids = R.id.silentProcesserBT) private void silentProcesserBT(View v) {
        TaskUtils.loadData(getContext(), vm.processDataOnExe(), new SilentProcesser() {
                    @Override public void onSuccess(Object tipData) {
                        contentTV.setText(vm.content);
                    }
                }
        );
    }
}