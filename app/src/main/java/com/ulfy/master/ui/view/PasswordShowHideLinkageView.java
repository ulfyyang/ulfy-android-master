package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.ImageView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_linkage.PasswordShowHideLinkage;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.PasswordShowHideLinkageVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_password_show_hide_linkage)
public class PasswordShowHideLinkageView extends BaseView {
    @ViewById(id = R.id.contentET) private EditText contentET;
    @ViewById(id = R.id.eyeIV) private ImageView eyeIV;
    private PasswordShowHideLinkageVM vm;

    public PasswordShowHideLinkageView(Context context) {
        super(context);
        init(context, null);
    }

    public PasswordShowHideLinkageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        new PasswordShowHideLinkage(R.drawable.eye_open, R.drawable.eye_close, contentET, eyeIV);
    }

    @Override public void bind(IViewModel model) {
        vm = (PasswordShowHideLinkageVM) model;
    }
}