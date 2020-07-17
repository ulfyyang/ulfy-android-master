package com.ulfy.master.domain.entity;

import java.io.Serializable;

public class ObjectCacheEntity implements Serializable {
    private static final long serialVersionUID = -7112668867130853701L;
    public String name;
    public int age;
    public ObjectCacheInnerEntity innerEntity;
    @Override public String toString() {
        return "ObjectCacheEntity{name='" + name + '\'' + ", age=" + age + ", innerEntity=" + innerEntity + '}';
    }

    public static class ObjectCacheInnerEntity implements Serializable {
        private static final long serialVersionUID = -5303254059776996165L;
        public String innerName;
        @Override public String toString() {
            return "ObjectCacheInnerEntity{innerName='" + innerName + '\'' + '}'; }
    }
}
