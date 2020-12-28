package com.ulfy.master.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ulfy.android.adapter.RecyclerAdapter;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task_transponder.RecyclerViewPageLoader;
import com.ulfy.android.task_transponder_smart.SmartRefresher;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.utils.RecyclerViewUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.LittleVideoCM;
import com.ulfy.master.application.vm.LittleVideoPageVM;
import com.ulfy.master.ui.activity.DouyinActivity;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_little_video_page)
public class LittleVideoPageView extends BaseView {
    @ViewById(id = R.id.smartSRL) private SmartRefreshLayout smartSRL;
    @ViewById(id = R.id.videoRV) private RecyclerView videoRV;
    private RecyclerAdapter<LittleVideoCM> adapter = new RecyclerAdapter<>();
    private SmartRefresher refresher;
    private RecyclerViewPageLoader loader;
    private LittleVideoPageVM vm;

    public LittleVideoPageView(Context context) {
        super(context);
        init(context, null);
    }

    public LittleVideoPageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        RecyclerViewUtils.gridLayout(videoRV).vertical(2).dividerDp(Color.TRANSPARENT, 2, 2, 0, 1);
        videoRV.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener<LittleVideoCM>() {
            @Override public void onItemClick(ViewGroup parent, View view, int position, LittleVideoCM model) {
                DouyinActivity.startActivity(vm.videoCMList, vm.videoTaskInfo, position);
            }
        });
        refresher = new SmartRefresher(smartSRL, new SmartRefresher.OnRefreshSuccessListener() {
            @Override public void onRefreshSuccess(SmartRefresher smartRefresher) {
                bind(vm);
            }
        });
        loader = new RecyclerViewPageLoader(videoRV, adapter, null);
    }

    @Override public void bind(IViewModel model) {
        vm = (LittleVideoPageVM) model;
        refresher.updateExecuteBody(vm.videoTaskInfo, vm.loadDataPerPageOnExe());
        loader.updateExecuteBody(vm.videoTaskInfo, vm.loadDataPerPageOnExe());
        adapter.setData(vm.videoCMList);
        adapter.notifyDataSetChanged();
        loader.notifyDataSetChanged();
    }
}