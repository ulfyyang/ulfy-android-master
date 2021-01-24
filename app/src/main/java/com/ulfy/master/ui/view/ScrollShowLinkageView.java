package com.ulfy.master.ui.view;

import android.content.Context;
import androidx.core.widget.NestedScrollView;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_linkage.ScrollShowLinkage;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.ScrollShowLinkageVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_scroll_show_linkage)
public class ScrollShowLinkageView extends BaseView {
    @ViewById(id = R.id.contentNSV) private NestedScrollView contentNSV;
    @ViewById(id = R.id.titleTV) private TextView titleTV;
    @ViewById(id = R.id.titleShowTV) private TextView titleShowTV;
    private ScrollShowLinkageVM vm;

    public ScrollShowLinkageView(Context context) {
        super(context);
        init(context, null);
    }

    public ScrollShowLinkageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /*
        topView、bottomView 是根据其相对位置决定的。页面刚开始显示的时候在上面的为 topView，在下面的为 bottomView
     */
    private void init(Context context, AttributeSet attrs) {
        new ScrollShowLinkage(contentNSV, titleShowTV, titleTV, titleShowTV)
            .setShowStrategy(new ScrollShowLinkage.BottomBottomStrategy());
    }

    @Override public void bind(IViewModel model) {
        vm = (ScrollShowLinkageVM) model;
    }
}