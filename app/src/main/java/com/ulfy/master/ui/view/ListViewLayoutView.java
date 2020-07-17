package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.ulfy.android.adapter.ListAdapter;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.views.ListViewLayout;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.ListViewLayoutCM;
import com.ulfy.master.application.vm.ListViewLayoutVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_list_view_layout)
public class ListViewLayoutView extends BaseView {
    @ViewById(id = R.id.dataLVL) private ListViewLayout dataLVL;
    @ViewById(id = R.id.dataAverageLVL) private ListViewLayout dataAverageLVL;
    @ViewById(id = R.id.dataCountLVL) private ListViewLayout dataCountLVL;
    @ViewById(id = R.id.dataScaleLVL) private ListViewLayout dataScaleLVL;
    private ListAdapter<ListViewLayoutCM> adapter = new ListAdapter<>();
    private ListAdapter<ListViewLayoutCM> moreAdapter = new ListAdapter<>();
    private ListViewLayoutVM vm;

    public ListViewLayoutView(Context context) {
        super(context);
        init(context, null);
    }

    public ListViewLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        dataLVL.setAdapter(adapter);
        dataLVL.setOnItemClickListener(new ListViewLayout.OnItemClickListener() {
            @Override public void onItemClick(ListViewLayout parent, View view, int position, Object item, long itemId) {
                UiUtils.show("点击了：" + vm.dataCMList.get(position).content);
            }
        });
        dataAverageLVL.setAdapter(adapter);
        dataAverageLVL.setOnItemClickListener(new ListViewLayout.OnItemClickListener() {
            @Override public void onItemClick(ListViewLayout parent, View view, int position, Object item, long itemId) {
                UiUtils.show("点击了：" + vm.dataCMList.get(position).content);
            }
        });
        dataCountLVL.setAdapter(adapter);
        dataCountLVL.setOnItemClickListener(new ListViewLayout.OnItemClickListener() {
            @Override public void onItemClick(ListViewLayout parent, View view, int position, Object item, long itemId) {
                UiUtils.show("点击了：" + vm.dataCMList.get(position).content);
            }
        });
        dataScaleLVL.setAdapter(moreAdapter);
        dataScaleLVL.setOnItemClickListener(new ListViewLayout.OnItemClickListener() {
            @Override public void onItemClick(ListViewLayout parent, View view, int position, Object item, long itemId) {
                UiUtils.show("点击了：" + vm.dataMoreCMList.get(position).content);
            }
        });
    }

    @Override public void bind(IViewModel model) {
        vm = (ListViewLayoutVM) model;
        adapter.setData(vm.dataCMList);
        adapter.notifyDataSetChanged();
        moreAdapter.setData(vm.dataMoreCMList);
        moreAdapter.notifyDataSetChanged();
    }
}