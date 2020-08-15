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
import com.ulfy.master.application.vm.SmartTransponderVM;
import com.ulfy.master.ui.activity.SmartCustomHeaderActivity;
import com.ulfy.master.ui.activity.SmartLoadDataActivity;
import com.ulfy.master.ui.activity.SmartLoadListPageActivity;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_smart_transponder)
public class SmartTransponderView extends BaseView {
    @ViewById(id = R.id.loadDataTV) private TextView loadDataTV;
    @ViewById(id = R.id.loadListPageTV) private TextView loadListPageTV;
    private SmartTransponderVM vm;

    public SmartTransponderView(Context context) {
        super(context);
        init(context, null);
    }

    public SmartTransponderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (SmartTransponderVM) model;
    }
    
    /**
     * click: loadDataTV
     * 加载数据刷新
     */
    @ViewClick(ids = R.id.loadDataTV) private void loadDataTV(View v) {
        SmartLoadDataActivity.startActivity();
    }
    
    /**
     * click: loadListPageTV
     * 分页数据刷新
     */
    @ViewClick(ids = R.id.loadListPageTV) private void loadListPageTV(View v) {
        SmartLoadListPageActivity.startActivity();
    }

    /**
     * click: customHeaderTV
     * 定制刷新头
     */
    @ViewClick(ids = R.id.customHeaderTV) private void customHeaderTV(View v) {
        SmartCustomHeaderActivity.startActivity();
    }
}