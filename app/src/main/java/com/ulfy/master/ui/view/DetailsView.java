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
import com.ulfy.master.application.vm.DetailsVM;
import com.ulfy.master.ui.activity.Details1Activity;
import com.ulfy.master.ui.activity.Details2Activity;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_details)
public class DetailsView extends BaseView {
    @ViewById(id = R.id.details1TV) private TextView details1TV;
    @ViewById(id = R.id.details2TV) private TextView details2TV;
    private DetailsVM vm;

    public DetailsView(Context context) {
        super(context);
        init(context, null);
    }

    public DetailsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (DetailsVM) model;
    }

    /**
     * click: details1TV
     * 详情页一
     */
    @ViewClick(ids = R.id.details1TV) private void details1TV(View v) {
        Details1Activity.startActivity();
    }

    /**
     * click: details2TV
     * 详情页二
     */
    @ViewClick(ids = R.id.details2TV) private void details2TV(View v) {
        Details2Activity.startActivity();
    }
}