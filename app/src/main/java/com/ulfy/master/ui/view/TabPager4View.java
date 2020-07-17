package com.ulfy.master.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.TabPager4VM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_tab_pager4)
public class TabPager4View extends BaseView {
    @ViewById(id = R.id.type1LL) private LinearLayout type1LL;
    @ViewById(id = R.id.type1TV) private TextView type1TV;
    @ViewById(id = R.id.type1V) private View type1V;
    @ViewById(id = R.id.type2LL) private LinearLayout type2LL;
    @ViewById(id = R.id.type2TV) private TextView type2TV;
    @ViewById(id = R.id.type2V) private View type2V;
    @ViewById(id = R.id.page1FL) private FrameLayout page1FL;
    @ViewById(id = R.id.page2FL) private FrameLayout page2FL;
    private TabPager4VM vm;

    public TabPager4View(Context context) {
        super(context);
        init(context, null);
    }

    public TabPager4View(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (TabPager4VM) model;
        updatePageUI(0);
    }

    /**
     * 更换选中的标签页
     * @param index 索引
     */
    private void updatePageUI(int index) {
        type1TV.setTextColor(Color.BLACK);
        type1V.setVisibility(View.INVISIBLE);
        page1FL.setVisibility(View.INVISIBLE);
        type2TV.setTextColor(Color.BLACK);
        type2V.setVisibility(View.INVISIBLE);
        page2FL.setVisibility(View.INVISIBLE);

        switch (index) {
            case 0:
                type1TV.setTextColor(Color.parseColor("#DA1C36"));
                type1V.setVisibility(View.VISIBLE);
                page1FL.setVisibility(View.VISIBLE);
                break;
            case 1:
                type2TV.setTextColor(Color.parseColor("#DA1C36"));
                type2V.setVisibility(View.VISIBLE);
                page2FL.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * 点击标签页切换显示页面
     */
    @ViewClick(ids = {R.id.type1LL, R.id.type2LL})
    private void selectPage(View v) {
        switch (v.getId()) {
            case R.id.type1LL:
                updatePageUI(0);
                break;
            case R.id.type2LL:
                updatePageUI(1);
                break;
        }
    }
}