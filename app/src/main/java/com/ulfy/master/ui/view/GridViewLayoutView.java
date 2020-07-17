package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.ulfy.android.adapter.ListAdapter;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.views.GridViewLayout;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.GridViewLayoutCM;
import com.ulfy.master.application.vm.GridViewLayoutVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_grid_view_layout)
public class GridViewLayoutView extends BaseView {
    @ViewById(id = R.id.contentGVL) private GridViewLayout contentGVL;
    private ListAdapter<GridViewLayoutCM> adapter = new ListAdapter<>();
    private GridViewLayoutVM vm;

    public GridViewLayoutView(Context context) {
        super(context);
        init(context, null);
    }

    public GridViewLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        contentGVL.setAdapter(adapter);
        contentGVL.setOnItemClickListener(new GridViewLayout.OnItemClickListener() {
            @Override public void onItemClick(LinearLayout parent, View view, int position, Object item, long itemId) {
                UiUtils.show("点击了：" + vm.dataCMList.get(position).content);
            }
        });
    }

    @Override public void bind(IViewModel model) {
        vm = (GridViewLayoutVM) model;
        adapter.setData(vm.dataCMList);
        adapter.notifyDataSetChanged();
    }
}