package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.ulfy.android.dialog.DialogConfig;
import com.ulfy.android.dialog.DialogUtils;
import com.ulfy.android.dialog.NormalDialog;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.NormalDialogVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_normal_dialog)
public class NormalDialogView extends BaseView {
    @ViewById(id = R.id.normalDialogBT) private Button normalDialogBT;
    @ViewById(id = R.id.topDialogBT) private Button topDialogBT;
    @ViewById(id = R.id.bottomDialogBT) private Button bottomDialogBT;
    @ViewById(id = R.id.bottomSheetDialogBT) private Button bottomSheetDialogBT;
    @ViewById(id = R.id.bottomSheetNoBackgroundDialogBT) private Button bottomSheetNoBackgroundDialogBT;
    @ViewById(id = R.id.customDialogBT) private Button customDialogBT;
    @ViewById(id = R.id.demoDialogFL) private FrameLayout demoDialogFL;
    @ViewById(id = R.id.demoDialogCloseBT) private Button demoDialogCloseBT;
    @ViewById(id = R.id.inputET) private EditText inputET;
    private NormalDialogVM vm;

    public NormalDialogView(Context context) {
        super(context);
        init(context, null);
    }

    public NormalDialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (NormalDialogVM) model;
    }

    @ViewClick(ids = {R.id.normalDialogBT, R.id.topDialogBT, R.id.bottomDialogBT, R.id.customDialogBT})
    private void showDialog(View v) {
        switch (v.getId()) {
            case R.id.normalDialogBT:
                DialogUtils.showDialog(getContext(), demoDialogFL);
                break;
            case R.id.topDialogBT:
                DialogUtils.showTopDialog(getContext(), demoDialogFL);
                break;
            case R.id.bottomDialogBT:
                DialogUtils.showBottomDialog(getContext(), demoDialogFL);
                break;
            case R.id.customDialogBT:
                new NormalDialog.Builder(getContext(), demoDialogFL)
                        .setDialogId(DialogConfig.ULFY_MAIN_DIALOG_ID)
                        .setNoBackground(false).setFullDialog(false)
                        .setGravity(Gravity.LEFT).setTouchOutsideDismiss(false)
                        .setCancelable(false).setDialogAnimationId(DialogConfig.DIALOG_ANIMATION_ID_BOTTOM)
                        .setOnDialogShowListener(new NormalDialog.OnDialogShowListener() {
                            @Override public void onDialogShow(NormalDialog dialog) {
                                UiUtils.show("弹出框显示了");
                            }
                        })
                        .setOnDialogDismissListener(new NormalDialog.OnDialogDismissListener() {
                            @Override public void onDialogDismiss(NormalDialog dialog) {
                                UiUtils.show("弹出框消失了");
                            }
                        }).build().show();
                break;
        }
    }

    @ViewClick(ids = {R.id.bottomSheetDialogBT, R.id.bottomSheetNoBackgroundDialogBT})
    private void showBottomSheetDialog(View v) {
        switch (v.getId()) {
            case R.id.bottomSheetDialogBT:
                DialogUtils.showBottomSheetDialog(getContext(), demoDialogFL);
                break;
            case R.id.bottomSheetNoBackgroundDialogBT:
                DialogUtils.showBottomSheetDialog(getContext(), demoDialogFL, true);
                break;
        }
    }

    @ViewClick(ids = R.id.demoDialogCloseBT) private void demoDialogCloseBT(View v) {
        DialogUtils.showDialog(getContext(), "asdba", inputET);
//        DialogUtils.dismissDialog();
    }
}