package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.system.ActivityUtils;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.ReceiveDataVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_receive_data)
public class ReceiveDataView extends BaseView {
    @ViewById(id = R.id.returnDataBT) private Button returnDataBT;
    private ReceiveDataVM vm;

    public ReceiveDataView(Context context) {
        super(context);
        init(context, null);
    }

    public ReceiveDataView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (ReceiveDataVM) model;
    }

    @ViewClick(ids = R.id.returnDataBT) private void returnDataBT(View v) {
        ActivityUtils.returnData("content", "这是返回的内容");
    }
}