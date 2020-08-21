package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.ImageLoadVM;
import com.ulfy.master.ui.activity.ImagePreviewActivity;
import com.ulfy.master.ui.activity.ImageProcessActivity;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_image_load)
public class ImageLoadView extends BaseView {
    private ImageLoadVM vm;

    public ImageLoadView(Context context) {
        super(context);
        init(context, null);
    }

    public ImageLoadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (ImageLoadVM) model;
    }

    /**
     * click: imageProcessTV
     * 图片处理
     */
    @ViewClick(ids = R.id.imageProcessTV) private void imageProcessTV(View v) {
        ImageProcessActivity.startActivity();
    }
    
    /**
     * click: imagePreviewTV
     * 大图预览
     */
    @ViewClick(ids = R.id.imagePreviewTV) private void imagePreviewTV(View v) {
        ImagePreviewActivity.startActivity();
    }
}