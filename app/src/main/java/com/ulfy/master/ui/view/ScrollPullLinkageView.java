package com.ulfy.master.ui.view;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_linkage.ScrollPullLinkage;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.ScrollPullLinkageVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_scroll_pull_linkage)
public class ScrollPullLinkageView extends BaseView {
    @ViewById(id = R.id.contentNSV) private NestedScrollView contentNSV;
    @ViewById(id = R.id.floatBarTV) private TextView floatBarTV;
    private ScrollPullLinkageVM vm;

    public ScrollPullLinkageView(Context context) {
        super(context);
        init(context, null);
    }

    public ScrollPullLinkageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /*
    支持 NestedScrollView、RecyclerView，NestedScrollView 只支持一个滚动监听
    */
    private void init(Context context, AttributeSet attrs) {
        new ScrollPullLinkage(contentNSV, floatBarTV, 0.5f);
    }

    @Override public void bind(IViewModel model) {
        vm = (ScrollPullLinkageVM) model;
    }
}