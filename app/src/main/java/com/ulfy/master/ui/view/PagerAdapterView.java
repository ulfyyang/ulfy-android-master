package com.ulfy.master.ui.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.ulfy.android.adapter.PagerAdapter;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.PagerAdapterCM;
import com.ulfy.master.application.vm.PagerAdapterVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_pager_adapter)
public class PagerAdapterView extends BaseView {
    @ViewById(id = R.id.contentVP) private ViewPager contentVP;
    private PagerAdapter<PagerAdapterCM> pagerAdapter = new PagerAdapter<>();
    private PagerAdapterVM vm;

    public PagerAdapterView(Context context) {
        super(context);
        init(context, null);
    }

    public PagerAdapterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        contentVP.setOffscreenPageLimit(2);     // 对于这种从模型中动态生成的情况，增大保留数量可以增加流畅性
        contentVP.setAdapter(pagerAdapter);
    }

    @Override public void bind(IViewModel model) {
        vm = (PagerAdapterVM) model;
        pagerAdapter.setData(vm.pagerCMList);
        pagerAdapter.notifyDataSetChanged();
    }
}