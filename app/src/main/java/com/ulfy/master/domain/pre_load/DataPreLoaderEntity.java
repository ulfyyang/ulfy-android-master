package com.ulfy.master.domain.pre_load;

import com.ulfy.android.data_pre_loader.DataPreLoaderManager;

import java.util.Random;

public class DataPreLoaderEntity implements DataPreLoaderManager.PreLoadDataLoader<DataPreLoaderEntity> {
    public String content;

    @Override public DataPreLoaderEntity loadData() throws Exception {
        Thread.sleep(5000);
        content = String.format("加载的内容：%d", new Random().nextInt(10));
        return this;
    }
}
