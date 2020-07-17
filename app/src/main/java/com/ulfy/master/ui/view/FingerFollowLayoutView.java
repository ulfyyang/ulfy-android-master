package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.FingerFollowLayoutVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_finger_follow_layout)
public class FingerFollowLayoutView extends BaseView {
    @ViewById(id = R.id.content1FL) private FrameLayout content1FL;
    @ViewById(id = R.id.content2FL) private FrameLayout content2FL;
    private FingerFollowLayoutVM vm;

    public FingerFollowLayoutView(Context context) {
        super(context);
        init(context, null);
    }

    public FingerFollowLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (FingerFollowLayoutVM) model;
    }

    @ViewClick(ids = R.id.content1FL) private void content1FL(View v) {
        UiUtils.show("点击了不自动贴边");
    }

    @ViewClick(ids = R.id.content2FL) private void content2FL(View v) {
        UiUtils.show("点击了自动贴边");
    }
}