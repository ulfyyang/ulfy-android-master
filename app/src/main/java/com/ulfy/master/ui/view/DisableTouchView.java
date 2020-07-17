package com.ulfy.master.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.ulfy.android.dialog.DialogUtils;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.DisableTouchVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_disable_touch)
public class DisableTouchView extends BaseView {
    @ViewById(id = R.id.disableWithoutColorBT) private Button disableWithoutColorBT;
    @ViewById(id = R.id.disableWithColorBT) private Button disableWithColorBT;
    private DisableTouchVM vm;

    public DisableTouchView(Context context) {
        super(context);
        init(context, null);
    }

    public DisableTouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (DisableTouchVM) model;
    }


    @ViewClick(ids = {R.id.disableWithoutColorBT, R.id.disableWithColorBT})
    private void disable(View v) {
        switch (v.getId()) {
            case R.id.disableWithoutColorBT:
                DialogUtils.disableTouch(getContext());
                postDelayed(new Runnable() {
                    @Override public void run() {
                        DialogUtils.enableTouch(getContext());
                    }
                }, 3000);
                break;
            case R.id.disableWithColorBT:
                DialogUtils.disableTouch(getContext(), Color.RED);
                postDelayed(new Runnable() {
                    @Override public void run() {
                        DialogUtils.enableTouch(getContext());
                    }
                }, 3000);
                break;
        }
    }

}