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
import com.ulfy.master.application.cm.StaggeredRandomRatioCM;
import com.ulfy.master.application.vm.StaggeredRandomRatioVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_staggered_random_ratio)
public class StaggeredRandomRatioView extends BaseView {
    @ViewById(id = R.id.smartSRL) private SmartRefreshLayout smartSRL;
    @ViewById(id = R.id.staggeredRV) private RecyclerView staggeredRV;
    private RecyclerAdapter<StaggeredRandomRatioCM> staggeredAdapter = new RecyclerAdapter<>();
    private SmartRefresher staggeredRefresher;
    private RecyclerViewPageLoader staggeredLoader;
    private StaggeredRandomRatioVM vm;

    public StaggeredRandomRatioView(Context context) {
        super(context);
        init(context, null);
    }

    public StaggeredRandomRatioView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        RecyclerViewUtils.staggeredLayout(staggeredRV).vertical(3).dividerDp(Color.TRANSPARENT, 4, 4, 0, 0);
        staggeredAdapter.setComparator(new RecyclerAdapter.Comparator<StaggeredRandomRatioCM>() {
            @Override public boolean areItemsTheSame(StaggeredRandomRatioCM oldItem, StaggeredRandomRatioCM newItem) {
                return oldItem.index == newItem.index;
            }
            @Override public boolean areContentsTheSame(StaggeredRandomRatioCM oldItem, StaggeredRandomRatioCM newItem) {
                return oldItem.contentSame(newItem);
            }
        });
        staggeredAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener<StaggeredRandomRatioCM>() {
            @Override public void onItemClick(ViewGroup parent, View view, int position, StaggeredRandomRatioCM model) {
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
        vm = (StaggeredRandomRatioVM) model;
        staggeredRefresher.updateExecuteBody(vm.staggeredTaskInfo, vm.loadDataPerPageOnExe());
        staggeredLoader.updateExecuteBody(vm.staggeredTaskInfo, vm.loadDataPerPageOnExe());
        staggeredAdapter.setData(vm.staggeredCMList);
        RecyclerAdapter.notifyDataSetChanged(staggeredAdapter);
        staggeredLoader.notifyDataSetChanged();
    }
}