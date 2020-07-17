package com.ulfy.master.ui.view;

import android.Manifest;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.system.AppUtils;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.utils.StringUtils;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.DeviceIdVM;
import com.ulfy.master.domain.cache.AppCache;
import com.ulfy.master.ui.base.BaseView;

import java.util.UUID;

@Layout(id = R.layout.view_device_id)
public class DeviceIdView extends BaseView {
    @ViewById(id = R.id.splashUpdateBT) private Button splashUpdateBT;
    @ViewById(id = R.id.provideBT) private Button provideBT;
    @ViewById(id = R.id.deviceIdTV) private TextView deviceIdTV;
    private DeviceIdVM vm;

    public DeviceIdView(Context context) {
        super(context);
        init(context, null);
    }

    public DeviceIdView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (DeviceIdVM) model;
    }

    /**
     * click: splashUpdateBT
     * 点击启动页获取
     */
    @ViewClick(ids = R.id.splashUpdateBT) private void splashUpdateBT(View v) {
        AppUtils.requestPermission(new AppUtils.OnRequestPermissionListener() {
            @Override public void onSuccess() {
                if (StringUtils.isEmpty(AppCache.getInstance().deviceId())) {
                    String deviceId = AppUtils.getImei();
                    if (StringUtils.isEmpty(deviceId)) {
                        deviceId = Build.SERIAL;
                    }
                    if (StringUtils.isEmpty(deviceId)) {
                        deviceId = UUID.randomUUID().toString();
                    }
//                    AppCache.getInstance().updateDeviceId(deviceId);
                }
                UiUtils.show("获取成功");
            }
            @Override public void onFail() {
                System.exit(0);
            }
        }, Manifest.permission.READ_PHONE_STATE);
    }

    /**
     * click: provideBT
     * 点击其它位置获取
     */
    @ViewClick(ids = R.id.provideBT) private void provideBT(View v) {
        deviceIdTV.setText(String.format("设备标识：%s", AppCache.getInstance().deviceId()));
    }
}