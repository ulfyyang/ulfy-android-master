package com.ulfy.master.ui.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.DownloadManagerDownloadedCM;
import com.ulfy.master.ui.base.BaseCell;

@Layout(id = R.layout.cell_download_manager_downloaded)
public class DownloadManagerDownloadedCell extends BaseCell {
    @ViewById(id = R.id.nameTV) private TextView nameTV;
    @ViewById(id = R.id.statusTV) private TextView statusTV;
    private DownloadManagerDownloadedCM cm;

    public DownloadManagerDownloadedCell(Context context) {
        super(context);
        init(context, null);
    }

    public DownloadManagerDownloadedCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        cm = (DownloadManagerDownloadedCM) model;
        nameTV.setText(cm.downloadTask.getDownloadTaskInfo().name);
    }
}