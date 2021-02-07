package com.ulfy.master.ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.ViewToBitmapViewVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_view_to_bitmap_view)
public class ViewToBitmapViewView extends BaseView {
    @ViewById(id = R.id.viewToBitmapIV) private ImageView viewToBitmapIV;
    private ViewToBitmapViewVM vm;

    public ViewToBitmapViewView(Context context) {
        super(context);
        init(context, null);
    }

    public ViewToBitmapViewView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (ViewToBitmapViewVM) model;
        Glide.with(getContext()).load(vm.pictureUrl).into(new SimpleTarget<Drawable>() {
            @Override public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                viewToBitmapIV.setImageDrawable(resource);
            }
        });
    }
}