package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.ulfy.android.bus.BusUtils;
import com.ulfy.android.bus.Subscribe;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.EventBusVM;
import com.ulfy.master.ui.base.BaseView;
import com.ulfy.master.ui.event.EventBusEvent;

@Layout(id = R.layout.view_event_bus)
public class EventBusView extends BaseView {
    @ViewById(id = R.id.contextPostEventBT) private Button contextPostEventBT;
    @ViewById(id = R.id.globlePostEventBT) private Button globlePostEventBT;
    private EventBusVM vm;

    public EventBusView(Context context) {
        super(context);
        init(context, null);
    }

    public EventBusView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (EventBusVM) model;
    }

    /*
        上下文发布事件只在当前 Activity 的上下文内有效、全局发布事件在整个 app 内都有效
        @Subscribe 支持参数，默认为主线程。可选有：MethodMode.MAIN、MethodMode.SAME_THREAD、MethodMode.BACKGROUND、MethodMode.ASYNC
     */

    /**
     * click: contextPustEventBT, globlePustEventBT
     * 上下文发布事件、点击全局发布事件
     */
    @ViewClick(ids = {R.id.contextPostEventBT, R.id.globlePostEventBT})
    private void options(View v) {
        switch (v.getId()) {
            case R.id.contextPostEventBT:
                BusUtils.post(getContext(), new EventBusEvent());
                break;
            case R.id.globlePostEventBT:
                BusUtils.post(new EventBusEvent());
                break;
        }
    }

    @Subscribe private void EventBusEvent(EventBusEvent event) {
        UiUtils.show("收到了事件");
    }
}