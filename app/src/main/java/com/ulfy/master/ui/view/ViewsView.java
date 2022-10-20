package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.ViewsVM;
import com.ulfy.master.ui.activity.AppbarBehaviorActivity;
import com.ulfy.master.ui.activity.AutoScrollActivity;
import com.ulfy.master.ui.activity.AutoScrollUpActivity;
import com.ulfy.master.ui.activity.ChartViewActivity;
import com.ulfy.master.ui.activity.DashLineActivity;
import com.ulfy.master.ui.activity.DialogLayoutActivity;
import com.ulfy.master.ui.activity.FingerFollowLayoutActivity;
import com.ulfy.master.ui.activity.FlowLayoutActivity;
import com.ulfy.master.ui.activity.RatioLayoutActivity;
import com.ulfy.master.ui.activity.ShapeLayoutActivity;
import com.ulfy.master.ui.activity.VerticalViewPagerActivity;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_views)
public class ViewsView extends BaseView {
    private ViewsVM vm;

    public ViewsView(Context context) {
        super(context);
        init(context, null);
    }

    public ViewsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (ViewsVM) model;
    }

    /**
     * click: shapeLayoutTV
     * 形状定制
     */
    @ViewClick(ids = R.id.shapeLayoutTV) private void shapeLayoutTV(View v) {
        ShapeLayoutActivity.startActivity();
    }

    /**
     * click: ratioLayoutTV
     * 比例布局
     */
    @ViewClick(ids = R.id.ratioLayoutTV) private void ratioLayoutTV(View v) {
        RatioLayoutActivity.startActivity();
    }

    /**
     * click: flowLayoutTV
     * 流式布局
     */
    @ViewClick(ids = R.id.flowLayoutTV) private void flowLayoutTV(View v) {
        FlowLayoutActivity.startActivity();
    }

    /**
     * click: fingerFollowLayoutTV
     * 跟随手指移动
     */
    @ViewClick(ids = R.id.fingerFollowLayoutTV) private void fingerFollowLayoutTV(View v) {
        FingerFollowLayoutActivity.startActivity();
    }

    /**
     * click: autoScrollTV
     * 横向滚动
     */
    @ViewClick(ids = R.id.autoScrollTV) private void autoScrollTV(View v) {
        AutoScrollActivity.startActivity();
    }

    /**
     * click: autoScrollUpTV
     * 纵向滚动
     */
    @ViewClick(ids = R.id.autoScrollUpTV) private void autoScrollUpTV(View v) {
        AutoScrollUpActivity.startActivity();
    }

    /**
     * click: verticalViewPagerTV
     * 纵向ViewPager
     */
    @ViewClick(ids = R.id.verticalViewPagerTV) private void verticalViewPagerTV(View v) {
        VerticalViewPagerActivity.startActivity();
    }

    /**
     * click: dialogLayoutTV
     * 弹窗布局
     */
    @ViewClick(ids = R.id.dialogLayoutTV) private void dialogLayoutTV(View v) {
        DialogLayoutActivity.startActivity();
    }

    /**
     * click: dashLineTV
     * 绘制虚线
     */
    @ViewClick(ids = R.id.dashLineTV) private void dashLineTV(View v) {
        DashLineActivity.startActivity();
    }

    /**
     * click: chartViewTV
     * 绘制图表
     */
    @ViewClick(ids = R.id.chartViewTV) private void chartViewTV(View v) {
        ChartViewActivity.startActivity();
    }

    /**
     * click: appbarBehaviorTV
     * 定制AppbarBehavior
     */
    @ViewClick(ids = R.id.appbarBehaviorTV) private void appbarBehaviorTV(View v) {
        AppbarBehaviorActivity.startActivity();
    }
}