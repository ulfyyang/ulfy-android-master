package com.ulfy.master.ui.custom_dkplayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.util.Consumer;

import com.dueeeke.videoplayer.controller.ControlWrapper;
import com.dueeeke.videoplayer.controller.IControlComponent;
import com.dueeeke.videoplayer.player.VideoView;
import com.ulfy.android.image.ImageUtils;
import com.ulfy.android.system.AppUtils;
import com.ulfy.android.system.SystemConfig;
import com.ulfy.android.ui_injection.InjectUtils;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.utils.BitmapUtils;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Layout(id = R.layout.view_screenshot)
public class ScreenshotView extends FrameLayout implements IControlComponent {
    @ViewById(id = R.id.takeLV) private LinearLayout takeLV;
    @ViewById(id = R.id.takePictureIV) private ImageView takePictureIV;
    @ViewById(id = R.id.takeGifIV) private ImageView takeGifIV;
    @ViewById(id = R.id.takePicturePreviewFL) private FrameLayout takePicturePreviewFL;
    @ViewById(id = R.id.takePicturePreviewIV) private ImageView takePicturePreviewIV;
    @ViewById(id = R.id.takePictureShareToWechatLL) private LinearLayout takePictureShareToWechatLL;
    @ViewById(id = R.id.takePictureShareToQQLL) private LinearLayout takePictureShareToQQLL;
    @ViewById(id = R.id.takePicturePreviewTV) private TextView takePicturePreviewTV;
    @ViewById(id = R.id.recordingGifFL) private FrameLayout recordingGifFL;
    @ViewById(id = R.id.recordingGifStatusTV) private TextView recordingGifStatusTV;
    @ViewById(id = R.id.recordingGifTimeTV) private TextView recordingGifTimeTV;
    @ViewById(id = R.id.recordingGifIV) private ImageView recordingGifIV;
    @ViewById(id = R.id.takeGifPreviewFL) private FrameLayout takeGifPreviewFL;
    @ViewById(id = R.id.takeGifPreviewIV) private ImageView takeGifPreviewIV;
    @ViewById(id = R.id.takeGifShareToWechatLL) private LinearLayout takeGifShareToWechatLL;
    @ViewById(id = R.id.takeGifShareToQQLL) private LinearLayout takeGifShareToQQLL;
    @ViewById(id = R.id.takeGifPreviewTV) private TextView takeGifPreviewTV;
    private ControlWrapper controlWrapper;
    private Disposable recordScreenshotDisposable;

    @SuppressLint("ClickableViewAccessibility")
    public ScreenshotView(Context context) {
        super(context);
        InjectUtils.processLayoutFile(this);
        InjectUtils.processViewById(this);
        InjectUtils.processViewClick(this);
        takeGifIV.setOnTouchListener((v, event) -> { onTouchTakeGifIV(event); return true; });
    }

    /**
     * 点击相机按钮：获取截图并在预览页面中显示
     */
    @ViewClick(ids = {R.id.takePictureIV, R.id.takePictureShareToWechatLL, R.id.takePictureShareToQQLL, R.id.takePicturePreviewTV})
    private void taskPicture(View v) {
        switch (v.getId()) {
            case R.id.takePictureIV:
                requestPermissionThenDo(this::takeScreenShotThenShow);
                break;
            case R.id.takePictureShareToWechatLL:
                AppUtils.launchThirdApp(SystemConfig.Config.THIRD_APP_PACKAGE_NAME_WECHAT);
                break;
            case R.id.takePictureShareToQQLL:
                AppUtils.launchThirdApp(SystemConfig.Config.THIRD_APP_PACKAGE_NAME_QQ);
                break;
            case R.id.takePicturePreviewTV:
                takePicturePreviewFL.setVisibility(View.GONE);
                takePicturePreviewIV.setImageBitmap(null);
                controlWrapper.start();
                break;
        }
    }

    private void takeScreenShotThenShow() {
        controlWrapper.pause(); controlWrapper.toggleShowState();
        Bitmap screenshot = controlWrapper.doScreenShot();
        takePicturePreviewFL.setVisibility(View.VISIBLE);
        takePicturePreviewIV.setImageBitmap(screenshot);
        AppUtils.insertPictureToSystem(screenshot, AppUtils.getAppName() + "截图_" + System.currentTimeMillis(), () -> UiUtils.show("已保存到相册"));
    }

