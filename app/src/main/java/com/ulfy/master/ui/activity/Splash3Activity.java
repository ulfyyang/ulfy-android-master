package com.ulfy.master.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ulfy.android.image.ImageUtils;
import com.ulfy.android.system.ActivityUtils;
import com.ulfy.android.system.AppUtils;
import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_extension.UiTimer;
import com.ulfy.android.task_transponder.SilentProcesser;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.utils.StatusBarUtils;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.Splash3VM;
import com.ulfy.master.domain.cache.AppCache;
import com.ulfy.master.ui.base.BaseActivity;

@Layout(id = R.layout.activity_splash3)
public class Splash3Activity extends BaseActivity {
    @ViewById(id = R.id.splashIV) private ImageView splashIV;
    @ViewById(id = R.id.skipTV) private TextView skipTV;
    private boolean autoSkip = true;                                // 倒计时结束后页面是否自动跳转
    private UiTimer uiTimer = new UiTimer(1000);
    private Splash3VM vm = new Splash3VM();

    public static void startActivity() {
        ActivityUtils.startActivity(Splash3Activity.class);
    }

    /*
        测试用例：
            1. 定时器开始前倒计时按钮不显示
            2. 定时器开始后显示倒计时按钮，且按钮不可点击
            3. 定时器结束后：自动跳转到首页（自动跳转模式）、跳转按钮显示进入（非自动跳转模式）
            4. 非自动跳转模式：定时器结束后点击按钮进入首页
            5. 在启动页显示计时过程中关闭启动页不会启动主页
            6. 在手机设置中清除缓存后可重置首次打开记录

            1. 广告加载流程必须结束以后才会进入到后续的流程
            2. 广告加载流程结束之前点击广告无反应
            3. 广告加载出来之后点击广告进入广告页面，返回后会看到首页
            4. 广告没有设置的情况下会直接跳转到主页
     */
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.visiable(this, false);

        // 初始化页面显示
        skipTV.setVisibility(View.GONE);

        // 启动逻辑：应用首次打开进入引导页；否则按广告加载逻辑进入主页；
        if (AppCache.getInstance().isFirstStart()) {
            gotoGuideActivity();
        } else {
            loadSplashDataThenDo(new Runnable() {
                @Override public void run() {
                    initUiAfterLoadSplashData();
                }
            });
        }
    }

    /**
     * 加载完启动页数据后执行指定的操作
     */
    private void loadSplashDataThenDo(Runnable runnable) {
        TaskUtils.loadData(getContext(), vm.loadSplashDataOnExe(), new SilentProcesser() {
                    @Override public void onSuccess(Object tipData) {
                        if (runnable != null) {
                            runnable.run();
                        }
                    }
                    @Override protected void onFail(Object data) {
                        UiUtils.show(data);
                        loadSplashDataThenDo(runnable);     // 加载失败后继续重试（可以根据业务定制）
                    }
                }
        );
    }

    /**
     * 当启动页数据加载完成之后初始化页面：如果有广告则显示广告逻辑；如果没有广告则直接进入主页
     */
    private void initUiAfterLoadSplashData() {
        if (vm.advertisement != null) {
            ImageUtils.loadImage(vm.advertisement.cover, splashIV);
            initTimer();
            uiTimer.schedule();
        } else {
            gotoMainActivity();
        }
    }

    /**
     * 初始化定时器
     */
    private void initTimer() {
        uiTimer.setUiTimerExecuteBody(new UiTimer.UiTimerExecuteBody() {
            @Override public void onExecute(UiTimer timer, UiTimer.TimerDriver timerDriver) {
                // 定时器执行回调：这里可以设置按钮上的文字
                skipTV.setText(String.format("%d", ((UiTimer.NumberTimerDriver) timerDriver).getCurrentNumber()));
            }
        }).setOnTimerStartListener(new UiTimer.OnTimerStartListener() {
            @Override public void onTimerStart(UiTimer uiTimer, UiTimer.TimerDriver timerDriver) {
                // 定时器开始回调：这里可以设置按钮可见、按钮不可点击等
                skipTV.setVisibility(View.VISIBLE);
                skipTV.setEnabled(false);
            }
        }).setOnTimerFinishListener(new UiTimer.OnTimerFinishListener() {
            @Override public void onTimerFinish(UiTimer timer, UiTimer.TimerDriver timerDriver) {
                // 定时器结束回调：这可可以根据情况设置是否自动跳转
                if (autoSkip) {
                    gotoMainActivity();
                } else {
                    skipTV.setEnabled(true);
                    skipTV.setText("进入");
                }
            }
        }).setTimerDriver(new UiTimer.NumberTimerDriver(5, 1, 1, false, true));
    }

    /**
     * 点击按钮进入主页
     */
    @ViewClick(ids = R.id.skipTV) private void skipTV(View v) {
        gotoMainActivity();
    }

    /**
     * 点击广告跳转到浏览器
     */
    @ViewClick(ids = R.id.splashIV) private void splashIV(View v) {
        if (vm.advertisement != null) {
            gotoMainActivity();
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override public void run() {
                    AppUtils.launchSystemBrowser(vm.advertisement.url);
                }
            }, 50);     // 这里做个小延迟，否则有些手机上页面会被覆盖
        }
    }

    /**
     * 前往引导页
     */
    private void gotoGuideActivity() {
        UiUtils.show("打开了引导页");     // 替换为具体的启动页实现
        finish();
    }

    /**
     * 前往主页面
     */
    private void gotoMainActivity() {
        startActivity(new Intent(getContext(), MainActivity.class));
        finish();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        uiTimer.cancel(false);               // 注意及时取消定时器，在页面被销毁时取消定时不需要回调定时器完成回调
    }
}