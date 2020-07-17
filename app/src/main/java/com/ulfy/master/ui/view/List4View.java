package com.ulfy.master.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.ulfy.android.adapter.RecyclerAdapter;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.utils.RecyclerViewUtils;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.List4CM;
import com.ulfy.master.application.vm.List4VM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_list4)
public class List4View extends BaseView {
    @ViewById(id = R.id.avatarRV) private RecyclerView avatarRV;
    private RecyclerAdapter<List4CM> avatarAdapter = new RecyclerAdapter<>();
    private List4VM vm;

    public List4View(Context context) {
        super(context);
        init(context, null);
    }

    public List4View(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        RecyclerViewUtils.linearLayout(avatarRV).horizontal().dividerDp(Color.TRANSPARENT, -20, 0, 0);
        avatarRV.setAdapter(avatarAdapter);
        avatarAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener<List4CM>() {
            @Override public void onItemClick(ViewGroup parent, View view, int position, List4CM model) {
                UiUtils.show("点击了" + position);
            }
        });
    }

    @Override public void bind(IViewModel model) {
        vm = (List4VM) model;
        avatarAdapter.setData(vm.avatarCMList);
        avatarAdapter.notifyDataSetChanged();
    }
}