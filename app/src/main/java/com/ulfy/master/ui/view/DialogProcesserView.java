package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_transponder.DialogProcesser;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.DialogProcesserVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_dialog_processer)
public class DialogProcesserView extends BaseView {
    @ViewById(id = R.id.dialogProcessBT) private Button dialogProcessBT;
    @ViewById(id = R.id.contentTV) private TextView contentTV;
    private DialogProcesserVM vm;

    public DialogProcesserView(Context context) {
        super(context);
        init(context, null);
    }

    public DialogProcesserView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (DialogProcesserVM) model;
    }

    /**
     * click: dialogProcessBT
     * 弹出框处理
     */
    @ViewClick(ids = R.id.dialogProcessBT) private void dialogProcessBT(View v) {
        TaskUtils.loadData(getContext(), vm.processDataOnExe(), new DialogProcesser(getContext()) {
                    @Override public void onSuccess(Object tipData) {
                        contentTV.setText(vm.content);
                    }
                }
        );
    }
}