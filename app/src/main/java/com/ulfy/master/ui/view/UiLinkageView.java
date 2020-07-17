package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.UiLinkageVM;
import com.ulfy.master.ui.activity.EditTextClearLinkageActivity;
import com.ulfy.master.ui.activity.EditTextWordCountLinkageActivity;
import com.ulfy.master.ui.activity.MultiEditTextNotEmptyLinkageActivity;
import com.ulfy.master.ui.activity.PasswordShowHideLinkageActivity;
import com.ulfy.master.ui.activity.ScrollAlphaLinkageActivity;
import com.ulfy.master.ui.activity.ScrollShowLinkageActivity;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_ui_linkage)
public class UiLinkageView extends BaseView {
    private UiLinkageVM vm;

    public UiLinkageView(Context context) {
        super(context);
        init(context, null);
    }

    public UiLinkageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (UiLinkageVM) model;
    }

    /**
     * click: editTextClearLinkageTV
     * 输入框清空
     */
    @ViewClick(ids = R.id.editTextClearLinkageTV) private void editTextClearLinkageTV(View v) {
        EditTextClearLinkageActivity.startActivity();
    }
    
    /**
     * click: editTextWordCountLinkageTV
     * 输入框字数统计
     */
    @ViewClick(ids = R.id.editTextWordCountLinkageTV) private void editTextWordCountLinkageTV(View v) {
        EditTextWordCountLinkageActivity.startActivity();
    }

    /**
     * click: multiEditTextNotEmptyLinkageTV
     * 多输入框非空
     */
    @ViewClick(ids = R.id.multiEditTextNotEmptyLinkageTV) private void multiEditTextNotEmptyLinkageTV(View v) {
        MultiEditTextNotEmptyLinkageActivity.startActivity();
    }

    /**
     * click: passwordShowHideLinkageTV
     * 密码可见性
     */
    @ViewClick(ids = R.id.passwordShowHideLinkageTV) private void passwordShowHideLinkageTV(View v) {
        PasswordShowHideLinkageActivity.startActivity();
    }

    /**
     * click: scrollAlphaLinkageTV
     * 滚动透明度
     */
    @ViewClick(ids = R.id.scrollAlphaLinkageTV) private void scrollAlphaLinkageTV(View v) {
        ScrollAlphaLinkageActivity.startActivity();
    }
    
    /**
     * click: scrollShowLinkageTV
     * 滚动可见性
     */
    @ViewClick(ids = R.id.scrollShowLinkageTV) private void scrollShowLinkageTV(View v) {
        ScrollShowLinkageActivity.startActivity();
    }
}