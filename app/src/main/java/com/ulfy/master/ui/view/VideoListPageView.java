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
import com.ulfy.master.application.cm.VideoListCM;
import com.ulfy.master.application.vm.VideoListPageVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_video_list_page)
public class VideoListPageView extends BaseView {
    @ViewById(id = R.id.smartSRL) private SmartRefreshLayout smartSRL;
    @ViewById(id = R.id.videoRV) private RecyclerView videoRV;
    private RecyclerAdapter<VideoListCM> videoAdapter = new RecyclerAdapter<>();
    private SmartRefresher refresher;
    private RecyclerViewPageLoader loader;
    private VideoListPageVM vm;

    public VideoListPageView(Context context) {
        super(context);
        init(context, null);
    }

    public VideoListPageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        RecyclerViewUtils.linearLayout(videoRV).vertical().dividerDp(Color.parseColor("#f0f0f0"), 4, 0, 1);
        videoRV.setAdapter(videoAdapter);
        videoAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener<VideoListCM>() {
            @Override public void onItemClick(ViewGroup parent, View view, int position, VideoListCM model) {

            }
        });
        refresher = new SmartRefresher(smartSRL, new SmartRefresher.OnRefreshSuccessListener() {
            @Override public void onRefreshSuccess(SmartRefresher smartRefresher) {
                bind(vm);
            }
        });
        loader = new RecyclerViewPageLoader(videoRV, videoAdapter, null);
    }

    @Override public void bind(IViewModel model) {
        vm = (VideoListPageVM) model;
        refresher.updateExecuteBody(vm.videoTaskInfo, vm.loadDataPerPageOnExe());
        loader.updateExecuteBody(vm.videoTaskInfo, vm.loadDataPerPageOnExe());
        videoAdapter.setData(vm.videoCMList);
        videoAdapter.notifyDataSetChanged();
        loader.notifyDataSetChanged();
    }
}