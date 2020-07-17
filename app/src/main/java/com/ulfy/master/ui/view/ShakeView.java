package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ulfy.android.bus.Subscribe;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.system.ActivityUtils;
import com.ulfy.android.system.event.OnDeviceShakedEvent;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.ShakeVM;
import com.ulfy.master.ui.base.BaseView;

import java.util.Random;

@Layout(id = R.layout.view_shake)
public class ShakeView extends BaseView {
    @ViewById(id = R.id.enableBT) private Button enableBT;
    @ViewById(id = R.id.disableBT) private Button disableBT;
    @ViewById(id = R.id.contentTV) private TextView contentTV;
    private ShakeVM vm;

    public ShakeView(Context context) {
        super(context);
        init(context, null);
    }

    public ShakeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (ShakeVM) model;
    }

    @ViewClick(ids = R.id.enableBT) private void enableBT(View v) {
        ActivityUtils.enableShake(getContext(), true);
    }

    @ViewClick(ids = R.id.disableBT) private void disableBT(View v) {
        ActivityUtils.enableShake(getContext(), false);
    }

    @Subscribe private void OnDeviceShakedEvent(OnDeviceShakedEvent event) {
        contentTV.setText(String.format("摇一摇结果：%d", new Random().nextInt(10)));
    }
}