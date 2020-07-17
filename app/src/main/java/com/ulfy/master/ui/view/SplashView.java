package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.SplashVM;
import com.ulfy.master.ui.activity.Splash1Activity;
import com.ulfy.master.ui.activity.Splash2Activity;
import com.ulfy.master.ui.activity.Splash3Activity;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_splash)
public class SplashView extends BaseView {
    @ViewById(id = R.id.splash1TV) private TextView splash1TV;
    @ViewById(id = R.id.splash2TV) private TextView splash2TV;
    @ViewById(id = R.id.splash3TV) private TextView splash3TV;
    private SplashVM vm;

    public SplashView(Context context) {
        super(context);
        init(context, null);
    }

    public SplashView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (SplashVM) model;
    }

    /**
     * click: splash1TV
     * 启动页1
     */
    @ViewClick(ids = R.id.splash1TV) private void splash1TV(View v) {
        Splash1Activity.startActivity();
    }

    /**
     * click: splash2TV
     * 启动页2
     */
    @ViewClick(ids = R.id.splash2TV) private void splash2TV(View v) {
        Splash2Activity.startActivity();
    }

    /**
     * click: splash3TV
     * 启动页3
     */
    @ViewClick(ids = R.id.splash3TV) private void splash3TV(View v) {
        Splash3Activity.startActivity();
    }
}