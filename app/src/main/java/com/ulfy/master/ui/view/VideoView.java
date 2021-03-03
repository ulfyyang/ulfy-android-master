package com.ulfy.master.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ulfy.android.adapter.RecyclerAdapter;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task_transponder.RecyclerViewPageLoader;
import com.ulfy.android.task_transponder_smart.SmartRefresher;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.utils.RecyclerViewUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.VideoCM;
import com.ulfy.master.application.vm.VideoVM;
import com.ulfy.master.ui.base.BaseView;
import com.ulfy.master.ui.cell.VideoCell;

@Layout(id = R.layout.view_video)
public class VideoView extends BaseView {
    @ViewById(id = R.id.smartSRL) private SmartRefreshLayout smartSRL;
    @ViewById(id = R.id.videoRV) private RecyclerView videoRV;
    private RecyclerAdapter<VideoCM> adapter = new RecyclerAdapter<>();
    private SmartRefresher refresher;
    private RecyclerViewPageLoader loader;
    private VideoVM vm;

    public VideoView(Context context) {
        super(context);
        init(context, null);
    }

    public VideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        videoRV.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override public void onChildViewAttachedToWindow(View view) { }
            @Override public void onChildViewDetachedFromWindow(View view) {
                if (view instanceof VideoCell) {
                    ((VideoCell) view).releaseVideoForTinyScreen();
                }
            }
        });
        RecyclerViewUtils.linearLayout(videoRV).vertical().dividerDp(Color.parseColor("#f0f0f0"), 4, 0, 1);
        videoRV.setAdapter(adapter);
        refresher = new SmartRefresher(smartSRL, new SmartRefresher.OnRefreshSuccessListener() {
            @Override public void onRefreshSuccess(SmartRefresher smartRefresher) {
                bind(vm);
            }
        });
        loader = new RecyclerViewPageLoader(videoRV, adapter, null);
    }

    @Override public void bind(IViewModel model) {
        vm = (VideoVM) model;
        refresher.updateExecuteBody(vm.videoTaskInfo, vm.loadDataPerPageOnExe());
        loader.updateExecuteBody(vm.videoTaskInfo, vm.loadDataPerPageOnExe());
        adapter.setData(vm.videoCMList);
        adapter.notifyDataSetChanged();
        loader.notifyDataSetChanged();
    }
}