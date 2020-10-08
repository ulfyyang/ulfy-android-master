package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ulfy.android.adapter.PagerViewAdapter;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.views.ViewPager;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.VerticalViewPagerVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_vertical_view_pager)
public class VerticalViewPagerView extends BaseView {
    @ViewById(id = R.id.contentVVP) private ViewPager contentVVP;
    @ViewById(id = R.id.page1TV) private TextView page1TV;
    @ViewById(id = R.id.page2TV) private TextView page2TV;
    @ViewById(id = R.id.page3TV) private TextView page3TV;
    private PagerViewAdapter adapter = new PagerViewAdapter();
    private VerticalViewPagerVM vm;

    public VerticalViewPagerView(Context context) {
        super(context);
        init(context, null);
    }

    public VerticalViewPagerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        contentVVP.setVertical(true);       // 设置是否为纵向的
        contentVVP.setAdapter(adapter);
    }

    @Override public void bind(IViewModel model) {
        vm = (VerticalViewPagerVM) model;
        adapter.setViewList(page1TV, page2TV, page3TV);
        adapter.notifyDataSetChanged();
    }
}