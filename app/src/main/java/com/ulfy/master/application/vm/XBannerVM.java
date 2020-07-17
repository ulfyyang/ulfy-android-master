package com.ulfy.master.application.vm;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;
import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.domain.entity.Banner;
import com.ulfy.master.ui.view.XBannerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class XBannerVM extends BaseVM {
    public List<XBannerData> xBannerDataList = new ArrayList<>();

    public LoadDataUiTask.OnExecute loadDataOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在加载...");
                    // 模拟加载出来的业务轮播图数据
                    List<Banner> bannerList = Arrays.asList(
                            new Banner("http://static.runoob.com/images/demo/demo1.jpg"),
                            new Banner("http://static.runoob.com/images/demo/demo1.jpg"),
                            new Banner("http://pic.sc.chinaz.com/files/pic/pic9/201902/zzpic16755.jpg"),
                            new Banner("http://pic.sc.chinaz.com/files/pic/pic9/201902/zzpic16755.jpg")
                    );
                    // 将业务数据转化为 XBanner 要求的数据
                    for (Banner banner : bannerList) {
                        xBannerDataList.add(new XBannerData(banner));
                    }
                    task.notifySuccess("加载完成");
                } catch (Exception e) {
                    LogUtils.log("加载失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }

    public static class XBannerData extends SimpleBannerInfo {
        private Banner banner;

        public XBannerData(Banner banner) {
            this.banner = banner;
        }

        @Override public Object getXBannerUrl() {
            return banner.url;
        }
    }

    @Override public Class<? extends IView> getViewClass() {
        return XBannerView.class;
    }
}