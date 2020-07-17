package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.AdapterVM;
import com.ulfy.master.ui.activity.PagerAdapterActivity;
import com.ulfy.master.ui.activity.PagerFragmentAdapterActivity;
import com.ulfy.master.ui.activity.ListAdapterActivity;
import com.ulfy.master.ui.activity.PagerViewAdapterActivity;
import com.ulfy.master.ui.activity.RecyclerAdapterActivity;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_adapter)
public class AdapterView extends BaseView {
    private AdapterVM vm;

    public AdapterView(Context context) {
        super(context);
        init(context, null);
    }

    public AdapterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (AdapterVM) model;
    }

    /**
     * click: pagerViewAdapterTV
     * PagerView 适配器
     */
    @ViewClick(ids = R.id.pagerViewAdapterTV) private void pagerViewAdapterTV(View v) {
        PagerViewAdapterActivity.startActivity();
    }

    /**
     * click: pagerFragmentAdapterTV
     * PagerFragment 适配器
     */
    @ViewClick(ids = R.id.pagerFragmentAdapterTV) private void pagerFragmentAdapterTV(View v) {
        PagerFragmentAdapterActivity.startActivity();
    }

    /**
     * click: pagerAdapterTV
     * Pager 适配器
     */
    @ViewClick(ids = R.id.pagerAdapterTV) private void pagerAdapterTV(View v) {
        PagerAdapterActivity.startActivity();
    }

    /**
     * click: listAdapterTV
     * List适配器
     */
    @ViewClick(ids = R.id.listAdapterTV) private void listAdapterTV(View v) {
        ListAdapterActivity.startActivity();
    }

    /**
     * click: recyclerAdapterTV
     * Recycler适配器
     */
    @ViewClick(ids = R.id.recyclerAdapterTV) private void recyclerAdapterTV(View v) {
        RecyclerAdapterActivity.startActivity();
    }
}