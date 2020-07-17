package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.views.RatioLayout;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.RatioLayoutVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_ratio_layout)
public class RatioLayoutView extends BaseView {
    @ViewById(id = R.id.ratio21BT) private Button ratio21BT;
    @ViewById(id = R.id.ratio11BT) private Button ratio11BT;
    @ViewById(id = R.id.ratioRL) private RatioLayout ratioRL;
    private RatioLayoutVM vm;

    public RatioLayoutView(Context context) {
        super(context);
        init(context, null);
    }

    public RatioLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (RatioLayoutVM) model;
    }

    @ViewClick(ids = {R.id.ratio21BT, R.id.ratio11BT})
    private void changeRatio(View v) {
        switch (v.getId()) {
            case R.id.ratio21BT:
                ratioRL.setRatio(2, 1);
                break;
            case R.id.ratio11BT:
                ratioRL.setWidthRatio(1);
                ratioRL.setHeightRatio(1);
                break;
        }
    }
}