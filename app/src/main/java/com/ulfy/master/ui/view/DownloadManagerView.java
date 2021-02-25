package com.ulfy.master.ui.view;

import android.Manifest;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.ulfy.android.adapter.RecyclerAdapter;
import com.ulfy.android.bus.Subscribe;
import com.ulfy.android.dialog.AlertDialog;
import com.ulfy.android.dialog.DialogUtils;
import com.ulfy.android.download_manager.DownloadManager;
import com.ulfy.android.download_manager.DownloadTaskWrapper;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.system.AppUtils;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.utils.RecyclerViewUtils;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.android.views.FingerFollowLayout;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.DownloadManagerDownloadedCM;
import com.ulfy.master.application.cm.DownloadManagerDownloadingCM;
import com.ulfy.master.application.vm.DownloadManagerVM;
import com.ulfy.master.domain.entity.DownloadMovie;
import com.ulfy.master.ui.base.BaseView;

import java.util.List;
import java.util.Random;

@Layout(id = R.layout.view_download_manager)
public class DownloadManagerView extends BaseView {
    @ViewById(id = R.id.downloadApkBT) private Button downloadApkBT;
    @ViewById(id = R.id.downloadImageBT) private Button downloadImageBT;
    @ViewById(id = R.id.downloadingTaskRV) private RecyclerView downloadingTaskRV;
    @ViewById(id = R.id.downloadedTaskRV) private RecyclerView downloadedTaskRV;
    @ViewById(id = R.id.downloadFFL) private FingerFollowLayout downloadFFL;
    @ViewById(id = R.id.downloadCPV) private com.ulfy.master.ui.custom.CircleProgressView downloadCPV;
    private RecyclerAdapter<DownloadManagerDownloadingCM> downloadingAdapter = new RecyclerAdapter<>();
    private RecyclerAdapter<DownloadManagerDownloadedCM> downloadedCMAdapter = new RecyclerAdapter<>();
    private DownloadManagerVM vm;

    public DownloadManagerView(Context context) {
        super(context);
        init(context, null);
    }

    public DownloadManagerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        RecyclerViewUtils.linearLayout(downloadingTaskRV).vertical();
        downloadingTaskRV.setAdapter(downloadingAdapter);
        downloadingAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener<DownloadManagerDownloadingCM>() {
            @Override public void onItemClick(ViewGroup parent, View view, int position, final DownloadManagerDownloadingCM model) {
                DialogUtils.showAlertTwoButtonDialog(getContext(), "删除任务", "确定删除该任务吗？", new AlertDialog.OnClickAlertOkListener() {
                    @Override public void onClickAlertOk(android.app.AlertDialog dialog) {
                        DownloadManager.getInstance().destroy(model.downloadTask);
                    }
                });
            }
        });
        RecyclerViewUtils.linearLayout(downloadedTaskRV).vertical();
        downloadedTaskRV.setAdapter(downloadedCMAdapter);
        downloadedCMAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener<DownloadManagerDownloadedCM>() {
            @Override public void onItemClick(ViewGroup parent, View view, int position, final DownloadManagerDownloadedCM model) {
                DialogUtils.showAlertTwoButtonDialog(getContext(), "删除任务", "确定删除该任务吗？", new AlertDialog.OnClickAlertOkListener() {
                    @Override public void onClickAlertOk(android.app.AlertDialog dialog) {
                        DownloadManager.getInstance().destroy(model.downloadTask);
                    }
                });
            }
        });
    }

    @Override public void bind(IViewModel model) {
        vm = (DownloadManagerVM) model;
        downloadingAdapter.setData(vm.downloadingCMList);
        downloadingAdapter.notifyDataSetChanged();
        downloadedCMAdapter.setData(vm.downloadedCMList);
        downloadedCMAdapter.notifyDataSetChanged();
    }

    private void updateDownloadProgressUI() {
        long currentLength = 0, totalLength = 0;
        List<DownloadTaskWrapper> wrapperList = DownloadManager.getInstance().provideAllDownloadTaskInfo();
        for (DownloadTaskWrapper wrapper : wrapperList) {
            currentLength += wrapper.isComplete() ? wrapper.getTotalLength() : wrapper.getCurrentOffset();
            totalLength += wrapper.getTotalLength();
        }
        downloadFFL.setVisibility(currentLength == totalLength ? View.GONE : View.VISIBLE);
        downloadCPV.setProgress(totalLength == 0 ? 0 : (int) (currentLength * 100 / totalLength));
    }

    /**
     * click: downloadApkBT, downloadImageBT
     * 开启一个下载任务
     */
    @ViewClick(ids = {R.id.downloadApkBT, R.id.downloadImageBT})
    private void download(View v) {
        AppUtils.requestPermission(new AppUtils.OnRequestPermissionListener() {
            @Override public void onSuccess() {
                DownloadMovie downloadMovie = null;
                switch (v.getId()) {
                    case R.id.downloadApkBT:
                        downloadMovie = new DownloadMovie("下载文件" + new Random().nextInt(100), "http://file.ws.126.net/3g/client/netease_newsreader_android.apk");
                        break;
                    case R.id.downloadImageBT:
                        downloadMovie = new DownloadMovie("下载文件" + new Random().nextInt(100), "http://www.51pptmoban.com/d/file/2015/05/10/41b15ba17c8a4298d0acd27b649e869b.jpg");
                        break;
                }
                DownloadManager.getInstance().newDownloadingTask(downloadMovie);
            }
            @Override public void onFail() {
                UiUtils.show("您未授予文件写入权限");
            }
        }, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    /**
     * 当下载任务发生变化时更新界面
     */
    @Subscribe public void OnDownloadManagerStateChangeEvent(DownloadManager.OnDownloadManagerStateChangeEvent event) {
        vm.reloadDownloadingTaskInfo();
        downloadingAdapter.notifyDataSetChanged();
        vm.reloadDownloadedTaskInfo();
        downloadedCMAdapter.notifyDataSetChanged();
        updateDownloadProgressUI();
    }

    /**
     * 当进度变化时更新任务进度
     */
    @Subscribe public void OnDownloadManagerStateUpdateEvent(DownloadManager.OnDownloadManagerStateUpdateEvent event) {
        downloadingAdapter.notifyDataSetChanged();
        updateDownloadProgressUI();
    }
}