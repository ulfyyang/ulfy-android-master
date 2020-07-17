package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.time.TimeJudger;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.Time2VM;
import com.ulfy.master.infrastructure.AppConfig;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_time2)
public class Time2View extends BaseView {
    @ViewById(id = R.id.timeJudgeBT) private Button timeJudgeBT;
    @ViewById(id = R.id.resetTimeJudgeBT) private Button resetTimeJudgeBT;
    @ViewById(id = R.id.timeJudgeTV) private TextView timeJudgeTV;
    private Time2VM vm;

    public Time2View(Context context) {
        super(context);
        init(context, null);
    }

    public Time2View(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (Time2VM) model;
    }

    /**
     * click: timeJudgeBT, resetTimeJudgeBT
     * 判断是否在指定时间区间内 重置时间判定器
     */
    @ViewClick(ids = {R.id.timeJudgeBT, R.id.resetTimeJudgeBT})
    private void clickTimeJudge(View v) {
        switch (v.getId()) {
            case R.id.timeJudgeBT:
                /*
                判定已经流逝的时间是否在指定的时间范围内，通常可以控制在指定时间可以做的事情
                    1. 区间判定在没有初始化时（首次使用）处于一个不确定的时间基准下，任何的时间区间都不在这个区间内；
                    2. 初始化区间判定会将时间基准重置为当前时间，这时候就会以当前时间为基准来判断已经流逝的时间是否在指定区间之内；
                    3. 区间判定目前仅支持判定指定的天数和小时；
                 */
                timeJudgeTV.setText(String.format("判定结果：%s", TimeJudger.isInDays(AppConfig.TIME_KEY_RECORD_LOGIN_INFO, 2) ? "是" : "否"));
                break;
            case R.id.resetTimeJudgeBT:
                TimeJudger.initTimeJudger(AppConfig.TIME_KEY_RECORD_LOGIN_INFO);
                break;
        }
    }

}