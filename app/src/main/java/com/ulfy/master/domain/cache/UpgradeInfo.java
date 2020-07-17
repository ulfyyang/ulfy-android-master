package com.ulfy.master.domain.cache;

import com.ulfy.android.cache.CacheUtils;

import java.io.Serializable;

public class UpgradeInfo implements Serializable {
    private static final long serialVersionUID = 2468327800629365078L;
    public String description;      // 升级描述信息
    public String downloadUrl;      // 直接下载的地址
    public String downloadPage;     // 进入Web页下载的地址
    public boolean shouldUpgrad;    // 是否应该升级
    public boolean forceUpgrade;    // 是否强制升级

    /**
     * 获取更新信息
     */
    public static UpgradeInfo getInstance() {
        return CacheUtils.isCached(UpgradeInfo.class) ? CacheUtils.getCache(UpgradeInfo.class) : CacheUtils.cache(new UpgradeInfo());
    }

    /**
     * 是否应该升级
     *      该方法根据具体的业务规则编写
     */
    public boolean shouldUpgrade() {
        return shouldUpgrad;
    }

    /**
     * 是否强制升级
     *      强制升级时弹出窗口无法关闭，非强制升级弹出窗口可以关闭
     */
    public boolean forceUpgrade() {
        return forceUpgrade;
    }

    /**
     * 更新到缓存
     */
    public void updateToCache() {
        CacheUtils.cache(this);
    }
}
