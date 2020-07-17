package com.ulfy.master.ui.view;

import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ulfy.android.bus.Subscribe;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.system.ActivityUtils;
import com.ulfy.android.system.AppUtils;
import com.ulfy.android.system.event.OnReceiveDataEvent;
import com.ulfy.android.system.event.OnTakePhotoOrPickPictureEvent;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.QRCodeVM;
import com.ulfy.master.ui.base.BaseView;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

@Layout(id = R.layout.view_qrcode)
public class QRCodeView extends BaseView {
    @ViewById(id = R.id.cameroScanBT) private Button cameroScanBT;
    @ViewById(id = R.id.scanBT) private Button scanBT;
    @ViewById(id = R.id.scanCodeTV) private TextView scanCodeTV;
    @ViewById(id = R.id.generateBT) private Button generateBT;
    @ViewById(id = R.id.qrCodeIV) private ImageView qrCodeIV;
    @ViewById(id = R.id.qrCodeLogo1IV) private ImageView qrCodeLogo1IV;
    @ViewById(id = R.id.qrCodeLogo2ContentIV) private ImageView qrCodeLogo2ContentIV;
    @ViewById(id = R.id.qrCodeLogo2LogoIV) private ImageView qrCodeLogo2LogoIV;
    public static final int CODE_SCAN_CAMERA = 101;     // 相机
    public static final int CODE_SCAN_PHOTO = 102;      // 相册
    private QRCodeVM vm;

    public QRCodeView(Context context) {
        super(context);
        init(context, null);
    }

    public QRCodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (QRCodeVM) model;
    }

    /**
     * 相机扫描
     */
    @ViewClick(ids = R.id.cameroScanBT) private void cameroScanBT(View v) {
        AppUtils.requestPermission(new AppUtils.OnRequestPermissionListener() {
            @Override public void onSuccess() {
                ActivityUtils.startActivity(CaptureActivity.class, CODE_SCAN_CAMERA, null);
            }
            @Override public void onFail() {
                UiUtils.show("获取相机授权失败");
            }
        }, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    /**
     * 图片扫描
     */
    @ViewClick(ids = R.id.scanBT) private void scanBT(View v) {
        ActivityUtils.pickPicture(CODE_SCAN_PHOTO);
    }

    /**
     * 相机扫描
     */
    @Subscribe public void scanCodeEvent(OnReceiveDataEvent event) {
        if (event.requestCode == CODE_SCAN_CAMERA) {
            Bundle bundle = event.data;
            if (bundle != null) {
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    scanCodeTV.setText(bundle.getString(CodeUtils.RESULT_STRING));
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    UiUtils.show("解析二维码失败");
                }
            }
        }
    }

    /**
     * 图片扫描
     */
    @Subscribe public void OnTakePhotoOrPickPictureEvent(OnTakePhotoOrPickPictureEvent event) {
        if (event.requestCode == CODE_SCAN_PHOTO) {
            CodeUtils.analyzeBitmap(event.file.getAbsolutePath(), new CodeUtils.AnalyzeCallback() {
                @Override public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                    scanCodeTV.setText(result);
                }
                @Override public void onAnalyzeFailed() {
                    UiUtils.show("解析二维码失败");
                }
            });
        }
    }

    /**
     * 生成二维码
     */
    @ViewClick(ids = R.id.generateBT) private void generateBT(View v) {
        Bitmap bitmap = CodeUtils.createImage("www.baidu.com", 400, 400, null);
        Bitmap bitmapLogo1 = CodeUtils.createImage("www.baidu.com", 400, 400, BitmapFactory.decodeResource(getResources(), R.drawable.icon_logo));
        // 不生成logo
        qrCodeIV.setImageBitmap(bitmap);
        // 生成logo
        qrCodeLogo1IV.setImageBitmap(bitmapLogo1);
        // 自制logo
        qrCodeLogo2ContentIV.setImageBitmap(bitmap);
        qrCodeLogo2LogoIV.setImageResource(R.drawable.icon_logo);
    }
}