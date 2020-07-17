package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ulfy.android.dialog.DialogUtils;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.PopupDialogVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_popup_dialog)
public class PopupDialogView extends BaseView {
    @ViewById(id = R.id.popupDialogBT) private Button popupDialogBT;
    @ViewById(id = R.id.archorTV) private TextView archorTV;
    @ViewById(id = R.id.demoDialogFL) private FrameLayout demoDialogFL;
    @ViewById(id = R.id.demoDialogCloseBT) private Button demoDialogCloseBT;
    private PopupDialogVM vm;

    public PopupDialogView(Context context) {
        super(context);
        init(context, null);
    }

    public PopupDialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (PopupDialogVM) model;
    }

    @ViewClick(ids = R.id.popupDialogBT) private void popupDialogBT(View v) {
        DialogUtils.showPopupDialog(getContext(), demoDialogFL, archorTV);
    }

    @ViewClick(ids = R.id.demoDialogCloseBT) private void demoDialogCloseBT(View v) {
        DialogUtils.dismissPopupDialog();
    }
}