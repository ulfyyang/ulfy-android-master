package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.ulfy.android.adapter.PagerViewAdapter;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.PagerViewAdapterVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_pager_view_adapter)
public class PagerViewAdapterView extends BaseView {
    @ViewById(id = R.id.contentVP) private ViewPager contentVP;
    @ViewById(id = R.id.page1TV) private TextView page1TV;
    @ViewById(id = R.id.page2TV) private TextView page2TV;
    @ViewById(id = R.id.page3TV) private TextView page3TV;
    @ViewById(id = R.id.page4TV) private TextView page4TV;
    private PagerViewAdapter viewAdapter = new PagerViewAdapter();
    private PagerViewAdapterVM vm;

    public PagerViewAdapterView(Context context) {
        super(context);
        init(context, null);
    }

    public PagerViewAdapterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        contentVP.setAdapter(viewAdapter);
    }

    @Override public void bind(IViewModel model) {
        vm = (PagerViewAdapterVM) model;
        viewAdapter.setViewList(page1TV, page2TV, page3TV, page4TV);
        viewAdapter.notifyDataSetChanged();
    }
}