package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ulfy.android.adapter.ListAdapter;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task_transponder.ListViewPageLoader;
import com.ulfy.android.task_transponder_smart.SmartRefresher;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.List2CM;
import com.ulfy.master.application.vm.List2VM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_list2)
public class List2View extends BaseView {
    @ViewById(id = R.id.contentSRL) private SmartRefreshLayout contentSRL;
    @ViewById(id = R.id.contentLV) private ListView contentLV;
    private ListAdapter<List2CM> contentAdapter = new ListAdapter<>();
    private SmartRefresher contentRefresher;
    private ListViewPageLoader contentLoader;
    private List2VM vm;

    public List2View(Context context) {
        super(context);
        init(context, null);
    }

    public List2View(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        contentLV.setAdapter(contentAdapter);
        contentAdapter.setOnItemClickListener(contentLV, new ListAdapter.OnItemClickListener<List2CM>() {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, List2CM model) {
                UiUtils.show(String.format("点击了：%d", position));
            }
        });
        contentRefresher = new SmartRefresher(contentSRL, new SmartRefresher.OnRefreshSuccessListener() {
            @Override public void onRefreshSuccess(SmartRefresher smartRefresher) {
                bind(vm);
            }
        });
        contentLoader = new ListViewPageLoader(contentLV, null);
    }

    @Override public void bind(IViewModel model) {
        vm = (List2VM) model;

        contentRefresher.updateExecuteBody(vm.contentTaskInfo, vm.loadContentDataPerPageOnExe());
        contentLoader.updateExecuteBody(vm.contentTaskInfo, vm.loadContentDataPerPageOnExe());

        contentAdapter.setData(vm.contentCMList);
        contentAdapter.notifyDataSetChanged();
    }
}