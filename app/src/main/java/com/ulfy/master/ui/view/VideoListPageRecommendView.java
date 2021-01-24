package com.ulfy.master.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ulfy.android.adapter.RecyclerAdapter;
import com.ulfy.android.image.ImageUtils;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task_transponder.RecyclerViewPageLoader;
import com.ulfy.android.task_transponder_smart.SmartRefresher;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.utils.RecyclerViewUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.VideoListCM;
import com.ulfy.master.application.vm.VideoListPageRecommendVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_video_list_page_recommend)
public class VideoListPageRecommendView extends BaseView {
    @ViewById(id = R.id.headerLL) private LinearLayout headerLL;
    @ViewById(id = R.id.advertisementIV) private ImageView advertisementIV;
    @ViewById(id = R.id.collectionLL) private LinearLayout collectionLL;
    @ViewById(id = R.id.collectionTV) private TextView collectionTV;
    @ViewById(id = R.id.fanGroupLL) private LinearLayout fanGroupLL;
    @ViewById(id = R.id.fanGroupTV) private TextView fanGroupTV;
    @ViewById(id = R.id.smartSRL) private SmartRefreshLayout smartSRL;
    @ViewById(id = R.id.videoRV) private RecyclerView videoRV;
    private RecyclerAdapter<VideoListCM> videoAdapter = new RecyclerAdapter<>();
    private SmartRefresher refresher;
    private RecyclerViewPageLoader loader;
    private VideoListPageRecommendVM vm;

    public VideoListPageRecommendView(Context context) {
        super(context);
        init(context, null);
    }

    public VideoListPageRecommendView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        RecyclerViewUtils.linearLayout(videoRV).vertical().dividerDp(Color.parseColor("#f0f0f0"), 4, 0, 1);
        videoAdapter.setHeaderView(headerLL);
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
        vm = (VideoListPageRecommendVM) model;
        refresher.updateExecuteBody(vm.videoTaskInfo, vm.loadDataPerPageOnExe());
        loader.updateExecuteBody(vm.videoTaskInfo, vm.loadDataPerPageOnExe());
        ImageUtils.loadImage(vm.advertisementUrl, R.drawable.drawable_loading, R.drawable.drawable_loading_false, advertisementIV);
        videoAdapter.setData(vm.videoCMList);
        videoAdapter.notifyDataSetChanged();
        loader.notifyDataSetChanged();
    }

    public void autoRefresh() {
        refresher.autoRefresh();
    }
}