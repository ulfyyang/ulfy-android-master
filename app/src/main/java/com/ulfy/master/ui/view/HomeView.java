package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.HomeVM;
import com.ulfy.master.ui.activity.AdapterActivity;
import com.ulfy.master.ui.activity.AgentWebActivity;
import com.ulfy.master.ui.activity.DataPreLoaderActivity;
import com.ulfy.master.ui.activity.DialogActivity;
import com.ulfy.master.ui.activity.DownloadManagerActivity;
import com.ulfy.master.ui.activity.EventBusActivity;
import com.ulfy.master.ui.activity.ImageProcessActivity;
import com.ulfy.master.ui.activity.MultiDomainPickerActivity;
import com.ulfy.master.ui.activity.ObjectCacheActivity;
import com.ulfy.master.ui.activity.SmartTransponderActivity;
import com.ulfy.master.ui.activity.SystemActivity;
import com.ulfy.master.ui.activity.TaskActivity;
import com.ulfy.master.ui.activity.TaskExtensionActivity;
import com.ulfy.master.ui.activity.TaskTransponderActivity;
import com.ulfy.master.ui.activity.Time1Activity;
import com.ulfy.master.ui.activity.Time2Activity;
import com.ulfy.master.ui.activity.UiLinkageActivity;
import com.ulfy.master.ui.activity.ViewsActivity;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_home)
public class HomeView extends BaseView {
    private HomeVM vm;

    public HomeView(Context context) {
        super(context);
        init(context, null);
    }

    public HomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (HomeVM) model;
    }

    /**
     * click: objectCacheTV
     * 对象缓存
     */
    @ViewClick(ids = R.id.objectCacheTV) private void objectCacheTV(View v) {
        ObjectCacheActivity.startActivity();
    }

    /**
     * click: eventBusTV
     * 事件总线
     */
    @ViewClick(ids = R.id.eventBusTV) private void eventBusTV(View v) {
        EventBusActivity.startActivity();
    }

    /**
     * click: systemTV
     * 系统功能
     */
    @ViewClick(ids = R.id.systemTV) private void systemTV(View v) {
        SystemActivity.startActivity();
    }

    /**
     * click: taskTV
     * 任务引擎
     */
    @ViewClick(ids = R.id.taskTV) private void taskTV(View v) {
        TaskActivity.startActivity();
    }

    /**
     * click: taskTransponderTV
     * 任务应答器
     */
    @ViewClick(ids = R.id.taskTransponderTV) private void taskTransponderTV(View v) {
        TaskTransponderActivity.startActivity();
    }

    /**
     * click: smartTransponderTV
     * Smart响应器
     */
    @ViewClick(ids = R.id.smartTransponderTV) private void smartTransponderTV(View v) {
        SmartTransponderActivity.startActivity();
    }

    /**
     * click: taskExtensionTV
     * 任务扩展包
     */
    @ViewClick(ids = R.id.taskExtensionTV) private void taskExtensionTV(View v) {
        TaskExtensionActivity.startActivity();
    }

    /**
     * click: adapterTV
     * 便捷适配器
     */
    @ViewClick(ids = R.id.adapterTV) private void adapterTV(View v) {
        AdapterActivity.startActivity();
    }

    /**
     * click: time1TV
     * 时间跟踪
     */
    @ViewClick(ids = R.id.time1TV) private void time1TV(View v) {
        Time1Activity.startActivity();
    }

    /**
     * click: time2TV
     * 时间跟踪
     */
    @ViewClick(ids = R.id.time2TV) private void time2TV(View v) {
        Time2Activity.startActivity();
    }

    /**
     * click: viewsTV
     * 自定义控件
     */
    @ViewClick(ids = R.id.viewsTV) private void viewsTV(View v) {
        ViewsActivity.startActivity();
    }

    /**
     * click: uiLinkageTV
     * 页面联动
     */
    @ViewClick(ids = R.id.uiLinkageTV) private void uiLinkageTV(View v) {
        UiLinkageActivity.startActivity();
    }

    /**
     * click: downloadManagerTV
     * 下载管理
     */
    @ViewClick(ids = R.id.downloadManagerTV) private void downloadManagerTV(View v) {
        DownloadManagerActivity.startActivity();
    }

    /**
     * click: multiDomainPickerTV
     * 多域名选择器
     */
    @ViewClick(ids = R.id.multiDomainPickerTV) private void multiDomainPickerTV(View v) {
        MultiDomainPickerActivity.startActivity();
    }

    /**
     * click: dataPreLoaderTV
     * 数据预加载
     */
    @ViewClick(ids = R.id.dataPreLoaderTV) private void dataPreLoaderTV(View v) {
        DataPreLoaderActivity.startActivity();
    }

    /**
     * click: dialogTV
     * 弹出框
     */
    @ViewClick(ids = R.id.dialogTV) private void dialogTV(View v) {
        DialogActivity.startActivity();
    }
    
    /**
     * click: imageTV
     * 图片处理
     */
    @ViewClick(ids = R.id.imageTV) private void imageTV(View v) {
        ImageProcessActivity.startActivity();
    }

    /**
     * click: agentwebTV
     * 网页加载
     */
    @ViewClick(ids = R.id.agentwebTV) private void agentwebTV(View v) {
        AgentWebActivity.startActivity();
    }
}