package com.ulfy.master.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ulfy.android.adapter.RecyclerAdapter;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task_transponder.RecyclerViewPageLoader;
import com.ulfy.android.task_transponder_smart.SmartRefresher;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.utils.RecyclerViewUtils;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.StaggeredAutoFullCM;
import com.ulfy.master.application.vm.StaggeredAutoFullVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_staggered)
public class StaggeredAutoFullView extends BaseView {
    @ViewById(id = R.id.smartSRL) private SmartRefreshLayout smartSRL;
    @ViewById(id = R.id.staggeredRV) private RecyclerView staggeredRV;
    private RecyclerAdapter<StaggeredAutoFullCM> staggeredAdapter = new RecyclerAdapter<>();
    private SmartRefresher staggeredRefresher;
    private RecyclerViewPageLoader staggeredLoader;
    private StaggeredAutoFullVM vm;

    public StaggeredAutoFullView(Context context) {
        super(context);
        init(context, null);
    }

    public StaggeredAutoFullView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        RecyclerViewUtils.staggeredLayout(staggeredRV).vertical(3).dividerDp(Color.TRANSPARENT, 4, 4, 0, 0);
        staggeredAdapter.setComparator(new RecyclerAdapter.Comparator<StaggeredAutoFullCM>() {
            @Override public boolean areItemsTheSame(StaggeredAutoFullCM oldItem, StaggeredAutoFullCM newItem) {
                return oldItem.index == newItem.index;
            }
            @Override public boolean areContentsTheSame(StaggeredAutoFullCM oldItem, StaggeredAutoFullCM newItem) {
                return oldItem.contentSame(newItem);
            }
        });
        staggeredAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener<StaggeredAutoFullCM>() {
            @Override public void onItemClick(ViewGroup parent, View view, int position, StaggeredAutoFullCM model) {
                UiUtils.show("点击了：" + position);
            }
        });
        staggeredRV.setAdapter(staggeredAdapter);
        staggeredRefresher = new SmartRefresher(smartSRL, new SmartRefresher.OnRefreshSuccessListener() {
            @Override public void onRefreshSuccess(SmartRefresher smartRefresher) {
                bind(vm);
            }
        });
        staggeredLoader = new RecyclerViewPageLoader(staggeredRV, staggeredAdapter, null);
    }

    @Override public void bind(IViewModel model) {
        vm = (StaggeredAutoFullVM) model;
        staggeredRefresher.updateExecuteBody(vm.staggeredTaskInfo, vm.loadDataPerPageOnExe());
        staggeredLoader.updateExecuteBody(vm.staggeredTaskInfo, vm.loadDataPerPageOnExe());
        staggeredAdapter.setData(vm.staggeredCMList);
        RecyclerAdapter.notifyDataSetChanged(staggeredAdapter);
        staggeredLoader.notifyDataSetChanged();
    }
}