package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_linkage.EditTextClearLinkage;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.EditTextClearLinkageVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_edit_text_clear_linkage)
public class EditTextClearLinkageView extends BaseView {
    @ViewById(id = R.id.contentET) private EditText contentET;
    @ViewById(id = R.id.clearIV) private ImageView clearIV;
    private EditTextClearLinkageVM vm;

    public EditTextClearLinkageView(Context context) {
        super(context);
        init(context, null);
    }

    public EditTextClearLinkageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        new EditTextClearLinkage(contentET, clearIV)
            .setOnClearClickListener(new EditTextClearLinkage.OnClearClickListener() {
                @Override public void onClearClick(EditTextClearLinkage linkage, View cliearView) {
                    UiUtils.show("点击了清除按钮");
                }
            })
            .setOnClearListener(new EditTextClearLinkage.OnClearListener() {
                @Override public void onClear(EditTextClearLinkage linkage) {
                    UiUtils.show("内容清除了");
                }
            });
    }

    @Override public void bind(IViewModel model) {
        vm = (EditTextClearLinkageVM) model;
    }
}