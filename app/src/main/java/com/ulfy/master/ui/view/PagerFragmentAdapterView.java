package com.ulfy.master.ui.view;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.ulfy.android.adapter.PagerFragmentAdapter;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.PagerFragmentAdapterVM;
import com.ulfy.master.ui.base.BaseView;
import com.ulfy.master.ui.fragment.PagerFragmentAdapterFragment;

@Layout(id = R.layout.view_pager_fragment_adapter)
public class PagerFragmentAdapterView extends BaseView {
    @ViewById(id = R.id.contentVP) private ViewPager contentVP;
    private PagerFragmentAdapter fragmentAdapter = new PagerFragmentAdapter((FragmentActivity) getContext());
    private PagerFragmentAdapterVM vm;

    public PagerFragmentAdapterView(Context context) {
        super(context);
        init(context, null);
    }

    public PagerFragmentAdapterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        contentVP.setAdapter(fragmentAdapter);
    }

    @Override public void bind(IViewModel model) {
        vm = (PagerFragmentAdapterVM) model;
        fragmentAdapter.setFragmentList(PagerFragmentAdapterFragment.getInstance("一"), PagerFragmentAdapterFragment.getInstance("二"), PagerFragmentAdapterFragment.getInstance("三"));
        fragmentAdapter.notifyDataSetChanged();
    }
}