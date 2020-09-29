package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.FindVM;
import com.ulfy.master.ui.activity.CommentEditTextActivity;
import com.ulfy.master.ui.activity.ContentSearchActivity;
import com.ulfy.master.ui.activity.DetailsActivity;
import com.ulfy.master.ui.activity.DeviceIdActivity;
import com.ulfy.master.ui.activity.FailRollbackActivity;
import com.ulfy.master.ui.activity.GuideActivity;
import com.ulfy.master.ui.activity.ListActivity;
import com.ulfy.master.ui.activity.MinePage1Activity;
import com.ulfy.master.ui.activity.MinePage2Activity;
import com.ulfy.master.ui.activity.NetInfoActivity;
import com.ulfy.master.ui.activity.ShowTypeActivity;
import com.ulfy.master.ui.activity.SplashActivity;
import com.ulfy.master.ui.activity.TabPagerActivity;
import com.ulfy.master.ui.activity.UpgradeCheckActivity;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_find)
public class FindView extends BaseView {
    private FindVM vm;

    public FindView(Context context) {
        super(context);
        init(context, null);
    }

    public FindView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (FindVM) model;
    }

    /**
     * click: splashIV
     * 编写启动页
     */
    @ViewClick(ids = R.id.splashIV) private void splashIV(View v) {
        SplashActivity.startActivity();
    }

    /**
     * click: guideIV
     * 编写引导页
     */
    @ViewClick(ids = R.id.guideIV) private void guideIV(View v) {
        GuideActivity.startActivity();
    }

    /**
     * click: tabPagerTV
     * 标签页分页
     */
    @ViewClick(ids = R.id.tabPagerTV) private void tabPagerTV(View v) {
        TabPagerActivity.startActivity();
    }

    /**
     * click: detailsTV
     * 编写详情页
     */
    @ViewClick(ids = R.id.detailsTV) private void detailsTV(View v) {
        DetailsActivity.startActivity();
    }

    /**
     * click: listTV
     * 编写列表页
     */
    @ViewClick(ids = R.id.listTV) private void listTV(View v) {
        ListActivity.startActivity();
    }

    /**
     * click: contentSearchTV
     * 编写搜索页
     */
    @ViewClick(ids = R.id.contentSearchTV) private void contentSearchTV(View v) {
        ContentSearchActivity.startActivity();
    }

    /**
     * click: netInfoTV
     * 获取网络信息
     */
    @ViewClick(ids = R.id.netInfoTV) private void netInfoTV(View v) {
        NetInfoActivity.startActivity();
    }

    /**
     * click: failRollbackTV
     * 失败回退
     */
    @ViewClick(ids = R.id.failRollbackTV) private void failRollbackTV(View v) {
        FailRollbackActivity.startActivity();
    }

    /**
     * click: deviceIdTV
     * 设备标识
     */
    @ViewClick(ids = R.id.deviceIdTV) private void deviceIdTV(View v) {
        DeviceIdActivity.startActivity();
    }

    /**
     * click: upgradeCheckTV
     * 升级检测
     */
    @ViewClick(ids = R.id.upgradeCheckTV) private void upgradeCheckTV(View v) {
        UpgradeCheckActivity.startActivity();
    }

    /**
     * click: commentEditTextTV
     * 评论输入框
     */
    @ViewClick(ids = R.id.commentEditTextTV) private void commentEditTextTV(View v) {
        CommentEditTextActivity.startActivity();
    }

    /**
     * click: minePageIV
     * 个人主页
     */
    @ViewClick(ids = R.id.minePage1IV) private void minePage1IV(View v) {
        MinePage1Activity.startActivity();
    }

    /**
     * click: minePageIV
     * 个人主页
     */
    @ViewClick(ids = R.id.minePage2IV) private void minePage2IV(View v) {
        MinePage2Activity.startActivity();
    }

    /**
     * click: showTypeIV
     * 显示类型切换
     */
    @ViewClick(ids = R.id.showTypeIV) private void showTypeIV(View v) {
        ShowTypeActivity.startActivity();
    }
}