package com.ulfy.master.ui.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ulfy.android.download_manager.DownloadManager;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.DownloadManagerDownloadingCM;
import com.ulfy.master.ui.base.BaseCell;

@Layout(id = R.layout.cell_download_manager_downloading)
public class DownloadManagerDownloadingCell extends BaseCell {
    @ViewById(id = R.id.nameTV) private TextView nameTV;
    @ViewById(id = R.id.statusTV) private TextView statusTV;
    @ViewById(id = R.id.sizeTV) private TextView sizeTV;
    @ViewById(id = R.id.speedTV) private TextView speedTV;
    @ViewById(id = R.id.progressPB) private ProgressBar progressPB;
    private DownloadManagerDownloadingCM cm;

    public DownloadManagerDownloadingCell(Context context) {
        super(context);
        init(context, null);
    }

    public DownloadManagerDownloadingCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        cm = (DownloadManagerDownloadingCM) model;
        nameTV.setText(cm.downloadTask.getDownloadTaskInfo().name);
        statusTV.setText(cm.downloadTask.getStatusString());
        if (cm.downloadTask.getTotalLength() == 0) {
            sizeTV.setText("0MB/0MB");
            speedTV.setText("0KB");
            progressPB.setProgress(0);
        } else {
            sizeTV.setText(String.format("%s/%s", DownloadManager.convertFileSizeToHumanReadableString(cm.downloadTask.getCurrentOffset()), DownloadManager.convertFileSizeToHumanReadableString(cm.downloadTask.getTotalLength())));
            speedTV.setText(DownloadManager.convertFileSizeToHumanReadableString(cm.downloadTask.getSpeed()));
            progressPB.setMax(100);
            progressPB.setProgress((int) (cm.downloadTask.getCurrentOffset() * 100f / cm.downloadTask.getTotalLength()));
        }
    }
    
    /**
     * click: statusTV
     * 开始暂停
     */
    @ViewClick(ids = R.id.statusTV) private void statusTV(View v) {
        if (cm.downloadTask.isStart()) {
            DownloadManager.getInstance().stop(cm.downloadTask);
        } else {
            DownloadManager.getInstance().start(cm.downloadTask);
        }
    }
}