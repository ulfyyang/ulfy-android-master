package com.ulfy.master.application.vm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.android.task.LoadDataUiTask;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.application.base.BaseVM;
import com.ulfy.master.domain.cache.UpgradeInfo;
import com.ulfy.master.ui.view.UpgradeView;

public class UpgradeVM extends BaseVM {
    public boolean autoCheck = true;            // 是否启用自动检测（这里需要说动开启）
    public boolean haveShowUpgradeDialog;       // 定时检查升级提示，如果显示过一次升级弹窗之后本次要在下次打开后显示

    public LoadDataUiTask.OnExecute updateUpgradeDataOnExe() {
        return new LoadDataUiTask.OnExecute() {
            @Override public void onExecute(LoadDataUiTask task) {
                try {
                    task.notifyStart("正在加载...");

                    Thread.sleep(1000);     // 模拟后台加载过程
                    UpgradeInfo upgradeInfo = new UpgradeInfo();
                    upgradeInfo.description = "这里是升级提示！";
                    upgradeInfo.downloadUrl = "http://file.ws.126.net/3g/client/netease_newsreader_android.apk";
                    upgradeInfo.downloadPage = "www.baidu.com";
                    upgradeInfo.shouldUpgrad = true;
                    upgradeInfo.forceUpgrade = false;
                    upgradeInfo.updateToCache();

                    task.notifySuccess("加载完成");
                } catch (Exception e) {
                    LogUtils.log("加载失败", e);
                    task.notifyFail(e);
                }
            }
        };
    }

    @Override public Class<? extends IView> getViewClass() {
        return UpgradeView.class;
    }
}