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
import com.ulfy.master.application.vm.TabPagerVM;
import com.ulfy.master.ui.activity.TabPager1Activity;
import com.ulfy.master.ui.activity.TabPager2Activity;
import com.ulfy.master.ui.activity.TabPager3Activity;
import com.ulfy.master.ui.activity.TabPager4Activity;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_tab_pager)
public class TabPagerView extends BaseView {
    @ViewById(id = R.id.tabPager1TV) private TextView tabPage1TV;
    @ViewById(id = R.id.tabPager2TV) private TextView tabPage2TV;
    @ViewById(id = R.id.tabPager3TV) private TextView tabPage3TV;
    @ViewById(id = R.id.tabPager4TV) private TextView tabPage4TV;
    private TabPagerVM vm;

    public TabPagerView(Context context) {
        super(context);
        init(context, null);
    }

    public TabPagerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (TabPagerVM) model;
    }

    /**
     * click: tabPage1TV
     * 标签页分页1
     */
    @ViewClick(ids = R.id.tabPager1TV) private void tabPage1TV(View v) {
        TabPager1Activity.startActivity();
    }

    /**
     * click: tabPage2TV
     * 标签页分页2
     */
    @ViewClick(ids = R.id.tabPager2TV) private void tabPage2TV(View v) {
        TabPager2Activity.startActivity();
    }

    /**
     * click: tabPage3TV
     * 标签页分页3
     */
    @ViewClick(ids = R.id.tabPager3TV) private void tabPage3TV(View v) {
        TabPager3Activity.startActivity();
    }

    /**
     * click: tabPage4TV
     * 标签页分页4
     */
    @ViewClick(ids = R.id.tabPager4TV) private void tabPage4TV(View v) {
        TabPager4Activity.startActivity();
    }
}