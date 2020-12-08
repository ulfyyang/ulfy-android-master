package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.ulfy.android.bus.Subscribe;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.system.event.OnReceiveDataEvent;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.SystemVM;
import com.ulfy.master.ui.activity.FileChooseActivity;
import com.ulfy.master.ui.activity.ImagePickActivity;
import com.ulfy.master.ui.activity.NetStateActivity;
import com.ulfy.master.ui.activity.ReceiveDataActivity;
import com.ulfy.master.ui.activity.ShakeActivity;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_system)
public class SystemView extends BaseView {
    public static final int REQUEST_CODE_GET_MESSAGE = 100;
    private SystemVM vm;

    public SystemView(Context context) {
        super(context);
        init(context, null);
    }

    public SystemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (SystemVM) model;
    }
    
    /**
     * click: receiveDataTV
     * 启动另一个页面并接收数据
     */
    @ViewClick(ids = R.id.receiveDataTV) private void receiveDataTV(View v) {
        ReceiveDataActivity.startActivity(REQUEST_CODE_GET_MESSAGE);
    }

    /**
     * 接收另一个页面返回的数据
     */
    @Subscribe private void OnReceiveDataEvent(OnReceiveDataEvent event) {
        if (event.requestCode == REQUEST_CODE_GET_MESSAGE) {
            UiUtils.show("接收到了内容：" + event.data.getString("content"));
        }
    }

    /**
     * click: fileChooseTV
     * 文件选择
     */
    @ViewClick(ids = R.id.fileChooseTV) private void fileChooseTV(View v) {
        FileChooseActivity.startActivity();
    }
    
    /**
     * click: shakeTV
     * 摇一摇
     */
    @ViewClick(ids = R.id.shakeTV) private void shakeTV(View v) {
        ShakeActivity.startActivity();
    }

    /**
     * click: netStateTV
     * 网络监听
     */
    @ViewClick(ids = R.id.netStateTV) private void netStateTV(View v) {
        NetStateActivity.startActivity();
    }

    /**
     * click: imgPickTV
     * 图片选择
     */
    @ViewClick(ids = R.id.imgPickTV) private void imgPickTV(View v) {
        ImagePickActivity.startActivity();
    }

}