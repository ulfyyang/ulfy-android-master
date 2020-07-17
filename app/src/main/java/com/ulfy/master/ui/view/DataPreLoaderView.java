package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_transponder.DialogProcesser;
import com.ulfy.android.task_transponder.SilentProcesser;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.DataPreLoaderVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_data_pre_loader)
public class DataPreLoaderView extends BaseView {
    @ViewById(id = R.id.preLoadBT) private Button preLoadBT;
    @ViewById(id = R.id.getPreLoadBT) private Button getPreLoadBT;
    @ViewById(id = R.id.preLoadTV) private TextView preLoadTV;
    @ViewById(id = R.id.getPreLoadTV) private TextView getPreLoadTV;
    private DataPreLoaderVM vm;

    public DataPreLoaderView(Context context) {
        super(context);
        init(context, null);
    }

    public DataPreLoaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (DataPreLoaderVM) model;
    }
    
    /**
     * click: preLoadBT
     * 加载预加载数据
     */
    @ViewClick(ids = R.id.preLoadBT) private void preLoadBT(View v) {
        TaskUtils.loadData(getContext(), vm.dataPreLoaderEntityPreLoadOnExe(), new SilentProcesser() {
                    @Override protected void onStart(Object data) {
                        preLoadTV.setText(String.format("预加载内容：%s", "加载中"));
                    }
                    @Override public void onSuccess(Object tipData) {
                        preLoadTV.setText(String.format("预加载内容：%s", "已完成"));
                    }
                }
        );
    }
    
    /**
     * click: getPreLoadBT
     * 获取预加载数据
     */
    @ViewClick(ids = R.id.getPreLoadBT) private void getPreLoadBT(View v) {
        TaskUtils.loadData(getContext(), vm.getPreLoadDataOnExe(), new DialogProcesser(getContext()) {
                    @Override public void onSuccess(Object tipData) {
                        getPreLoadTV.setText(vm.content);
                    }
                }
        );
    }
}