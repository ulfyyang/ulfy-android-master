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
import com.ulfy.master.application.cm.List1CM;
import com.ulfy.master.application.vm.List1VM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_list1)
public class List1View extends BaseView {
    @ViewById(id = R.id.contentSRL) private SmartRefreshLayout contentSRL;
    @ViewById(id = R.id.contentRV) private RecyclerView contentRV;
    private RecyclerAdapter<List1CM> contentAdapter = new RecyclerAdapter<>();
    private SmartRefresher contentRefresher;
    private RecyclerViewPageLoader contentLoader;
    private List1VM vm;

    public List1View(Context context) {
        super(context);
        init(context, null);
    }

    public List1View(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        // RecyclerAdapter 可以设置 header 和 footer，如果设置了 header、footer 则 dividerXX 方法中要填写对应的 header、footer 数量
        // RecyclerViewPageLoader 上拉加载分页内部通过 RecyclerAdapter 设置了 footer，因此需要配置 footer 为 1
        RecyclerViewUtils.linearLayout(contentRV).vertical().dividerDp(getResources().getColor(R.color.line), 0.5f, 0, 1);
        contentRV.setAdapter(contentAdapter);
        contentAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener<List1CM>() {
            @Override public void onItemClick(ViewGroup parent, View view, int position, List1CM model) {
                UiUtils.show(String.format("点击了：%d", position));
            }
        });
        contentAdapter.setOnItemLongClickListener(new RecyclerAdapter.OnItemLongClickListener<List1CM>() {
            @Override public void onItemLongClick(ViewGroup parent, View view, int position, List1CM model) {
                UiUtils.show(String.format("长按了：%d", position));
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
        vm = (List1VM) model;
        // 配置下拉刷新、上拉加载内部的执行任务
        contentRefresher.updateExecuteBody(vm.contentTaskInfo, vm.loadContentDataPerPageOnExe());
        contentLoader.updateExecuteBody(vm.contentTaskInfo, vm.loadContentDataPerPageOnExe());

        // 设置数据源并更新列表页
        contentAdapter.setData(vm.contentCMList);
        contentAdapter.notifyDataSetChanged();
        contentLoader.notifyDataSetChanged();           // 该方法用于通知上拉加载器更新页面，这样在首页为空的情况下有更好的页面显示
    }
}