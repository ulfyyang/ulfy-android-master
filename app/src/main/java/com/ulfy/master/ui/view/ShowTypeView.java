package com.ulfy.master.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ulfy.android.adapter.RecyclerAdapter;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task_transponder.RecyclerViewPageLoader;
import com.ulfy.android.task_transponder_smart.SmartRefresher;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.utils.RecyclerViewUtils;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.ShowTypeCM;
import com.ulfy.master.application.vm.ShowTypeVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_show_type)
public class ShowTypeView extends BaseView {
    @ViewById(id = R.id.horizontalBT) private Button horizontalBT;
    @ViewById(id = R.id.verticalBT) private Button verticalBT;
    @ViewById(id = R.id.smartSRL) private SmartRefreshLayout smartSRL;
    @ViewById(id = R.id.showRV) private RecyclerView showRV;
    private RecyclerAdapter<ShowTypeCM> showAdapter = new RecyclerAdapter<>();
    private SmartRefresher showRefresher;
    private RecyclerViewPageLoader showLoader;
    private ShowTypeVM vm;

    public ShowTypeView(Context context) {
        super(context);
        init(context, null);
    }

    public ShowTypeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        RecyclerViewUtils.linearLayout(showRV).vertical().dividerDp(Color.TRANSPARENT, 10, 0, 1);
        showRV.setAdapter(showAdapter);
        showAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener<ShowTypeCM>() {
            @Override public void onItemClick(ViewGroup parent, View view, int position, ShowTypeCM model) {
                UiUtils.show("点击了：" + position);
            }
        });
        showRefresher = new SmartRefresher(smartSRL, new SmartRefresher.OnRefreshSuccessListener() {
            @Override public void onRefreshSuccess(SmartRefresher smartRefresher) {
                showAdapter.notifyDataSetChanged();
            }
        });
        showLoader = new RecyclerViewPageLoader(showRV, showAdapter, null);
    }

    @Override public void bind(IViewModel model) {
        vm = (ShowTypeVM) model;
        showRefresher.updateExecuteBody(vm.showTaskInfo, vm.loadDataPerPageOnExe());
        showLoader.updateExecuteBody(vm.showTaskInfo, vm.loadDataPerPageOnExe());
        showAdapter.setData(vm.showCMList);
        showAdapter.notifyDataSetChanged();
        showLoader.notifyDataSetChanged();
    }

    @ViewClick(ids = {R.id.horizontalBT, R.id.verticalBT})
    private void operation(View v) {
        int scrollPosition = ((LinearLayoutManager) showRV.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
        switch (v.getId()) {
            case R.id.horizontalBT:
                if (vm.changeCellDirection(false)) {
                    RecyclerViewUtils.linearLayout(showRV).vertical().dividerDp(Color.TRANSPARENT, 10, 0, 1);
                }
                break;
            case R.id.verticalBT:
                if (vm.changeCellDirection(true)) {
                    RecyclerViewUtils.gridLayout(showRV).vertical(2).dividerDp(Color.TRANSPARENT, 10, 10, 0, 1);
                }
                break;
        }
        showRV.setAdapter(showAdapter);             // 必须调用setAdapter
        showRV.scrollToPosition(scrollPosition);
    }
}