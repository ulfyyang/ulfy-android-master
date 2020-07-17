package com.ulfy.master.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import com.ulfy.android.system.ActivityUtils;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.utils.StatusBarUtils;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.domain.cache.AppCache;
import com.ulfy.master.ui.base.BaseActivity;

@Layout(id = R.layout.activity_splash1)
public class Splash1Activity extends BaseActivity {
    @ViewById(id = R.id.splashIV) private ImageView splashIV;
    private Handler uiHandler = new Handler(Looper.getMainLooper());

    public static void startActivity() {
        ActivityUtils.startActivity(Splash1Activity.class);
    }

    /*
        测试用例：
            1. 首次打开该页面弹窗`打开了引导页`随后直接关闭（有可能太快看不见页面打开）
            2. 第二次打开进入页面之后 1.5 秒后启动主页
            3. 在启动页显示延迟过程中关闭启动页不会启动主页
            4. 在手机设置中清除缓存后可重置首次打开记录
     */

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.visiable(this, false);

        // 启动逻辑：应用首次打开进入引导页；否则延迟后进入主页；
        if (AppCache.getInstance().isFirstStart()) {
            gotoGuideActivity();
        } else {
            gotoMainActivity();
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
     * 延迟指定时间之后前往主页面
     */
    private void gotoMainActivity() {
        uiHandler.postDelayed(new Runnable() {
            @Override public void run() {
                startActivity(new Intent(getContext(), MainActivity.class));
                finish();
            }
        }, 1500);
    }

    /**
     * 在页面关闭的时候取消延迟消息，防止关闭应用后依然能跳转到主页
     */
    @Override protected void onDestroy() {
        super.onDestroy();
        uiHandler.removeCallbacksAndMessages(null);
    }
}