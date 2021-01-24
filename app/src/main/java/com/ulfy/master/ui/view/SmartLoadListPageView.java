package com.ulfy.master.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ulfy.android.adapter.RecyclerAdapter;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task_transponder_smart.SmartRefresher;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.utils.RecyclerViewUtils;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.SmartLoadListPageCM;
import com.ulfy.master.application.vm.SmartLoadListPageVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_smart_load_list_page)
public class SmartLoadListPageView extends BaseView {
    @ViewById(id = R.id.smartSRL) private SmartRefreshLayout smartSRL;
    @ViewById(id = R.id.contentRV) private RecyclerView contentRV;
    // 声明一个可以处理ulfy的mvvm规范的RecyclerView适配器
    private RecyclerAdapter<SmartLoadListPageCM> adapter = new RecyclerAdapter<>();
    // 配置下拉刷新器
    private SmartRefresher smartRefresher;
    private SmartLoadListPageVM vm;

    public SmartLoadListPageView(Context context) {
        super(context);
        init(context, null);
    }

    public SmartLoadListPageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        // 配置显示样式
        RecyclerViewUtils.linearLayout(contentRV).vertical().dividerDp(Color.GRAY, 1, 0, 0);
        contentRV.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener<SmartLoadListPageCM>() {
            @Override public void onItemClick(ViewGroup parent, View view, int position, SmartLoadListPageCM model) {
                UiUtils.show(String.format("点击了：%s", model.content));
            }
        });
        // 配置下拉刷新器
        smartRefresher = new SmartRefresher(smartSRL, new SmartRefresher.OnRefreshSuccessListener() {
            @Override public void onRefreshSuccess(SmartRefresher smartRefresher) {
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override public void bind(IViewModel model) {
        vm = (SmartLoadListPageVM) model;
        // 更新下拉刷新器的执行体，这里指定的是具体的加载过程
        smartRefresher.updateExecuteBody(vm.dataTaskInfo, vm.loadDataPerPageOnExe());
        // 设置适配器的数据并更新数据
        adapter.setData(vm.dataCMList);
        adapter.notifyDataSetChanged();
    }
}