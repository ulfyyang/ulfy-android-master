package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.ChargeVM;
import com.ulfy.master.ui.activity.DouyinActivity;
import com.ulfy.master.ui.activity.MakeGifActivity;
import com.ulfy.master.ui.activity.QRCodeActivity;
import com.ulfy.master.ui.activity.VideoActivity;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_charge)
public class ChargeView extends BaseView {
    private ChargeVM vm;

    public ChargeView(Context context) {
        super(context);
        init(context, null);
    }

    public ChargeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (ChargeVM) model;
    }

    /**
     * click: douyinTV
     * 仿抖音
     */
    @ViewClick(ids = R.id.douyinTV) private void douyinTV(View v) {
        DouyinActivity.startActivity();
    }

    /**
     * click: videoTV
     * 视频列表
     */
    @ViewClick(ids = R.id.videoTV) private void videoTV(View v) {
        VideoActivity.startActivity();
    }
    
    /**
     * click: makeGifTV
     * 生成GIF动图
     */
    @ViewClick(ids = R.id.makeGifTV) private void makeGifTV(View v) {
        MakeGifActivity.startActivity();
    }

    /**
     * click: qrCodeTV
     * 二维码
     */
    @ViewClick(ids = R.id.qrCodeTV) private void qrCodeTV(View v) {
        QRCodeActivity.startActivity();
    }
}