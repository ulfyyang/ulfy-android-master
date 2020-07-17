package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ulfy.android.cache.CacheUtils;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.ObjectCacheVM;
import com.ulfy.master.domain.entity.ObjectCacheEntity;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_object_cache)
public class ObjectCacheView extends BaseView {
    @ViewById(id = R.id.isCacheBT) private Button isCacheBT;
    @ViewById(id = R.id.cacheBT) private Button cacheBT;
    @ViewById(id = R.id.getCacheBT) private Button getCacheBT;
    @ViewById(id = R.id.deleteCacheBT) private Button deleteCacheBT;
    @ViewById(id = R.id.deleteAllCacheBT) private Button deleteAllCacheBT;
    @ViewById(id = R.id.isCacheTV) private TextView isCacheTV;
    @ViewById(id = R.id.cacheContenteTV) private TextView cacheContenteTV;
    private ObjectCacheVM vm;

    public ObjectCacheView(Context context) {
        super(context);
        init(context, null);
    }

    public ObjectCacheView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (ObjectCacheVM) model;
    }

    ///////////////////////////////////////////////////////////////////////////
    // 注意：使用该框架时需要在 Application 中注册 CacheUtils.initDefaultCache(this);
    ///////////////////////////////////////////////////////////////////////////

    /**
     * click: isCacheBT, cacheBT, getCacheBT, deleteCacheBT, deleteAllCacheBT
     * 点击：是否缓存了、缓存该对象、读取缓存的对象、删除缓存对象、删除全部缓存
     */
    @ViewClick(ids = {R.id.isCacheBT, R.id.cacheBT, R.id.getCacheBT, R.id.deleteCacheBT, R.id.deleteAllCacheBT})
    private void options(View v) {
        switch (v.getId()) {
            case R.id.isCacheBT:
                isCacheTV.setText(String.format("是否缓存了：%s", CacheUtils.isCached(ObjectCacheEntity.class) ? "是" : "否"));
                break;
            case R.id.cacheBT:
                ObjectCacheEntity cacheEntity = new ObjectCacheEntity();
                cacheEntity.name = "张三";
                cacheEntity.age = 20;
                cacheEntity.innerEntity = new ObjectCacheEntity.ObjectCacheInnerEntity();
                cacheEntity.innerEntity.innerName = "张三内部";
                CacheUtils.cache(cacheEntity);
                break;
            case R.id.getCacheBT:
                ObjectCacheEntity cachedEntity = CacheUtils.getCache(ObjectCacheEntity.class);
                cacheContenteTV.setText(String.format("缓存内容：%s", cachedEntity == null ? "无" : cachedEntity.toString()));
                break;
            case R.id.deleteCacheBT:
                CacheUtils.deleteCache(ObjectCacheEntity.class);
                break;
            case R.id.deleteAllCacheBT:
                CacheUtils.deleteAllCache();
                break;
        }
    }
}