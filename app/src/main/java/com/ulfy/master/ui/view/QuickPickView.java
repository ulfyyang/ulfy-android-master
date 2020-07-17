package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.ulfy.android.dialog.DialogUtils;
import com.ulfy.android.dialog.IQuickPickView;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.QuickPickVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_quick_pick)
public class QuickPickView extends BaseView {
    @ViewById(id = R.id.quickPickBT) private Button quickPickBT;
    private QuickPickVM vm;

    public QuickPickView(Context context) {
        super(context);
        init(context, null);
    }

    public QuickPickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (QuickPickVM) model;
    }

    @ViewClick(ids = R.id.quickPickBT) private void quickPickBT(View v) {
        DialogUtils.showQuickPick(getContext(), "快速选择", new IQuickPickView.OnItemClickListener() {
            @Override public void onItemClick(int index, String item) {
                UiUtils.show(String.format("选择了：%d %s", index, item));
            }
        }, "男", "女", "未知");
    }
}