package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.ulfy.android.dialog.DialogConfig;
import com.ulfy.android.dialog.NormalDialog;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task.TaskExecutor;
import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_extension.UiTimer;
import com.ulfy.android.task_transponder.SilentProcesser;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.UpgradeCheckVM;
import com.ulfy.master.application.vm.UpgradeVM;
import com.ulfy.master.domain.cache.UpgradeInfo;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_upgrade_check)
public class UpgradeCheckView extends BaseView {
    @ViewById(id = R.id.upgradeCheckBT) private Button upgradeCheckBT;
    private UpgradeVM upgradeVM = new UpgradeVM();
    private UiTimer upgradeCheckTimer = new UiTimer(1000);      // 演示使用采用1秒钟，实际项目建议10分钟检查一次（10 * 60 * 1000）
    private UpgradeCheckVM vm;

    public UpgradeCheckView(Context context) {
        super(context);
        init(context, null);
    }

    public UpgradeCheckView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /*
        测试用例：
            1. 手动检测多次点击多次弹出升级提示窗口
            2. 强制更新弹窗无法关闭
            3. 非强制更新弹窗可以手动关闭
            4. 自动检测当前页面只显示一次，关闭后不再显示
     */

    private void init(Context context, AttributeSet attrs) {
        initUpgradeCheck();
    }

    /**
     * 初始化升级检测
     */
    private void initUpgradeCheck() {
        upgradeCheckTimer.setUiTimerExecuteBody(new UiTimer.UiTimerExecuteBody() {
            @Override public void onExecute(UiTimer timer, UiTimer.TimerDriver timerDriver) {
                if (upgradeVM.haveShowUpgradeDialog) {
                    upgradeCheckTimer.cancel();
                } else {
                    upgradeIfNeed();
                }
            }
        });
        if (upgradeVM.autoCheck) {
            upgradeCheckTimer.schedule();
        }
    }

    @Override public void bind(IViewModel model) {
        vm = (UpgradeCheckVM) model;
    }

    /**
     * 点击按钮进行手动检测
     */
    @ViewClick(ids = R.id.upgradeCheckBT) private void upgradeCheckBT(View v) {
        upgradeIfNeed();
    }

    /**
     * 调用该方法会进行升级检测，并根据返回结果来处理页面显示
     */
    private void upgradeIfNeed() {
        TaskUtils.loadData(getContext(), upgradeVM.updateUpgradeDataOnExe(), new SilentProcesser() {
                    @Override public void onSuccess(Object tipData) {
                        if (UpgradeInfo.getInstance().shouldUpgrade()) {
                            upgradeVM.haveShowUpgradeDialog = true;         // 记录显示了一次弹窗了
                            new NormalDialog.Builder(getContext(), UiUtils.createView(getContext(), upgradeVM))
                                    .setDialogId(DialogConfig.ULFY_MAIN_DIALOG_ID)
                                    .setTouchOutsideDismiss(!UpgradeInfo.getInstance().forceUpgrade())
                                    .setCancelable(!UpgradeInfo.getInstance().forceUpgrade())
                                    .build().show();
                        }
                    }
                }, TaskExecutor.defaultSingleTaskExecutor()     // 采用了单任务执行器，防止任务重复执行
        );
    }

    /**
     * 即时释放升级检测定时器
     */
    @Override protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        upgradeCheckTimer.cancel();
    }
}