package com.ulfy.master.domain.entity;

import com.ulfy.android.cache.CacheUtils;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private static final long serialVersionUID = -7397635548130582253L;
    public String nickname;
    public int age;
    public List<Hobby> hobbies;

    public static class Hobby {
        public String title;
        public String describtion;
    }

    public static User getCurrentUser() {
        return CacheUtils.getCache(User.class);
    }

    public static boolean isLogin() {
        return CacheUtils.isCached(User.class);
    }

    public static void updateCurrentUser(User user) {
        // 从旧的对象保存一部分不存在的字段，防止被覆盖。例如 token
//        if (StringUtils.isEmpty(user.accessToken)) {
//            user.accessToken = getCurrentUser().accessToken;
//        }
        CacheUtils.cache(user);
    }

    public static void deleteCurrentUser() {
        CacheUtils.deleteCache(User.class);
    }
}
