package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.views.DialogLayout;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.DialogLayoutVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_dialog_layout)
public class DialogLayoutView extends BaseView {
    @ViewById(id = R.id.showBT) private Button showBT;
    @ViewById(id = R.id.hideBT) private Button hideBT;
    @ViewById(id = R.id.dialogDL) private DialogLayout dialogDL;
    private DialogLayoutVM vm;

    public DialogLayoutView(Context context) {
        super(context);
        init(context, null);
    }

    public DialogLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (DialogLayoutVM) model;
    }

    @ViewClick(ids = {R.id.showBT, R.id.hideBT})
    private void option(View v) {
        switch (v.getId()) {
            case R.id.showBT:
                dialogDL.show();
                break;
            case R.id.hideBT:
                dialogDL.hide();
                break;
        }
    }

}