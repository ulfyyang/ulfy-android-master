package com.ulfy.master.ui.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ulfy.android.adapter.RecyclerAdapter;
import com.ulfy.android.bus.Subscribe;
import com.ulfy.android.image.ImageUtils;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.system.ActivityUtils;
import com.ulfy.android.system.event.OnPickMediaEvent;
import com.ulfy.android.system.event.OnReceiveDataEvent;
import com.ulfy.android.system.media_picker.MediaRepository;
import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_transponder.DialogProcesser;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.utils.RecyclerViewUtils;
import com.ulfy.android.utils.VideoUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.VideoThumbnailCM;
import com.ulfy.master.application.vm.VideoThumbnailVM;
import com.ulfy.master.ui.activity.VideoThumbnailPickerActivity;
import com.ulfy.master.ui.base.BaseView;

import org.joda.time.Period;

import java.io.File;

@Layout(id = R.layout.view_video_thumbnail)
public class VideoThumbnailView extends BaseView {
    @ViewById(id = R.id.firstThumbnailBT) private Button firstThumbnailBT;
    @ViewById(id = R.id.moreThumbnailBT) private Button moreThumbnailBT;
    @ViewById(id = R.id.manualThumbnailBT) private Button manualThumbnailBT;
    @ViewById(id = R.id.firstThumbnailIV) private ImageView firstThumbnailIV;
    @ViewById(id = R.id.moreThumbnailRV) private RecyclerView moreThumbnailRV;
    @ViewById(id = R.id.videoInfoBT) private Button videoInfoBT;
    @ViewById(id = R.id.videoInfoTV) private TextView videoInfoTV;
    private static final int REQUEST_CODE_PICK_VIDEO_FOR_ONE = 100;
    private static final int REQUEST_CODE_PICK_VIDEO_FOR_MORE = 101;
    private static final int REQUEST_CODE_PICK_VIDEO_FOR_MANUAL = 102;
    private static final int REQUEST_CODE_PICK_VIDEO_FOR_MANUAL_PICK = 103;
    private static final int REQUEST_CODE_PICK_VIDEO_FOR_INFO = 104;
    private RecyclerAdapter<VideoThumbnailCM> thumbnailAdapter = new RecyclerAdapter<>();
    private VideoThumbnailVM vm;

    public VideoThumbnailView(Context context) {
        super(context);
        init(context, null);
    }

    public VideoThumbnailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        RecyclerViewUtils.gridLayout(moreThumbnailRV).vertical(3).dividerDp(Color.TRANSPARENT, 10, 10, 0, 0);
        moreThumbnailRV.setAdapter(thumbnailAdapter);
    }

    @Override public void bind(IViewModel model) {
        vm = (VideoThumbnailVM) model;
        thumbnailAdapter.setData(vm.thumbnailCMList);
        thumbnailAdapter.notifyDataSetChanged();
    }

    @ViewClick(ids = R.id.firstThumbnailBT) private void firstThumbnailBT(View v) {
        ActivityUtils.pickMedia(REQUEST_CODE_PICK_VIDEO_FOR_ONE, MediaRepository.SEARCH_TYPE_VIDEO, 1, null);
    }

    @Subscribe private void OnPickMediaEventForOne(OnPickMediaEvent event) {
        if (event.requestCode == REQUEST_CODE_PICK_VIDEO_FOR_ONE && event.entities != null && event.entities.size() > 0) {
            firstThumbnailIV.setVisibility(View.VISIBLE);
            moreThumbnailRV.setVisibility(View.GONE);
            File thumbnail = VideoUtils.videoToBitmapToTempFile(event.entities.get(0).file);
            ImageUtils.loadImage(thumbnail, firstThumbnailIV);
        }
    }

    @ViewClick(ids = R.id.moreThumbnailBT) private void moreThumbnailBT(View v) {
        ActivityUtils.pickMedia(REQUEST_CODE_PICK_VIDEO_FOR_MORE, MediaRepository.SEARCH_TYPE_VIDEO, 1, null);
    }

    @Subscribe private void OnPickMediaEventForMore(OnPickMediaEvent event) {
        if (event.requestCode == REQUEST_CODE_PICK_VIDEO_FOR_MORE && event.entities != null && event.entities.size() > 0) {
            File file = event.entities.get(0).file;
            TaskUtils.loadData(getContext(), vm.handleThumbnailOnExe(event.entities.get(0).file), new DialogProcesser(getContext()) {
                        @Override public void onSuccess(Object tipData) {
                            firstThumbnailIV.setVisibility(View.GONE);
                            moreThumbnailRV.setVisibility(View.VISIBLE);
                            thumbnailAdapter.notifyDataSetChanged();
                        }
                    }
            );
        }
    }

    @ViewClick(ids = R.id.manualThumbnailBT) private void manualThumbnailBT(View v) {
        ActivityUtils.pickMedia(REQUEST_CODE_PICK_VIDEO_FOR_MANUAL, MediaRepository.SEARCH_TYPE_VIDEO, 1, null);
    }

    @Subscribe private void OnPickMediaEventForManual(OnPickMediaEvent event) {
        if (event.requestCode == REQUEST_CODE_PICK_VIDEO_FOR_MANUAL && event.entities != null && event.entities.size() > 0) {
            VideoThumbnailPickerActivity.startActivity((Activity) getContext(), REQUEST_CODE_PICK_VIDEO_FOR_MANUAL_PICK, event.entities.get(0).file);
        }
    }

    @Subscribe public void OnReceiveDataEventForManual(OnReceiveDataEvent event) {
        if (event.requestCode == REQUEST_CODE_PICK_VIDEO_FOR_MANUAL_PICK) {
            firstThumbnailIV.setVisibility(View.VISIBLE);
            moreThumbnailRV.setVisibility(View.GONE);
            ImageUtils.loadImage((File) event.data.getSerializable("thumbnail"), firstThumbnailIV);
        }
    }

    @ViewClick(ids = R.id.videoInfoBT) private void videoInfoBT(View v) {
        ActivityUtils.pickMedia(REQUEST_CODE_PICK_VIDEO_FOR_INFO, MediaRepository.SEARCH_TYPE_VIDEO, 1, null);
    }

    @Subscribe private void OnPickMediaEventForInfo(OnPickMediaEvent event) {
        if (event.requestCode == REQUEST_CODE_PICK_VIDEO_FOR_INFO && event.entities != null && event.entities.size() > 0) {
            File video = event.entities.get(0).file;
            VideoUtils.Size size = VideoUtils.getVideoSize(video);
            long duration = VideoUtils.getVideoDuration(video);
            int rotation = VideoUtils.getVideoRotation(video);
            videoInfoTV.setText(String.format("宽：%d 高：%d\n时长：%s 旋转角度：%d",
                    size.getWidth(), size.getHeight(), convertDurationToString(duration), rotation));
        }
    }

    private String convertDurationToString(long duration) {
        Period period = new Period(duration);
        return String.format("%02d:%02d:%02d", period.getHours(), period.getMinutes(), period.getSeconds());
    }
}