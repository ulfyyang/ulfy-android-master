package com.ulfy.master.application.vm;

import com.ulfy.android.image.ImageUtils;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.domain.entity.Advertisement;

public class Splash3VM {
    public Advertisement advertisement;         // 保存加载出来的广告

    /**
     * 加载广告信息
     */
    public LoadDataUiTask.OnExecute loadSplashDataOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在加载...");

                    // 模拟广告加载过程：1. 从后台接口加载广告基本信息；2. 将广告中的图片下载到本地
                    Thread.sleep(1000);
                    Advertisement advertisement = new Advertisement();
                    advertisement.cover = "https://wimg.ruan8.com/uploadimg/image/20190909/20190909152153_79744.jpg";
                    advertisement.url = "http://www.baidu.com";
                    // 预先将广告图片加载到本地
                    if (advertisement != null) {    // 防止后台返回错误数据
                        ImageUtils.download(advertisement.cover);
                    }

                    // 当广告的数据都准备就绪了之后将广告信息赋值给成员变量，其它位置会根据该成员变量来判断广告是否加载成功
                    Splash3VM.this.advertisement = advertisement;

                    task.notifySuccess("加载完成");
                } catch (Exception e) {
                    LogUtils.log("加载失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }

}
