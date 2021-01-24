package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_linkage.OnTabSelectedListener;
import com.ulfy.android.ui_linkage.PagerLinkage;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.TabPager3VM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_tab_pager3)
public class TabPager3View extends BaseView {
    @ViewById(id = R.id.titleLL) private LinearLayout titleLL;
    @ViewById(id = R.id.recommendLL) private LinearLayout recommendLL;
    @ViewById(id = R.id.recommendTV) private TextView recommendTV;
    @ViewById(id = R.id.focusLL) private LinearLayout focusLL;
    @ViewById(id = R.id.focusTV) private TextView focusTV;
    @ViewById(id = R.id.containerVP) private ViewPager containerVP;
    @ViewById(id = R.id.containerFL) private FrameLayout containerFL;
    @ViewById(id = R.id.page1FL) private FrameLayout page1FL;
    @ViewById(id = R.id.page2FL) private FrameLayout page2FL;
    private PagerLinkage linkage = new PagerLinkage();
    private TabPager3VM vm;

    public TabPager3View(Context context) {
        super(context);
        init(context, null);
    }

    public TabPager3View(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /*
        注意事项：
            1. 显示容器支持 ViewGroup、ViewPager
            2. 标签只支持 自定义View
            3. 标签页支持View和Fragment
            4. 标签显示样式由 select 属性设定，可以通过开发 selector 资源自定义显示的样式
            5. 通过 ClickFilter 可以在 Tab 切换之前进行拦截
            6. 更加灵活的定制可以在标签选中回调中动态调整
     */

    private void init(Context context, AttributeSet attrs) {
        linkage.setContainer(containerFL).setClickFilter(new PagerLinkage.ClickFilter() {
            /**
             * 该方法用于限制标签是否可以点击
             *      返回 true 则点击标签会执行页面切换操作及相应的回调，返回 false 则不作任何处理
             *      该方法只能对标签点击进行限制，无法限制 ViewPager 的滑动和 select() 方法做出的更改
             */
            @Override public boolean canClick(int index) {
                return true;
            }
        }).addOnTabSelectedListener(new OnTabSelectedListener() {
            @Override public void onTabSelected(int index) {
                UiUtils.show("选中了标签页：" + index);
            }
        });
    }

    @Override public void bind(IViewModel model) {
        vm = (TabPager3VM) model;
        linkage.initViewTabs(recommendLL, focusLL).initViewPages(page1FL, page2FL)
                .build().select(0);
    }
}