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

import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
     * 定义一些共有的数据
     */
    public static List<String> pictureUrlList;              // 公共图片配置

    static {
        pictureUrlList = Arrays.asList(
                "http://static.runoob.com/images/demo/demo1.jpg",
                "http://pic.sc.chinaz.com/files/pic/pic9/201902/zzpic16755.jpg",
                "http://img01.cztv.com/201605/23/72f53f1af0862021fa283780e9732b08.jpg",
                "https://i.keaitupian.net/up/af/b1/01/93d4aee9e82718d98c38f4994d01b1af.jpg",

                "http://upload.art.ifeng.com/2017/0425/1493105660290.jpg",
                "http://pic1.win4000.com/wallpaper/2019-04-25/5cc14aaa3eb0a.jpg",
                "http://pic1.win4000.com/wallpaper/2018-12-29/5c27352aef6ce.jpg",
                "https://www.mirrormedia.com.tw/assets/images/20190128152517-a2b611041e8d8b75af07cb61d34f3119-mobile.jpg",
                "http://up.deskcity.org/pic/18/2e/04/182e04f62f1aebf9089ed2275d26de21.jpg",

                "https://pic.sucaibar.com/pic/201612/13/417aebfcc2.jpg",
                "https://img95.699pic.com/photo/50121/7530.jpg_wh860.jpg",
                "https://static.qiyuange.com/uploads/allimg/200520/9-20052009544VS.jpg",
                "https://www.mysearchome.cn/article/doctype13/doc31231/img031231_1559033672.jpg",
                "https://pic1.shejiben.com/case/2018/11/23/20181123160023-7bba87ae-la.jpg",
                "https://fotor-cn-website-upload-prod.oss-cn-beijing.aliyuncs.com/images/carousel/%E6%9C%AA%E5%91%BD%E5%90%8D%E8%AE%BE%E8%AE%A1-33.jpg",
                "https://bpic.588ku.com/element_origin_min_pic/19/03/07/e31692990e58dc4257ddc8408ca662e6.jpg",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcT7v_9u-c7ljvl6tW73EYYveP4bAEiAWLqrfg&usqp=CAU"
        );
    }

    /**
     * 随机从图片中选取一张
     */
    public static String getPictureUrlRandom() {
        return pictureUrlList.get(new Random().nextInt(pictureUrlList.size()));
    }

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
