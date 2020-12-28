package com.ulfy.master.application.vm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadListPageUiTask;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.application.cm.LittleVideoCM;
import com.ulfy.master.ui.view.LittleVideoPageView;

import java.util.ArrayList;
import java.util.List;

public class LittleVideoPageVM extends BaseVM {
    public List<LittleVideoCM> videoCMList = new ArrayList<>();
    public LoadListPageUiTask.LoadListPageUiTaskInfo<LittleVideoCM> videoTaskInfo = new LoadListPageUiTask.LoadListPageUiTaskInfo<>(videoCMList);

    public LoadListPageUiTask.OnLoadListPage loadDataPerPageOnExe() {
        return new LoadListPageUiTask.OnLoadSimpleListPage() {
            @Override protected void loadSimplePage(LoadListPageUiTask task, List<Object> modelList, List<Object> tempList, int page, int pageSize) throws Exception {
                Thread.sleep(1000);

                tempList.add(new LittleVideoCM(
                        "http://p9-dy.byteimg.com/large/tos-cn-p-0015/2e6b13b31a2a40b2aadaade01387584f_1575456802.jpeg?from=2563711402_large",
                        "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200fc40000bnjp049evctvb2f04l90&line=0&ratio=480p&watermark=1&media_type=4&vr_type=0&improve_bitrate=0&logo_name=aweme"));
                tempList.add(new LittleVideoCM(
                        "http://p1-dy.byteimg.com/large/tos-cn-p-0015/d6addaee76f3495d840d6dff8d2216e0_1575363173.jpeg?from=2563711402_large",
                        "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200f470000bnj24h87q8i137v8k2t0&line=0&ratio=540p&watermark=1&media_type=4&vr_type=0&improve_bitrate=0&logo_name=aweme"));
                tempList.add(new LittleVideoCM(
                        "http://p3-dy.byteimg.com/large/tos-cn-p-0015/818d1fad6be3458e940dcd4b5e3bdaf9_1575374652.jpeg?from=2563711402_large",
                        "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200f120000bnj4u9egncodds6bjgm0&line=0&ratio=540p&watermark=1&media_type=4&vr_type=0&improve_bitrate=0&logo_name=aweme"));
                tempList.add(new LittleVideoCM(
                        "http://p9-dy.byteimg.com/large/tos-cn-p-0015/2d4110d5d01c4bf38b69791bae43c89e_1575435339.jpeg?from=2563711402_large",
                        "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200fc20000bnjjodd8n75ja660q7k0&line=0&ratio=540p&watermark=1&media_type=4&vr_type=0&improve_bitrate=0&logo_name=aweme"));
                tempList.add(new LittleVideoCM(
                        "http://p9-dy.byteimg.com/large/tos-cn-p-0015/11ec23a65dd7452b9d08740038bbfec0_1575378425.jpeg?from=2563711402_large",
                        "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200ffb0000bnj5ro0a2pele7j5h0lg&line=0&ratio=540p&watermark=1&media_type=4&vr_type=0&improve_bitrate=0&logo_name=aweme"));

                tempList.add(new LittleVideoCM(
                        "http://p9-dy.byteimg.com/large/tos-cn-p-0015/2e6b13b31a2a40b2aadaade01387584f_1575456802.jpeg?from=2563711402_large",
                        "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200fc40000bnjp049evctvb2f04l90&line=0&ratio=480p&watermark=1&media_type=4&vr_type=0&improve_bitrate=0&logo_name=aweme"));
                tempList.add(new LittleVideoCM(
                        "http://p1-dy.byteimg.com/large/tos-cn-p-0015/d6addaee76f3495d840d6dff8d2216e0_1575363173.jpeg?from=2563711402_large",
                        "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200f470000bnj24h87q8i137v8k2t0&line=0&ratio=540p&watermark=1&media_type=4&vr_type=0&improve_bitrate=0&logo_name=aweme"));
                tempList.add(new LittleVideoCM(
                        "http://p3-dy.byteimg.com/large/tos-cn-p-0015/818d1fad6be3458e940dcd4b5e3bdaf9_1575374652.jpeg?from=2563711402_large",
                        "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200f120000bnj4u9egncodds6bjgm0&line=0&ratio=540p&watermark=1&media_type=4&vr_type=0&improve_bitrate=0&logo_name=aweme"));
                tempList.add(new LittleVideoCM(
                        "http://p9-dy.byteimg.com/large/tos-cn-p-0015/2d4110d5d01c4bf38b69791bae43c89e_1575435339.jpeg?from=2563711402_large",
                        "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200fc20000bnjjodd8n75ja660q7k0&line=0&ratio=540p&watermark=1&media_type=4&vr_type=0&improve_bitrate=0&logo_name=aweme"));
                tempList.add(new LittleVideoCM(
                        "http://p9-dy.byteimg.com/large/tos-cn-p-0015/11ec23a65dd7452b9d08740038bbfec0_1575378425.jpeg?from=2563711402_large",
                        "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200ffb0000bnj5ro0a2pele7j5h0lg&line=0&ratio=540p&watermark=1&media_type=4&vr_type=0&improve_bitrate=0&logo_name=aweme"));

                tempList.add(new LittleVideoCM(
                        "http://p9-dy.byteimg.com/large/tos-cn-p-0015/2e6b13b31a2a40b2aadaade01387584f_1575456802.jpeg?from=2563711402_large",
                        "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200fc40000bnjp049evctvb2f04l90&line=0&ratio=480p&watermark=1&media_type=4&vr_type=0&improve_bitrate=0&logo_name=aweme"));
                tempList.add(new LittleVideoCM(
                        "http://p1-dy.byteimg.com/large/tos-cn-p-0015/d6addaee76f3495d840d6dff8d2216e0_1575363173.jpeg?from=2563711402_large",
                        "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200f470000bnj24h87q8i137v8k2t0&line=0&ratio=540p&watermark=1&media_type=4&vr_type=0&improve_bitrate=0&logo_name=aweme"));
                tempList.add(new LittleVideoCM(
                        "http://p3-dy.byteimg.com/large/tos-cn-p-0015/818d1fad6be3458e940dcd4b5e3bdaf9_1575374652.jpeg?from=2563711402_large",
                        "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200f120000bnj4u9egncodds6bjgm0&line=0&ratio=540p&watermark=1&media_type=4&vr_type=0&improve_bitrate=0&logo_name=aweme"));
                tempList.add(new LittleVideoCM(
                        "http://p9-dy.byteimg.com/large/tos-cn-p-0015/2d4110d5d01c4bf38b69791bae43c89e_1575435339.jpeg?from=2563711402_large",
                        "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200fc20000bnjjodd8n75ja660q7k0&line=0&ratio=540p&watermark=1&media_type=4&vr_type=0&improve_bitrate=0&logo_name=aweme"));
                tempList.add(new LittleVideoCM(
                        "http://p9-dy.byteimg.com/large/tos-cn-p-0015/11ec23a65dd7452b9d08740038bbfec0_1575378425.jpeg?from=2563711402_large",
                        "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200ffb0000bnj5ro0a2pele7j5h0lg&line=0&ratio=540p&watermark=1&media_type=4&vr_type=0&improve_bitrate=0&logo_name=aweme"));

                tempList.add(new LittleVideoCM(
                        "http://p9-dy.byteimg.com/large/tos-cn-p-0015/2e6b13b31a2a40b2aadaade01387584f_1575456802.jpeg?from=2563711402_large",
                        "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200fc40000bnjp049evctvb2f04l90&line=0&ratio=480p&watermark=1&media_type=4&vr_type=0&improve_bitrate=0&logo_name=aweme"));
                tempList.add(new LittleVideoCM(
                        "http://p1-dy.byteimg.com/large/tos-cn-p-0015/d6addaee76f3495d840d6dff8d2216e0_1575363173.jpeg?from=2563711402_large",
                        "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200f470000bnj24h87q8i137v8k2t0&line=0&ratio=540p&watermark=1&media_type=4&vr_type=0&improve_bitrate=0&logo_name=aweme"));
                tempList.add(new LittleVideoCM(
                        "http://p3-dy.byteimg.com/large/tos-cn-p-0015/818d1fad6be3458e940dcd4b5e3bdaf9_1575374652.jpeg?from=2563711402_large",
                        "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200f120000bnj4u9egncodds6bjgm0&line=0&ratio=540p&watermark=1&media_type=4&vr_type=0&improve_bitrate=0&logo_name=aweme"));
                tempList.add(new LittleVideoCM(
                        "http://p9-dy.byteimg.com/large/tos-cn-p-0015/2d4110d5d01c4bf38b69791bae43c89e_1575435339.jpeg?from=2563711402_large",
                        "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200fc20000bnjjodd8n75ja660q7k0&line=0&ratio=540p&watermark=1&media_type=4&vr_type=0&improve_bitrate=0&logo_name=aweme"));
                tempList.add(new LittleVideoCM(
                        "http://p9-dy.byteimg.com/large/tos-cn-p-0015/11ec23a65dd7452b9d08740038bbfec0_1575378425.jpeg?from=2563711402_large",
                        "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200ffb0000bnj5ro0a2pele7j5h0lg&line=0&ratio=540p&watermark=1&media_type=4&vr_type=0&improve_bitrate=0&logo_name=aweme"));
            }
        };
    }

    @Override public Class<? extends IView> getViewClass() {
        return LittleVideoPageView.class;
    }
}