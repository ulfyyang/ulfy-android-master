package com.ulfy.master.domain.cache;

import com.ulfy.android.cache.CacheUtils;
import com.ulfy.android.system.AppUtils;
import com.ulfy.android.utils.StringUtils;

import java.io.Serializable;

public class AppCache implements Serializable {
    private static final long serialVersionUID = -5779821089279136150L;
    private boolean firstStart = true;      // 是否是第一次启动
    private String deviceId;                // 存储手机唯一标识

    private AppCache() { }

    public static AppCache getInstance() {
        return CacheUtils.isCached(AppCache.class) ? CacheUtils.getCache(AppCache.class) : CacheUtils.cache(new AppCache());
    }

    /**
     * 是否是首次启动
     * @return 首次调用返回true，之后的调用返回false
     */
    public boolean isFirstStart() {
        boolean firstStart = this.firstStart;
        if (firstStart) {
            this.firstStart = false;
            updateToCache();
        }
        return firstStart;
    }

    /**
     * 获取设备唯一标识
     */
    public String deviceId() {
        if (StringUtils.isEmpty(deviceId)) {
            deviceId = AppUtils.getDevideId();
            updateToCache();
        }
        return deviceId;
    }

    private void updateToCache() {
        CacheUtils.cache(this);
    }
}
