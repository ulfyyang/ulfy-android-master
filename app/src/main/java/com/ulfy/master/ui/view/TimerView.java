package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task_extension.UiBackgrounder;
import com.ulfy.android.task_extension.UiTimer;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.TimerVM;
import com.ulfy.master.ui.base.BaseView;

import java.util.Random;

@Layout(id = R.layout.view_timer)
public class TimerView extends BaseView {
    @ViewById(id = R.id.timer1TV) private TextView timer1TV;
    @ViewById(id = R.id.timerStart1BT) private Button timerStart1BT;
    @ViewById(id = R.id.timerStop1BT) private Button timerStop1BT;
    @ViewById(id = R.id.timer2TV) private TextView timer2TV;
    @ViewById(id = R.id.timerStart2BT) private Button timerStart2BT;
    @ViewById(id = R.id.timerStop2BT) private Button timerStop2BT;
    @ViewById(id = R.id.numberTimerV) private View numberTimerV;
    @ViewById(id = R.id.numberTimerStartBT) private Button numberTimerStartBT;
    @ViewById(id = R.id.numberTimerStopBT) private Button numberTimerStopBT;
    @ViewById(id = R.id.getVerifyCodeBT) private Button getVerifyCodeBT;
    private UiTimer uiTimer1 = new UiTimer(300);
    private UiTimer uiTimer2 = new UiTimer(100);
    private UiTimer numberDriverTimer = new UiTimer(1).setDelayStart(1000);
    private UiTimer getVerifyCodeTimer = new UiTimer(1000);
    private UiBackgrounder getVerifyCodeBackgrounder = new UiBackgrounder();
    private TimerVM vm;

    public TimerView(Context context) {
        super(context);
        init(context, null);
    }

