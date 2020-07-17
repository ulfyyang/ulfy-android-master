package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.TaskTransponderVM;
import com.ulfy.master.ui.activity.ContentDataInsideLoaderActivity;
import com.ulfy.master.ui.activity.ContentDataLoaderActivity;
import com.ulfy.master.ui.activity.ContentDataRefreshActivity;
import com.ulfy.master.ui.activity.DialogProcesserActivity;
import com.ulfy.master.ui.activity.ListViewLoaderActivity;
import com.ulfy.master.ui.activity.RecyclerViewLoaderActivity;
import com.ulfy.master.ui.activity.SilentProcesserActivity;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_task_transponder)
public class TaskTransponderView extends BaseView {
    @ViewById(id = R.id.contentDataInsideLoaderTV) private TextView contentDataInsideLoaderTV;
    @ViewById(id = R.id.contentDataLoaderTV) private TextView contentDataLoaderTV;
    @ViewById(id = R.id.contentDataRefreshTV) private TextView contentDataRefreshTV;
    @ViewById(id = R.id.dialogProcesserTV) private TextView dialogProcesserTV;
    @ViewById(id = R.id.silentProcesserTV) private TextView silentProcesserTV;
    private TaskTransponderVM vm;

    public TaskTransponderView(Context context) {
        super(context);
        init(context, null);
    }

    public TaskTransponderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (TaskTransponderVM) model;
    }

    /**
     * click: contentDataInsideLoaderTV
     * 内部容器加载
     */
    @ViewClick(ids = {R.id.contentDataInsideLoaderTV}) private void contentDataInsideLoaderTV(View v) {
        ContentDataInsideLoaderActivity.startActivity();
    }

    /**
     * click: contentDataLoaderTV
     * 内容加载
     */
    @ViewClick(ids = {R.id.contentDataLoaderTV}) private void contentDataLoaderTV(View v) {
        ContentDataLoaderActivity.startActivity();
    }

    /**
     * click: contentRefreshTV
     * 内部容器刷新
     */
    @ViewClick(ids = {R.id.contentDataRefreshTV}) private void contentDataRefreshTV(View v) {
        ContentDataRefreshActivity.startActivity();
    }

    /**
     * click: dialogProcesserTV
     * 弹出框处理
     */
    @ViewClick(ids = {R.id.dialogProcesserTV}) private void dialogProcesserTV(View v) {
        DialogProcesserActivity.startActivity();
    }

    /**
     * click: silentProcesserTV
     * 静默处理
     */
    @ViewClick(ids = {R.id.silentProcesserTV}) private void silentProcesserTV(View v) {
        SilentProcesserActivity.startActivity();
    }

    /**
     * click: listViewLoaderTV
     * ListView上拉加载
     */
    @ViewClick(ids = R.id.listViewLoaderTV) private void listViewLoaderTV(View v) {
        ListViewLoaderActivity.startActivity();
    }
    
    /**
     * click: recyclerViewLoaderTV
     * RecyclerView上拉加载
     */
    @ViewClick(ids = R.id.recyclerViewLoaderTV) private void recyclerViewLoaderTV(View v) {
        RecyclerViewLoaderActivity.startActivity();
    }
}