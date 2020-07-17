package com.ulfy.master.application.vm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.application.cm.DouyinCM;
import com.ulfy.master.ui.view.DouyinView;

import java.util.ArrayList;
import java.util.List;

public class DouyinVM extends BaseVM {
    public List<DouyinCM> douyinCMList = new ArrayList<>();

    public LoadDataUiTask.OnExecute loadDataOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在加载...");
                    douyinCMList.add(new DouyinCM("https://p9.pstatp.com/large/4c87000639ab0f21c285.jpeg", "https://aweme.snssdk.com/aweme/v1/play/?video_id=97022dc18711411ead17e8dcb75bccd2&line=0&ratio=720p&media_type=4&vr_type=0"));
                    douyinCMList.add(new DouyinCM("https://p1.pstatp.com/large/4bea0014e31708ecb03e.jpeg", "https://aweme.snssdk.com/aweme/v1/play/?video_id=374e166692ee4ebfae030ceae117a9d0&line=0&ratio=720p&media_type=4&vr_type=0"));
                    douyinCMList.add(new DouyinCM("https://p1.pstatp.com/large/4bb500130248a3bcdad0.jpeg", "https://aweme.snssdk.com/aweme/v1/play/?video_id=8a55161f84cb4b6aab70cf9e84810ad2&line=0&ratio=720p&media_type=4&vr_type=0"));
                    douyinCMList.add(new DouyinCM("https://p9.pstatp.com/large/4b8300007d1906573584.jpeg", "https://aweme.snssdk.com/aweme/v1/play/?video_id=47a9d69fe7d94280a59e639f39e4b8f4&line=0&ratio=720p&media_type=4&vr_type=0"));
                    douyinCMList.add(new DouyinCM("https://p9.pstatp.com/large/4b61000b6a4187626dda.jpeg", "https://aweme.snssdk.com/aweme/v1/play/?video_id=3fdb4876a7f34bad8fa957db4b5ed159&line=0&ratio=720p&media_type=4&vr_type=0"));
                    task.notifySuccess("加载完成");
                } catch (Exception e) {
                    LogUtils.log("加载失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }

    @Override public Class<? extends IView> getViewClass() {
        return DouyinView.class;
    }
}