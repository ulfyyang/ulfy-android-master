package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ulfy.android.multi_domain_picker.MultiDomainPicker;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_transponder.DialogProcesser;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.MultiDomainPickerVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_multi_domain_picker)
public class MultiDomainPickerView extends BaseView {
    @ViewById(id = R.id.pickBT) private Button pickBT;
    @ViewById(id = R.id.invalidateBT) private Button invalidateBT;
    @ViewById(id = R.id.targetDomainTV) private TextView targetDomainTV;
    private MultiDomainPickerVM vm;

    public MultiDomainPickerView(Context context) {
        super(context);
        init(context, null);
    }

    public MultiDomainPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (MultiDomainPickerVM) model;
    }

    /**
     * click: pickBT
     * 获取可用域名
     */
    @ViewClick(ids = R.id.pickBT) private void pickBT(View v) {
        TaskUtils.loadData(getContext(), vm.domainPickOnExe(), new DialogProcesser(getContext()) {
                    @Override public void onSuccess(Object tipData) {
                        targetDomainTV.setText(String.format("目标域名：%s", vm.targetDomainUrl));
                        UiUtils.show(tipData);
                    }
                }
        );
    }
    
    /**
     * click: invalidateBT
     * 失效当前域名
     */
    @ViewClick(ids = R.id.invalidateBT) private void invalidateBT(View v) {
        MultiDomainPicker.getInstance().invalidateDomain();
    }
}