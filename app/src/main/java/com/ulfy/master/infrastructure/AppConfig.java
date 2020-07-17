package com.ulfy.master.infrastructure;

import android.app.Activity;

import com.ulfy.android.bus.BusUtils;
import com.ulfy.android.bus.Subscribe;
import com.ulfy.android.system.ActivityUtils;
import com.ulfy.android.time.TimeRecorder;
import com.ulfy.master.domain.entity.User;
import com.ulfy.master.ui.activity.MainActivity;
import com.ulfy.master.ui.event.OnLoginActionEvent;
import com.ulfy.master.ui.event.OnUserUpdateEvent;

/**
 * 这个类里边放一些统一的配置和公共行为信息
 */
public class AppConfig {
    /**
     * 定义时间跟踪的 key
     */
    public static final String TIMER_KEY_RECORD_PLAY_GAME = "TIMER_KEY_RECORD_PLAY_GAME";       // 声明用于跟踪的 key
    public static final String TIME_KEY_RECORD_LOGIN_INFO = "TIME_KEY_RECORD_LOGIN_INFO";       // 声明用于时间判定的KEY
    public static Class<? extends Activity> loginActivityClazz = MainActivity.class;            // 这里需要替换为登录的页面

    /**
     * 如果未登录则跳转到登录页面
     * @return 当前是否是登录状态
     */
    public static boolean gotoLoginIfNeed() {
        if (!User.isLogin()) {
            ActivityUtils.startActivity(loginActivityClazz);
        }
        return User.isLogin();
    }

    /**
     * 当用户登录后才执行
     *      如果用户已经登录则直接执行
     *      如果用户未登录则跳转到登录页面，用户登录后再执行
     */
    public static void executeAfterUserLogined(Runnable runnable) {
        if (runnable != null) {
            if (User.isLogin()) {           // 如果登录则直接执行
                runnable.run();
            }
            /*
            如果未登录则跳转到登录页面，等待登录事件的到来。要在登录流程相关的页面上发布 OnLoginActionEvent 事件
                在登录流程退出的各个点要发布：OnLoginActionEvent.STATUS_LOGIN_CANCEL 事件；
                在登录成功后要发布：OnLoginActionEvent.STATUS_LOGIN_SUCCESS 事件；
            确保登录流程退出的各个点都要正确的发布事件，这样接收者收到事件后会释放自己，否则会造成内存泄漏问题。
             */
            else {
                ActivityUtils.startActivity(loginActivityClazz);
                BusUtils.register(new LoginStateChangedEventSubscriber(runnable));      // 和具体的上下文绑定，这样当该向下文被销毁时相应的事件也会被释放
            }
        }
    }

    public static class LoginStateChangedEventSubscriber {
        private Runnable runnable;

        public LoginStateChangedEventSubscriber(Runnable runnable) {
            this.runnable = runnable;
        }

        @Subscribe public void OnLoginStateChangedEvent(OnLoginActionEvent event) {
            BusUtils.unregister(this);      // 释放掉之前注册的事件，如果不释放，当其它因素导致的登录变化也容易引发二次执行
            if (event.loginStatus == OnLoginActionEvent.STATUS_LOGIN_SUCCESS) { // 执行登录之后的逻辑
                runnable.run();
            }
        }
    }

    public static void logout() {
        User.deleteCurrentUser();
        TimeRecorder.resetTimeRecorder();
        BusUtils.post(new OnUserUpdateEvent());
        BusUtils.post(new OnLoginActionEvent(OnLoginActionEvent.STATUS_LOGOUT));
    }
}
