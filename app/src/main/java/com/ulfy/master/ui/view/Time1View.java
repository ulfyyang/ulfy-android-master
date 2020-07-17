package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.time.OnTimeRecordListener;
import com.ulfy.android.time.TimeRecorder;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.Time1VM;
import com.ulfy.master.infrastructure.AppConfig;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_time1)
public class Time1View extends BaseView {
    @ViewById(id = R.id.startRecordBT) private Button startRecordBT;
    @ViewById(id = R.id.stopRecordBT) private Button stopRecordBT;
    @ViewById(id = R.id.resetRecordBT) private Button resetRecordBT;
    @ViewById(id = R.id.recordTV) private TextView recordTV;
    private Time1VM vm;

    public Time1View(Context context) {
        super(context);
        init(context, null);
    }

    public Time1View(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        // 设置时间跟踪的回调，在跟踪的过程中每秒回调一次。在这个回调中可以做一些实时性处理的事情。
        TimeRecorder.setOnTimeRecordListener(AppConfig.TIMER_KEY_RECORD_PLAY_GAME, new OnTimeRecordListener() {
            @Override public void onTimeRecording(String key) {
                /*
                    TimeRecorder.isSecondTimeArrived(TIMER_KEY_RECORD_PLAY_GAME, needSecond);       // 是否到达了统计的秒数
                    TimeRecorder.isMinuteTimeArrived(TIMER_KEY_RECORD_PLAY_GAME, needSecond);       // 是否到达了统计的分数
                    TimeRecorder.getRecordSecond(TIMER_KEY_RECORD_PLAY_GAME)                        // 获取当前已经统计的秒数
                 */
                // 更新页面当前跟踪的时长
                recordTV.setText(String.format("计时时间：%d", TimeRecorder.getRecordSecond(AppConfig.TIMER_KEY_RECORD_PLAY_GAME)));
            }
        });
    }

    @Override public void bind(IViewModel model) {
        vm = (Time1VM) model;
        // 进入页面后显示已经跟踪的时长
        recordTV.setText(String.format("计时时间：%d", TimeRecorder.getRecordSecond(AppConfig.TIMER_KEY_RECORD_PLAY_GAME)));
    }

    /**
     * click: startRecordBT, stopRecordBT, resetRecordBT
     * 统计时间：开始统计、停止统计、重置统计
     */
    @ViewClick(ids = {R.id.startRecordBT, R.id.stopRecordBT, R.id.resetRecordBT})
    private void clickRecordTime(View v) {
        switch (v.getId()) {
            case R.id.startRecordBT:
                TimeRecorder.startRecord(AppConfig.TIMER_KEY_RECORD_PLAY_GAME);
                break;
            case R.id.stopRecordBT:
                TimeRecorder.stopRecord(AppConfig.TIMER_KEY_RECORD_PLAY_GAME);
                break;
            case R.id.resetRecordBT:
                // 当重置时间跟踪之后统计的时长会重置为 0，页面需要立刻更新以保证显示的正确性
                TimeRecorder.resetTimeRecorder(AppConfig.TIMER_KEY_RECORD_PLAY_GAME);
                recordTV.setText(String.format("计时时间：%d", TimeRecorder.getRecordSecond(AppConfig.TIMER_KEY_RECORD_PLAY_GAME)));
                break;
        }
    }

    @Override protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        /*
        由于时间跟踪是一个持续性的过程，若是在本页面设施了相应的回调则会导致内存泄漏
            1. 可以通过取消回调监听来防止内存泄漏
            2. 可以通过停止跟踪来防止内存泄漏
        具体的情况根据实际的业务来决定
         */
//        TimeRecorder.setOnTimeRecordListener(AppConfig.TIMER_KEY_RECORD_PLAY_GAME, null);     // 取消跟踪回调
        TimeRecorder.stopRecord(AppConfig.TIMER_KEY_RECORD_PLAY_GAME);                                            // 停止时间跟踪
    }
}