package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ulfy.android.bus.Subscribe;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.system.AppUtils;
import com.ulfy.android.system.event.OnNetworkStateChangedEvent;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.NetStateVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_net_state)
public class NetStateView extends BaseView {
    @ViewById(id = R.id.netStateTV) private TextView netStateTV;
    private NetStateVM vm;

    public NetStateView(Context context) {
        super(context);
        init(context, null);
    }

    public NetStateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (NetStateVM) model;
        netStateTV.setText(String.format("网络状态：%s", AppUtils.isInternetConnected() ? "已连接" : "已断开"));
    }

    @Subscribe private void OnNetworkStateChangedEvent(OnNetworkStateChangedEvent event) {
        netStateTV.setText(String.format("网络状态：%s", event.connected ? "已连接" : "已断开"));
    }

    @Override protected void onNetworkReconnected() {
        UiUtils.show("网络重新连接了");
    }
}