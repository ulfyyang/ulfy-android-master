package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ulfy.android.adapter.RecyclerAdapter;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.utils.RecyclerViewUtils;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.RecyclerAdapterCM;
import com.ulfy.master.application.vm.RecyclerAdapterVM;
import com.ulfy.master.ui.base.BaseView;
import com.ulfy.master.ui.cell.RecyclerAdapterCell;

@Layout(id = R.layout.view_recycler_adapter)
public class RecyclerAdapterView extends BaseView {
    @ViewById(id = R.id.showHeaderBT) private Button showHeaderBT;
    @ViewById(id = R.id.hideHeaderBT) private Button hideHeaderBT;
    @ViewById(id = R.id.headerFL) private FrameLayout headerFL;
    @ViewById(id = R.id.headerTV) private TextView headerTV;
    @ViewById(id = R.id.dataRV) private RecyclerView dataRV;
    private RecyclerAdapter<RecyclerAdapterCM> adapter = new RecyclerAdapter<>();
    private RecyclerAdapterVM vm;

    public RecyclerAdapterView(Context context) {
        super(context);
        init(context, null);
    }

    public RecyclerAdapterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /*
    1. 添加头部和底部视图，内部有清理View的实现，无需考虑View是否在一个容器中
        adapter.setHeaderView();
        adapter.setFooterView()
    2. 头部和底部视图的动态隐藏
        如果直接设置header和footer的GONE属性可以使其不可见，但是仍然会占用空间。解决方法由两种：
        1）设置视图为null并更新适配器 adapter.setHeaderView(null); adapter.notifyDataSetChanged();  会造成滚动抖动且实现比较复杂
        2) 将视图包裹起来，以包裹起来的视图作为header和footer，通过操作内部视图的可见性来达到动态显示的目的  不会造成滚动抖动且实现简单
     */

    private void init(Context context, AttributeSet attrs) {
        RecyclerViewUtils.setMaxRecycledViews(getContext(), RecyclerAdapterCell.class, 10);
        RecyclerViewUtils.linearLayout(dataRV).vertical().dividerDp(getResources().getColor(R.color.line), 0.5f, 0, 0);
        dataRV.setAdapter(adapter);
        adapter.setHeaderView(headerFL);
        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener<RecyclerAdapterCM>() {
            @Override public void onItemClick(ViewGroup parent, View view, int position, RecyclerAdapterCM model) {
                UiUtils.show(model.content);
            }
        });
    }

    @Override public void bind(IViewModel model) {
        vm = (RecyclerAdapterVM) model;
        adapter.setData(vm.dataCMList);
        adapter.notifyDataSetChanged();
    }


    /**
     * click: showHeaderBT, hideHeaderBT
     * 显示和隐藏头部视图
     */
    @ViewClick(ids = {R.id.showHeaderBT, R.id.hideHeaderBT})
    private void optionHeader(View v) {
        switch (v.getId()) {
            case R.id.showHeaderBT:
                headerTV.setVisibility(View.VISIBLE);
                break;
            case R.id.hideHeaderBT:
                headerTV.setVisibility(View.GONE);
                break;
        }
    }
}