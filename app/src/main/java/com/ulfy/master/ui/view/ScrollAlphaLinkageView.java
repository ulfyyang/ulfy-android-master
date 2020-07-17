package com.ulfy.master.ui.view;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_linkage.ScrollAlphaLinkage;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.ScrollAlphaLinkageVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_scroll_alpha_linkage)
public class ScrollAlphaLinkageView extends BaseView {
    @ViewById(id = R.id.contentNSV) private NestedScrollView contentNSV;
    @ViewById(id = R.id.titleTV) private TextView titleTV;
    private ScrollAlphaLinkageVM vm;

    public ScrollAlphaLinkageView(Context context) {
        super(context);
        init(context, null);
    }

    public ScrollAlphaLinkageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /*
        支持 NestedScrollView、RecyclerView，NestedScrollView 只支持一个滚动监听
        支持设置 View 透明度的部分：背景、整体
     */
    private void init(Context context, AttributeSet attrs) {
        new ScrollAlphaLinkage(contentNSV, titleTV, ScrollAlphaLinkage.TYPE_VIEW);
    }

    @Override public void bind(IViewModel model) {
        vm = (ScrollAlphaLinkageVM) model;
    }
}