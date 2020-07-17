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
import com.ulfy.master.application.vm.GuideVM;
import com.ulfy.master.ui.activity.Guide1Activity;
import com.ulfy.master.ui.activity.Guide2Activity;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_guide)
public class GuideView extends BaseView {
    @ViewById(id = R.id.guide1TV) private TextView guide1TV;
    @ViewById(id = R.id.guide2TV) private TextView guide2TV;
    private GuideVM vm;

    public GuideView(Context context) {
        super(context);
        init(context, null);
    }

    public GuideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (GuideVM) model;
    }

    /**
     * click: guide1TV
     * 引导页1
     */
    @ViewClick(ids = R.id.guide1TV) private void guide1TV(View v) {
        Guide1Activity.startActivity();
    }

    /**
     * click: guide2TV
     * 引导页2
     */
    @ViewClick(ids = R.id.guide2TV) private void guide2TV(View v) {
        Guide2Activity.startActivity();
    }
}