    public TimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        // 多定时器测试
        uiTimer1.setUiTimerExecuteBody(new UiTimer.UiTimerExecuteBody() {
            @Override public void onExecute(UiTimer timer, UiTimer.TimerDriver timerDriver) {
                timer1TV.setText(String.format("定时器1--当前数字:%d", new Random().nextInt(100)));
            }
        }).setOnTimerFinishListener(new UiTimer.OnTimerFinishListener() {
            @Override public void onTimerFinish(UiTimer timer, UiTimer.TimerDriver timerDriver) {
                timer1TV.setText(String.format("定时器1--当前数字:%s", "已停止"));
            }
        });
        uiTimer2.setUiTimerExecuteBody(new UiTimer.UiTimerExecuteBody() {
            @Override public void onExecute(UiTimer timer, UiTimer.TimerDriver timerDriver) {
                timer2TV.setText(String.format("定时器2--当前数字:%d", new Random().nextInt(100)));
            }
        }).setOnTimerFinishListener(new UiTimer.OnTimerFinishListener() {
            @Override public void onTimerFinish(UiTimer timer, UiTimer.TimerDriver timerDriver) {
                timer2TV.setText(String.format("定时器2--当前数字:%s", "已停止"));
            }
        });
        // 数字定时器测试
        numberDriverTimer.setUiTimerExecuteBody(new UiTimer.UiTimerExecuteBody() {
            @Override public void onExecute(UiTimer timer, UiTimer.TimerDriver timerDriver) {
                numberTimerV.setTranslationX(((UiTimer.NumberTimerDriver)timerDriver).getCurrentNumber());
            }
        }).setOnTimerStartListener(new UiTimer.OnTimerStartListener() {
            @Override public void onTimerStart(UiTimer uiTimer, UiTimer.TimerDriver timerDriver) {
                UiUtils.show("定时器开始了");
            }
        }).setOnTimerFinishListener(new UiTimer.OnTimerFinishListener() {
            @Override public void onTimerFinish(UiTimer timer, UiTimer.TimerDriver timerDriver) {
                UiUtils.show("定时器结束了");
            }
        }).setTimerDriver(new UiTimer.NumberTimerDriver(0, 200, 4, true, true));
        // 验证码
        getVerifyCodeTimer.setUiTimerExecuteBody(new UiTimer.UiTimerExecuteBody() {
            @Override public void onExecute(UiTimer timer, UiTimer.TimerDriver timerDriver) {
                getVerifyCodeBT.setText(String.format("%ds", ((UiTimer.NumberTimerDriver)timerDriver).getCurrentNumber()));
                getVerifyCodeBT.setEnabled(false);
            }
        }).setOnTimerFinishListener(new UiTimer.OnTimerFinishListener() {
            @Override public void onTimerFinish(UiTimer timer, UiTimer.TimerDriver timerDriver) {
                getVerifyCodeBT.setText("获取验证码");
                getVerifyCodeBT.setEnabled(true);
            }
        }).setTimerDriver(new UiTimer.NumberTimerDriver(5, 0, 1, false, false));
        // 验证码完后模拟加载
        getVerifyCodeBackgrounder.setUiBackgrounderExecuteBody(new UiBackgrounder.UiBackgrounderExecuteBody() {
            @Override public void onExecute(UiBackgrounder backgrounder) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) { }
            }
        }).setOnBackgrounderStartListener(new UiBackgrounder.OnBackgrounderStartListener() {
            @Override public void onBackgrounderStart(UiBackgrounder backgrounder) {
                getVerifyCodeBT.setText("正在获取...");
                getVerifyCodeBT.setEnabled(false);
            }
        }).setOnBackgrounderFinishListener(new UiBackgrounder.OnBackgrounderFinishListener() {
            @Override public void onBackgrounderFinish(UiBackgrounder backgrounder) {
                getVerifyCodeBT.setText("获取成功");
                getVerifyCodeBT.setEnabled(true);
            }
        });
        getVerifyCodeTimer.connect(getVerifyCodeBackgrounder);
    }

    @Override public void bind(IViewModel model) {
        vm = (TimerVM) model;
    }

    /*
    UiTimer和UiBackgrounder之间都有一个互相连接的方法，可以使得在其中一个执行完毕之后自动启动下一个被连接的任务
    这两个工具适合编写页面临时使用的小范围事件，应当将其作为实现页面部分效果的快速工具对待
     */

    /**
     * click: timerStart1BT, timerStop1BT
     * 点击定时器1
     */
    @ViewClick(ids = {R.id.timerStart1BT, R.id.timerStop1BT})
    private void timer1(View v) {
        switch (v.getId()) {
            case R.id.timerStart1BT:
                uiTimer1.schedule();
                break;
            case R.id.timerStop1BT:
                uiTimer1.cancel();
                break;
        }
    }

    /**
     * click: timerStart2BT, timerStop2BT
     * 点击定时器2
     */
    @ViewClick(ids = {R.id.timerStart2BT, R.id.timerStop2BT})
    private void timer2(View v) {
        switch (v.getId()) {
            case R.id.timerStart2BT:
                uiTimer2.schedule();
                break;
            case R.id.timerStop2BT:
                uiTimer2.cancel();
                break;
        }
    }

    /**
     * click: numberTimerStartBT, numberTimerStopBT
     * 点击数字定时器
     */
    @ViewClick(ids = {R.id.numberTimerStartBT, R.id.numberTimerStopBT})
    private void numberTimeDriver(View v) {
        switch (v.getId()) {
            case R.id.numberTimerStartBT:
                numberDriverTimer.schedule();
                break;
            case R.id.numberTimerStopBT:
                numberDriverTimer.cancel();
                break;
        }
    }

    /**
     * 点击：获取验证码
     */
    @ViewClick(ids = R.id.getVerifyCodeBT) private void getVerifyCodeBT(View v) {
        ((UiTimer.NumberTimerDriver)getVerifyCodeTimer.getTimerDriver()).initCurrentNumber();
        getVerifyCodeTimer.schedule();
    }

    @Override protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        uiTimer1.cancel(false);                 // 正常页面关闭无需回调结束监听
        uiTimer2.cancel(false);                 // 正常页面关闭无需回调结束监听
        numberDriverTimer.cancel(false);        // 正常页面关闭无需回调结束监听
        getVerifyCodeTimer.cancel(false);       // 正常页面关闭无需回调结束监听
    }
}