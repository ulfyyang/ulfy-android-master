package com.ulfy.master.application.vm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.task.LoadListPageUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.android.utils.StringUtils;
import com.ulfy.master.MainApplication;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.application.cm.DouyinCM;
import com.ulfy.master.application.cm.LittleVideoCM;
import com.ulfy.master.ui.custom_dkplayer.cache.PreloadManager;
import com.ulfy.master.ui.view.DouyinView;

import java.util.ArrayList;
import java.util.List;

public class DouyinVM extends BaseVM {
    public List<DouyinCM> douyinCMList = new ArrayList<>();
    public LoadListPageUiTask.LoadListPageUiTaskInfo<DouyinCM> douyinTaskInfo = new LoadListPageUiTask.LoadListPageUiTaskInfo<>(douyinCMList);
    private int index = 0;       // 用来计算元素的位置，位置必须正确，否则分页以后会显示不正常
    public int enterPosition;    // 用于记录点进来的是哪个位置，显示时直接跳转到该位置
    public int currentPosition;  // 当前正在显示的位置

    public void init(List<LittleVideoCM> videoCMList, LoadListPageUiTask.LoadListPageUiTaskInfo taskInfo, int enterPosition) {
        for (int i = 0; i < videoCMList.size(); i++) {
            this.douyinCMList.add(new DouyinCM(i, videoCMList.get(i).imageUrl, videoCMList.get(i).videoUrl));
        }
        this.douyinTaskInfo.setCurrentPointer(taskInfo.getCurrentPage());
        this.index = videoCMList.size();        // 因为已经加载了类表里边的数据，此时的index应该会在下载分页时直接使用，index应该是下次分页的其实位置
        this.enterPosition = enterPosition;
    }

    public LoadDataUiTask.OnExecute loadDataOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在加载...");

                    task.notifySuccess("加载完成");
                } catch (Exception e) {
                    LogUtils.log("加载失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }

    public LoadListPageUiTask.OnLoadListPage loadDataPerPageOnExe() {
        return new LoadListPageUiTask.OnLoadSimpleListPage() {
            @Override protected void loadSimplePage(LoadListPageUiTask task, List<Object> modelList, List<Object> tempList, int page, int pageSize) throws Exception {
                if (page == LoadListPageUiTask.DEFAULT_START_PAGE) {        // 加载第一页时重置位置计数
                    index = 0;
                }
                tempList.add(new DouyinCM(index++,
                        "http://p9-dy.byteimg.com/large/tos-cn-p-0015/2e6b13b31a2a40b2aadaade01387584f_1575456802.jpeg?from=2563711402_large",
                        "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200fc40000bnjp049evctvb2f04l90&line=0&ratio=480p&watermark=1&media_type=4&vr_type=0&improve_bitrate=0&logo_name=aweme"));
                tempList.add(new DouyinCM(index++,
                        "http://p1-dy.byteimg.com/large/tos-cn-p-0015/d6addaee76f3495d840d6dff8d2216e0_1575363173.jpeg?from=2563711402_large",
                        "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200f470000bnj24h87q8i137v8k2t0&line=0&ratio=540p&watermark=1&media_type=4&vr_type=0&improve_bitrate=0&logo_name=aweme"));
                tempList.add(new DouyinCM(index++,
                        "http://p3-dy.byteimg.com/large/tos-cn-p-0015/818d1fad6be3458e940dcd4b5e3bdaf9_1575374652.jpeg?from=2563711402_large",
                        "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200f120000bnj4u9egncodds6bjgm0&line=0&ratio=540p&watermark=1&media_type=4&vr_type=0&improve_bitrate=0&logo_name=aweme"));
                tempList.add(new DouyinCM(index++,
                        "http://p9-dy.byteimg.com/large/tos-cn-p-0015/2d4110d5d01c4bf38b69791bae43c89e_1575435339.jpeg?from=2563711402_large",
                        "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200fc20000bnjjodd8n75ja660q7k0&line=0&ratio=540p&watermark=1&media_type=4&vr_type=0&improve_bitrate=0&logo_name=aweme"));
                tempList.add(new DouyinCM(index++,
                        "http://p9-dy.byteimg.com/large/tos-cn-p-0015/11ec23a65dd7452b9d08740038bbfec0_1575378425.jpeg?from=2563711402_large",
                        "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200ffb0000bnj5ro0a2pele7j5h0lg&line=0&ratio=540p&watermark=1&media_type=4&vr_type=0&improve_bitrate=0&logo_name=aweme"));
            }
        };
    }


    public void preloadByCurrentPosition() {
        // 预加载范围计算：当前位置、当前位置前两个、当前位置后两个，一共是五个（如果超出范围要纠正）
        int start = currentPosition - 2;
        if (start < 0) {
            start = 0;
        }
        int end = currentPosition + 2;
        if (end > douyinCMList.size() - 1) {
            end = douyinCMList.size() - 1;
        }
        // 开启选中范围的预加载
        for (int i = start; i <= end; i++) {
            String playUrl = douyinCMList.get(i).videoUrl;
            if (!StringUtils.isEmpty(playUrl)) {
                PreloadManager.getInstance(MainApplication.application).addPreloadTask(playUrl, i);
            }
        }
        // 在滚动的过程中，取消范围之外一个位置的预加载（滑到下一个则上方会多一个超出范围，反之一样的道理）
        int lastTop = start - 1, nextBottom = end + 1;
        if (lastTop >= 0) {         // 表示范围之外存在一个否则就是超出了原始数据范围
            String playUrl = douyinCMList.get(lastTop).videoUrl;
            if (!StringUtils.isEmpty(playUrl)) {
                PreloadManager.getInstance(MainApplication.application).removePreloadTask(playUrl);
            }
        }
        if (nextBottom <= douyinCMList.size() - 1) {
            String playUrl = douyinCMList.get(nextBottom).videoUrl;
            if (!StringUtils.isEmpty(playUrl)) {
                PreloadManager.getInstance(MainApplication.application).removePreloadTask(playUrl);
            }
        }
    }


    @Override public Class<? extends IView> getViewClass() {
        return DouyinView.class;
    }
}