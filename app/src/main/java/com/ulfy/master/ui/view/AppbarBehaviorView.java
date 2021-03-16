package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ulfy.android.adapter.RecyclerAdapter;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task_transponder_smart.SmartRefresher;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.utils.RecyclerViewUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.AppbarBehaviorCM;
import com.ulfy.master.application.vm.AppbarBehaviorVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_appbar_behavior)
public class AppbarBehaviorView extends BaseView {
    @ViewById(id = R.id.increaseBT) private Button increaseBT;
    @ViewById(id = R.id.minusBT) private Button minusBT;
    @ViewById(id = R.id.content1RV) private RecyclerView content1RV;
    @ViewById(id = R.id.smart2SRL) private SmartRefreshLayout smart2SRL;
    @ViewById(id = R.id.content2RV) private RecyclerView content2RV;
    @ViewById(id = R.id.contentRV) private RecyclerView contentRV;
    private RecyclerAdapter<AppbarBehaviorCM> rvNormalAdapter = new RecyclerAdapter<>();
    private RecyclerAdapter<AppbarBehaviorCM> rvRefreshAdapter = new RecyclerAdapter<>();
    private SmartRefresher rvRefreshRefresher;
    private AppbarBehaviorVM vm;

    public AppbarBehaviorView(Context context) {
        super(context);
        init(context, null);
    }

    public AppbarBehaviorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        RecyclerViewUtils.linearLayout(content1RV).vertical();
        content1RV.setAdapter(rvNormalAdapter);
        RecyclerViewUtils.linearLayout(content2RV).vertical();
        content2RV.setAdapter(rvRefreshAdapter);
        rvRefreshRefresher = new SmartRefresher(smart2SRL, new SmartRefresher.OnRefreshSuccessListener() {
            @Override public void onRefreshSuccess(SmartRefresher smartRefresher) {
                rvRefreshAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override public void bind(IViewModel model) {
        vm = (AppbarBehaviorVM) model;
        rvRefreshRefresher.updateExecuteBody(vm.refreshOnExe());
        rvNormalAdapter.setData(vm.rvNormalCMList);
        rvNormalAdapter.notifyDataSetChanged();
        rvRefreshAdapter.setData(vm.rvRefreshCMList);
        rvRefreshAdapter.notifyDataSetChanged();
    }

    /**
     *  增加一个数据、减少一个数据
     */
    @ViewClick(ids = {R.id.increaseBT, R.id.minusBT})
    private void opt(View v) {
        switch (v.getId()) {
            case R.id.increaseBT:
                vm.increase();
                break;
            case R.id.minusBT:
                vm.minus();
                break;
        }
        rvNormalAdapter.notifyDataSetChanged();
        rvRefreshAdapter.notifyDataSetChanged();
    }
}