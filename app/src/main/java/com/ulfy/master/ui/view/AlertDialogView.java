package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.ulfy.android.dialog.AlertDialog;
import com.ulfy.android.dialog.DialogUtils;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.AlertDialogVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_alert_dialog)
public class AlertDialogView extends BaseView {
    @ViewById(id = R.id.oneButtonTipBT) private Button oneButtonTipBT;
    @ViewById(id = R.id.oneButtonOperationBT) private Button oneButtonOperationBT;
    @ViewById(id = R.id.twoButtonOperationBT) private Button twoButtonOperationBT;
    private AlertDialogVM vm;

    public AlertDialogView(Context context) {
        super(context);
        init(context, null);
    }

    public AlertDialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (AlertDialogVM) model;
    }

    /*
    如果弹出框的标题内容为空，则不会显示该标题
     */

    @ViewClick(ids = {R.id.oneButtonTipBT, R.id.oneButtonOperationBT, R.id.twoButtonOperationBT})
    private void alertOperations(View v) {
        switch (v.getId()) {
            case R.id.oneButtonTipBT:
                DialogUtils.showAlertOneButtonDialog(getContext(), "弹出框标题", "弹出框内容");
                break;
            case R.id.oneButtonOperationBT:
                DialogUtils.showAlertOneButtonDialog(getContext(), "弹出框标题", "弹出框内容", new AlertDialog.OnClickAlertOkListener() {
                    @Override public void onClickAlertOk(android.app.AlertDialog dialog) {
                        UiUtils.show("点击了确定");
                    }
                });
                break;
            case R.id.twoButtonOperationBT:
                DialogUtils.showAlertTwoButtonDialog(getContext(), "弹出框标题", "弹出框内容", new AlertDialog.OnClickAlertOkListener() {
                    @Override public void onClickAlertOk(android.app.AlertDialog dialog) {
                        UiUtils.show("点击了确定");
                    }
                });
                break;
        }
    }

}