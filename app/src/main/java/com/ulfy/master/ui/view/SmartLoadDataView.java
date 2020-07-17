package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task_transponder_smart.SmartRefresher;
import com.ulfy.android.utils.StringUtils;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.SmartLoadDataVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_smart_load_data)
public class SmartLoadDataView extends BaseView {
    @ViewById(id = R.id.smartSRL) private SmartRefreshLayout smartSRL;
    @ViewById(id = R.id.contentTV) private TextView contentTV;
    // 配置下拉刷新器
    private SmartRefresher smartRefresher;
    private SmartLoadDataVM vm;

    public SmartLoadDataView(Context context) {
        super(context);
        init(context, null);
    }

    public SmartLoadDataView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        // 配置下拉刷新器
        smartRefresher = new SmartRefresher(smartSRL, new SmartRefresher.OnRefreshSuccessListener() {
            @Override public void onRefreshSuccess(SmartRefresher smartRefresher) {
                bind(vm);
            }
        });
    }

    @Override public void bind(IViewModel model) {
        vm = (SmartLoadDataVM) model;
        // 更新下拉刷新器的执行体，这里指定的是具体的加载过程
        smartRefresher.updateExecuteBody(vm.updateContentOnExe());
        if (!StringUtils.isEmpty(vm.content)) {
            contentTV.setText(vm.content);
        }
    }
}