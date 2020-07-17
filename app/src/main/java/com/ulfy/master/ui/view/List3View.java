package com.ulfy.master.ui.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ulfy.android.adapter.RecyclerAdapter;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task_transponder.RecyclerViewPageLoader;
import com.ulfy.android.task_transponder_smart.SmartRefresher;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.utils.RecyclerViewUtils;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.List3CM;
import com.ulfy.master.application.vm.List3VM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_list3)
public class List3View extends BaseView {
    @ViewById(id = R.id.contentSRL) private SmartRefreshLayout contentSRL;
    @ViewById(id = R.id.contentRV) private RecyclerView contentRV;
    private RecyclerAdapter<List3CM> contentAdapter = new RecyclerAdapter<>();
    private SmartRefresher contentRefresher;
    private RecyclerViewPageLoader contentLoader;
    private List3VM vm;

    public List3View(Context context) {
        super(context);
        init(context, null);
    }

    public List3View(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        RecyclerViewUtils.linearLayout(contentRV).vertical().dividerDp(getResources().getColor(R.color.line), 0.5f, 0, 1);
        contentRV.setAdapter(contentAdapter);
        contentAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener<List3CM>() {
            @Override public void onItemClick(ViewGroup parent, View view, int position, List3CM model) {
                UiUtils.show(String.format("点击了：%d", position));
            }
        });
        contentRefresher = new SmartRefresher(contentSRL, new SmartRefresher.OnRefreshSuccessListener() {
            @Override public void onRefreshSuccess(SmartRefresher smartRefresher) {
                bind(vm);
            }
        });
        contentLoader = new RecyclerViewPageLoader(contentRV, contentAdapter, null);
    }

    @Override public void bind(IViewModel model) {
        vm = (List3VM) model;// 配置下拉刷新、上拉加载内部的执行任务
        contentRefresher.updateExecuteBody(vm.contentTaskInfo, vm.loadContentDataPerPageOnExe());
        contentLoader.updateExecuteBody(vm.contentTaskInfo, vm.loadContentDataPerPageOnExe());

        // 设置数据源并更新列表页
        contentAdapter.setData(vm.contentCMList);
        contentAdapter.notifyDataSetChanged();
        contentLoader.notifyDataSetChanged();           // 该方法用于通知上拉加载器更新页面，这样在首页为空的情况下有更好的页面显示
    }
}