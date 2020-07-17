package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.ulfy.android.adapter.ListAdapter;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.views.FlowLayout;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.FlowLayoutCM;
import com.ulfy.master.application.vm.FlowLayoutVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_flow_layout)
public class FlowLayoutView extends BaseView {
    @ViewById(id = R.id.contentFL) private FlowLayout contentFL;
    private ListAdapter<FlowLayoutCM> adapter = new ListAdapter<>();
    private FlowLayoutVM vm;

    public FlowLayoutView(Context context) {
        super(context);
        init(context, null);
    }

    public FlowLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        contentFL.setAdapter(adapter);
        contentFL.setOnItemClickListener(new FlowLayout.OnItemClickListener() {
            @Override public void onItemClick(FlowLayout parent, View view, int position, Object item, long itemId) {
                UiUtils.show(String.format("点击了：%s", vm.dataCMList.get(position).content));
            }
        });
    }

    @Override public void bind(IViewModel model) {
        vm = (FlowLayoutVM) model;
        adapter.setData(vm.dataCMList);
        adapter.notifyDataSetChanged();
    }
}