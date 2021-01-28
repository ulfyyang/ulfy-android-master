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
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.FailRollbackVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_fail_rollback)
public class FailRollbackView extends BaseView {
    @ViewById(id = R.id.optionBT) private Button optionBT;
    @ViewById(id = R.id.statusTV) private TextView statusTV;
    private FailRollbackVM vm;

    public FailRollbackView(Context context) {
        super(context);
        init(context, null);
    }

    public FailRollbackView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (FailRollbackVM) model;
    }

    /**
     * click: optionBT
     * 点击开始操作，多次重复点击查看效果
     */
    @ViewClick(ids = R.id.optionBT) private void optionBT(View v) {
        TaskUtils.loadData(getContext(), vm.oprationExe(), new SilentProcesser() {
                    @Override protected void onStart(Object data) {
                        statusTV.setText(String.format("当前数字：%d", vm.number));
                    }
                    @Override protected void onFail(Object data) {
                        statusTV.setText(String.format("当前数字：%d", vm.number));
                        UiUtils.show(data);
                    }
                }
        );
    }
}