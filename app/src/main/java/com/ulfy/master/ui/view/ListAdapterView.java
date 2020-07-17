package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ulfy.android.adapter.ListAdapter;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.ListAdapterCM;
import com.ulfy.master.application.vm.ListAdapterVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_list_adapter)
public class ListAdapterView extends BaseView {
    @ViewById(id = R.id.dataLV) private ListView dataLV;
    private ListAdapter<ListAdapterCM> adapter = new ListAdapter<>();
    private ListAdapterVM vm;

    public ListAdapterView(Context context) {
        super(context);
        init(context, null);
    }

    public ListAdapterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        dataLV.setAdapter(adapter);
        adapter.setOnItemClickListener(dataLV, new ListAdapter.OnItemClickListener<ListAdapterCM>() {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, ListAdapterCM model) {
                UiUtils.show(model.content);
            }
        });
    }

    @Override public void bind(IViewModel model) {
        vm = (ListAdapterVM) model;
        adapter.setData(vm.dataCMList);
        adapter.notifyDataSetChanged();
    }
}