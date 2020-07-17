package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ulfy.android.adapter.ListAdapter;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task_transponder.ListViewPageLoader;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.ListViewLoaderCM;
import com.ulfy.master.application.vm.ListViewLoaderVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_list_view_loader)
public class ListViewLoaderView extends BaseView {
    @ViewById(id = R.id.contentLV) private ListView contentLV;
    // 声明一个可以处理ulfy的mvvm规范的ListView适配器
    private ListAdapter<ListViewLoaderCM> adapter = new ListAdapter<>();
    // 声明上拉加载响应器
    private ListViewPageLoader listViewPageLoader;
    private ListViewLoaderVM vm;

    public ListViewLoaderView(Context context) {
        super(context);
        init(context, null);
    }

    public ListViewLoaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        // 配置适配器和单击事件，通过适配器设置单击事件的好处是内部处理了因为header和footer导致的坐标不正确问题，且毁掉类型直接支持model
        contentLV.setAdapter(adapter);
        adapter.setOnItemClickListener(contentLV, new ListAdapter.OnItemClickListener<ListViewLoaderCM>() {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, ListViewLoaderCM model) {
                UiUtils.show(String.format("点击了：%s", model.content));
            }
        });
        // 配置上拉加载器，如果加载成功后没有多余的操作则无需传入回调，框架会处理好数据的刷新问题
        listViewPageLoader = new ListViewPageLoader(contentLV, null);
//        listViewPageLoader = new ListViewPageLoader(contentLV, new ListViewPageLoader.OnLoadSuccessListener() {
//            @Override public void onLoadSuccess(ListViewPageLoader listViewPageLoader) {
//
//            }
//        });
    }

    @Override public void bind(IViewModel model) {
        vm = (ListViewLoaderVM) model;
        // 更新上拉加载器的执行体，这里指定的是具体的加载过程
        listViewPageLoader.updateExecuteBody(vm.dataTaskInfo, vm.loadDataPerPageOnExe());
        // 设置适配器的数据并更新数据
        adapter.setData(vm.dataCMList);
        adapter.notifyDataSetChanged();
    }
}