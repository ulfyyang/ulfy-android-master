package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.ControlsVM;
import com.ulfy.master.ui.activity.CircleProgressActivity;
import com.ulfy.master.ui.activity.XBannerActivity;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_controls)
public class ControlsView extends BaseView {
    private ControlsVM vm;

    public ControlsView(Context context) {
        super(context);
        init(context, null);
    }

    public ControlsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (ControlsVM) model;
    }

    /**
     * click: xBannerTV
     * 轮播图
     */
    @ViewClick(ids = R.id.xBannerTV) private void xBannerTV(View v) {
        XBannerActivity.startActivity();
    }

    /**
     * click: circleProgressTV
     * 圆形进度条
     */
    @ViewClick(ids = R.id.circleProgressTV) private void circleProgressTV(View v) {
        CircleProgressActivity.startActivity();
    }
}