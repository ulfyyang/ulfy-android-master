package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.MeVM;
import com.ulfy.master.ui.activity.VideoThumbnailActivity;
import com.ulfy.master.ui.activity.ViewToBitmapActivity;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_me)
public class MeView extends BaseView {
    private MeVM vm;

    public MeView(Context context) {
        super(context);
        init(context, null);
    }

    public MeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (MeVM) model;
    }
    
    /**
     * click: videoThumbailTV
     * 视频截图
     */
    @ViewClick(ids = R.id.videoThumbailTV) private void videoThumbailTV(View v) {
        VideoThumbnailActivity.startActivity();
    }

    /**
     * click: videoToBitmapTV
     * 生成截图
     */
    @ViewClick(ids = R.id.videoToBitmapTV) private void videoToBitmapTV(View v) {
        ViewToBitmapActivity.startActivity();
    }
}