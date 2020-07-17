package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_transponder.ContentDataRefresher;
import com.ulfy.android.task_transponder.OnReloadListener;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.ContentDataRefreshVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_content_data_refresh)
public class ContentDataRefreshView extends BaseView {
    @ViewById(id = R.id.refreshBT) private Button refreshBT;
    @ViewById(id = R.id.containerFL) private FrameLayout containerFL;
    @ViewById(id = R.id.insideTV) private TextView insideTV;
    private ContentDataRefreshVM vm;

    public ContentDataRefreshView(Context context) {
        super(context);
        init(context, null);
    }

    public ContentDataRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (ContentDataRefreshVM) model;
    }
    
    /**
     * click: refreshBT
     * 点击刷新
     */
    @ViewClick(ids = R.id.refreshBT) private void refreshBT(View v) {
        refreshContent();
    }

    private void refreshContent() {
        TaskUtils.loadData(getContext(), vm.refreshDataOnExe(), new ContentDataRefresher(containerFL, insideTV) {
            @Override public void onSuccess(Object tipData) {
                super.onSuccess(tipData);
                insideTV.setText(vm.content);
            }}.setOnReloadListener(new OnReloadListener() {
                    @Override public void onReload() {
                        refreshContent();
                    }
                }));
    }
}