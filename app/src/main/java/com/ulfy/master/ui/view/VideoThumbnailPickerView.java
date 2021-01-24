package com.ulfy.master.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.ulfy.android.adapter.RecyclerAdapter;
import com.ulfy.android.image.ImageUtils;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_transponder.DialogProcesser;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.utils.RecyclerViewUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.VideoThumbnailCM;
import com.ulfy.master.application.vm.VideoThumbnailPickerVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_video_thumbnail_picker)
public class VideoThumbnailPickerView extends BaseView {
    @ViewById(id = R.id.coverIV) private ImageView coverIV;
    @ViewById(id = R.id.videoThumbailRV) private RecyclerView videoThumbailRV;
    private RecyclerAdapter<VideoThumbnailCM> thumbnailAdapter = new RecyclerAdapter<>();
    private VideoThumbnailPickerVM vm;

    public VideoThumbnailPickerView(Context context) {
        super(context);
        init(context, null);
    }

    public VideoThumbnailPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        RecyclerViewUtils.linearLayout(videoThumbailRV).horizontal().dividerDp(Color.TRANSPARENT, 1, 0, 0);
        videoThumbailRV.setAdapter(thumbnailAdapter);
        thumbnailAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener<VideoThumbnailCM>() {
            @Override public void onItemClick(ViewGroup parent, View view, int position, VideoThumbnailCM model) {
                pickThumbnail(position);
            }
        });
    }

    @Override public void bind(IViewModel model) {
        vm = (VideoThumbnailPickerVM) model;
        thumbnailAdapter.setData(vm.thumbnailCMList);
        thumbnailAdapter.notifyDataSetChanged();
    }

    private void pickThumbnail(int position) {
        ImageUtils.loadImage(vm.thumbnailCMList.get(position).thumbnail, coverIV);
        vm.pickedThumbnail = vm.thumbnailCMList.get(position).thumbnail;
    }

    public void handleThumbnailThenRefreshUI() {
        TaskUtils.loadData(getContext(), vm.handleThumbnailOnExe(), new DialogProcesser(getContext()) {
                    @Override public void onSuccess(Object tipData) {
                        if (vm.thumbnailCMList != null && vm.thumbnailCMList.size() > 0) {
                            thumbnailAdapter.notifyDataSetChanged();
                            pickThumbnail(0);
                        }
                    }
                }
        );
    }
}