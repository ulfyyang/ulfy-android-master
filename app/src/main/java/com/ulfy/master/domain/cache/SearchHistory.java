package com.ulfy.master.domain.cache;

import com.ulfy.android.cache.CacheUtils;
import com.ulfy.android.utils.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SearchHistory implements Serializable {
    private static final long serialVersionUID = 8961895108018722641L;
    public static final int MAX_WORD = 10;                                  // 最多缓存的关键词
    public List<String> searchHistory = new ArrayList<>();                  // 存储搜索的关键词

    /**
     * 私有化构造方法
     */
    private SearchHistory() { }

    /**
     * 获取实例
     */
    public static SearchHistory getInstance() {
        return CacheUtils.isCached(SearchHistory.class) ? CacheUtils.getCache(SearchHistory.class) : CacheUtils.cache(new SearchHistory());
    }

    /**
     * 添加搜索关键词
     */
    public boolean addSearchHistory(String content) {
        if (!StringUtils.isEmpty(content) && searchHistory.size() < MAX_WORD && !searchHistory.contains(content)) {
            searchHistory.add(0, content);
            updateToCache();
            return true;
        } else {
            return false;
        }
    }

    /**
     * 清除缓存
     */
    public static void clearCache() {
        CacheUtils.deleteCache(SearchHistory.class);
    }

    /**
     * 更新到缓存
     */
    private void updateToCache() {
        CacheUtils.cache(this);
    }
}