    /**
     * 当录屏按钮按下时开始录屏，当松开或满10秒后开始生成GIF
     */
    private void onTouchTakeGifIV(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                requestPermissionThenDo(() -> recordScreenshotDisposable = recordScreenshotThenDo(this::generateGifThenShow));
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (recordScreenshotDisposable != null) {
                    recordScreenshotDisposable.dispose();
                    recordScreenshotDisposable = null;
                }
                break;
        }
    }

    /**
     * 点击相机按钮：获取截图并在预览页面中显示
     */
    @ViewClick(ids = {R.id.takeGifShareToWechatLL, R.id.takeGifShareToQQLL, R.id.takeGifPreviewTV})
    private void taskGif(View v) {
        switch (v.getId()) {
            case R.id.takeGifShareToWechatLL:
                AppUtils.launchThirdApp(SystemConfig.Config.THIRD_APP_PACKAGE_NAME_WECHAT);
                break;
            case R.id.takeGifShareToQQLL:
                AppUtils.launchThirdApp(SystemConfig.Config.THIRD_APP_PACKAGE_NAME_QQ);
                break;
            case R.id.takeGifPreviewTV:
                takeGifPreviewFL.setVisibility(View.GONE);
                takeGifPreviewIV.setImageBitmap(null);
                controlWrapper.start();
                break;
        }
    }

    private Disposable recordScreenshotThenDo(Consumer<List<File>> consumer) {
        controlWrapper.toggleShowState();
        List<File> screenshotFileList = new ArrayList<>();
        // 0.2秒录制一张，最多录制50次，最多10秒钟
        return Observable.intervalRange(1, 50, 0, 200, TimeUnit.MILLISECONDS)
                // 页面响应部分（显示截取进度）
                .doOnSubscribe(disposable -> {
                    recordingGifFL.setVisibility(View.VISIBLE);
                    recordingGifStatusTV.setText("正在录制视频...");
                    recordingGifIV.setVisibility(View.VISIBLE);
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(x -> recordingGifTimeTV.setText(String.format("%.2fs", x * 200 * 1.0 / 1000)))
                // 实际截图处理部分
                .doOnSubscribe(disposable -> screenshotFileList.clear())
                .concatMapEager(x -> Observable.fromCallable(() -> {
                    File screenshotFile = File.createTempFile("screenshot", ".jpg", getContext().getCacheDir());
                    BitmapUtils.bitmapToFile(controlWrapper.doScreenShot(), screenshotFile, false);
                    return screenshotFile;
                }).subscribeOn(Schedulers.io()))
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.computation())
                .doFinally(() -> {
                    consumer.accept(new ArrayList<>(screenshotFileList));       // 做一份拷贝，之前生成的就可以丢弃了，尽量做无状态的功能
                    screenshotFileList.clear();
                })
                .subscribe(screenshotFileList::add);
    }

    private void generateGifThenShow(List<File> screenshotFileList) {
        // 处理图片部分
        Observable.create((ObservableOnSubscribe<FileTipPair>) emitter -> {
            File gifFile = File.createTempFile("screenshot", ".gif", getContext().getCacheDir());
            BitmapUtils.bitmapFileToGif(gifFile.getAbsolutePath(), 100, false, 400, 400, screenshotFileList, (bitmap, current, total) -> {
                emitter.onNext(new FileTipPair(gifFile, String.format("%.2f%%", current * 100f / total)));
                if (current == total) { emitter.onComplete(); }
            });
        }).subscribeOn(Schedulers.io())
        // 页面响应部分
        .doOnSubscribe(disposable -> {
            controlWrapper.pause();
            recordingGifFL.setVisibility(View.VISIBLE);
            recordingGifStatusTV.setText("正在生成GIF...");
            recordingGifIV.setVisibility(View.GONE);
        })
        .subscribeOn(AndroidSchedulers.mainThread())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnNext(pair -> recordingGifStatusTV.setText(String.format("正在生成GIF...%s", pair.tip)))
        .lastElement()
        .subscribe(pair -> {
            recordingGifFL.setVisibility(View.GONE);
            takeGifPreviewFL.setVisibility(View.VISIBLE);
            ImageUtils.loadImage(pair.file, takeGifPreviewIV);
            AppUtils.insertPictureToSystem(pair.file, pair.file.getName(), () -> UiUtils.show("已保存到相册"));
        });
    }

    private static class FileTipPair {
        File file; String tip;
        FileTipPair(File file, String tip) {
            this.file = file; this.tip = tip;
        }
    }

    private void requestPermissionThenDo(Runnable runnable) {
        AppUtils.requestPermission(new AppUtils.OnRequestPermissionListener() {
            @Override public void onSuccess() {
                if (runnable != null) { runnable.run(); }
            }
            @Override public void onFail() {
                UiUtils.show("未授予系统存储权限");
            }
        });
    }

    @Override public void attach(ControlWrapper controlWrapper) {
        this.controlWrapper = controlWrapper;
    }

    @Override public View getView() {
        return this;
    }

    @Override public void onVisibilityChanged(boolean isVisible, Animation anim) {
        if (controlWrapper.isFullScreen()) {
            if (isVisible && takeLV.getVisibility() == View.GONE) {
                takeLV.setVisibility(View.VISIBLE);
                if (anim != null) { takeLV.startAnimation(anim); }
            } else if (!isVisible && takeLV.getVisibility() == View.VISIBLE) {
                takeLV.setVisibility(View.GONE);
                if (anim != null) { takeLV.startAnimation(anim); }
            }
        }
    }

    @Override public void onPlayStateChanged(int playState) {
        switch (playState) {
            case VideoView.STATE_IDLE: case VideoView.STATE_START_ABORT:
            case VideoView.STATE_PREPARING: case VideoView.STATE_PREPARED:
            case VideoView.STATE_ERROR: case VideoView.STATE_PLAYBACK_COMPLETED:
                takeLV.setVisibility(GONE);
                break;
        }
    }

    @Override public void onPlayerStateChanged(int playerState) {
        if (playerState == VideoView.PLAYER_FULL_SCREEN) {
            if (controlWrapper.isShowing() && !controlWrapper.isLocked()) {
                takeLV.setVisibility(VISIBLE);
            }
        } else {
            takeLV.setVisibility(GONE);
        }
    }

    @Override public void setProgress(int duration, int position) {

    }

    @Override public void onLockStateChanged(boolean isLocked) {
        takeLV.setVisibility(isLocked ? View.GONE : View.VISIBLE);
    }
}
