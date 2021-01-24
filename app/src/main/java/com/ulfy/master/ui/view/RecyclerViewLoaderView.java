package com.ulfy.master.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.ulfy.android.adapter.RecyclerAdapter;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_transponder.ContentDataRecyclerLoader;
import com.ulfy.android.task_transponder.OnReloadListener;
import com.ulfy.android.task_transponder.RecyclerViewPageLoader;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.utils.RecyclerViewUtils;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.RecyclerViewLoaderCM;
import com.ulfy.master.application.vm.RecyclerViewLoaderVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_recycler_view_loader)
public class RecyclerViewLoaderView extends BaseView {
    @ViewById(id = R.id.contentRV) private RecyclerView contentRV;
    // 声明一个可以处理ulfy的mvvm规范的RecyclerView适配器
    private RecyclerAdapter<RecyclerViewLoaderCM> adapter = new RecyclerAdapter<>();
    // 声明上拉加载响应器
    private RecyclerViewPageLoader recyclerViewPageLoader;
    private RecyclerViewLoaderVM vm;

    public RecyclerViewLoaderView(Context context) {
        super(context);
        init(context, null);
    }

    public RecyclerViewLoaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        // 配置显示样式、适配器和单击事件。由于上拉加载是作为一个数据项存在的，因此这里需要明确的告诉框架有一个footer
        RecyclerViewUtils.linearLayout(contentRV).vertical().dividerDp(Color.GRAY, 1, 0, 1);
        contentRV.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener<RecyclerViewLoaderCM>() {
            @Override public void onItemClick(ViewGroup parent, View view, int position, RecyclerViewLoaderCM model) {
                UiUtils.show(String.format("点击了：%s", model.content));
            }
        });
        // 配置上拉加载器，如果加载成功后没有多余的操作则无需传入回调，框架会处理好数据的刷新问题
        recyclerViewPageLoader = new RecyclerViewPageLoader(contentRV, adapter, null);
//        recyclerViewPageLoader = new RecyclerViewPageLoader(contentRV, adapter, new RecyclerViewPageLoader.OnLoadSuccessListener() {
//            @Override public void onLoadSuccess(RecyclerViewPageLoader recyclerViewPageLoader) {
//
//            }
//        });
    }

    @Override public void bind(IViewModel model) {
        vm = (RecyclerViewLoaderVM) model;
        // 更新上拉加载器的执行体，这里指定的是具体的加载过程
        recyclerViewPageLoader.updateExecuteBody(vm.dataTaskInfo, vm.loadDataPerPageOnExe());
        // 设置适配器的数据并更新数据
        adapter.setData(vm.dataCMList);
        adapter.notifyDataSetChanged();
        // 更新完数据后通知加载器数据变动可以更精确的显示底部页面展示
        recyclerViewPageLoader.notifyDataSetChanged();
    }

    public void loadData() {
        TaskUtils.loadData(getContext(), vm.dataTaskInfo, vm.loadDataPerPageOnExe(), new ContentDataRecyclerLoader(getContext(), adapter)
                .setOnReloadListener(new OnReloadListener() {
                    @Override public void onReload() {
                        loadData();
                    }
                }));
    }
}