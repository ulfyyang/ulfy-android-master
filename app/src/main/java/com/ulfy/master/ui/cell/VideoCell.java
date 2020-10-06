package com.ulfy.master.ui.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videocontroller.component.CompleteView;
import com.dueeeke.videocontroller.component.ErrorView;
import com.dueeeke.videocontroller.component.GestureView;
import com.dueeeke.videocontroller.component.PrepareView;
import com.dueeeke.videocontroller.component.TitleView;
import com.dueeeke.videocontroller.component.VodControlView;
import com.dueeeke.videoplayer.player.VideoView;
import com.ulfy.android.image.ImageUtils;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.views.ShapeLayout;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.VideoCM;
import com.ulfy.master.ui.base.BaseCell;
import com.ulfy.master.ui.custom_dkplayer.VideoViewRepository;

@Layout(id = R.layout.cell_video)
public class VideoCell extends BaseCell {
    @ViewById(id = R.id.playerVV) private VideoView playerVV;
    @ViewById(id = R.id.coverFL) private FrameLayout coverFL;
    @ViewById(id = R.id.coverIV) private ImageView coverIV;
    @ViewById(id = R.id.titleTV) private TextView titleTV;
    @ViewById(id = R.id.countTV) private TextView countTV;
    @ViewById(id = R.id.timeTV) private TextView timeTV;
    @ViewById(id = R.id.userLL) private LinearLayout userLL;
    @ViewById(id = R.id.avatarIV) private ImageView avatarIV;
    @ViewById(id = R.id.isAuthorSL) private ShapeLayout isAuthorSL;
    @ViewById(id = R.id.nameTV) private TextView nameTV;
    @ViewById(id = R.id.fanCountTV) private TextView fanCountTV;
    @ViewById(id = R.id.commentLL) private LinearLayout commentLL;
    @ViewById(id = R.id.commentTV) private TextView commentTV;
    @ViewById(id = R.id.shareLL) private LinearLayout shareLL;
    private PrepareView prepareView;
    private TitleView titleView;
    private VideoCM cm;

    public VideoCell(Context context) {
        super(context);
        init(context, null);
    }

    public VideoCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        prepareView = new PrepareView(getContext());
        titleView = new TitleView(getContext());

        StandardVideoController videoController = new StandardVideoController(getContext());
        videoController.addControlComponent(prepareView);
        videoController.addControlComponent(new CompleteView(getContext()));
        videoController.addControlComponent(new ErrorView(getContext()));
        videoController.addControlComponent(titleView);
        videoController.addControlComponent(new VodControlView(getContext()));
        videoController.addControlComponent(new GestureView(getContext()));
        playerVV.setVideoController(videoController);

        playerVV.setOnStateChangeListener(new VideoView.OnStateChangeListener() {
            @Override public void onPlayerStateChanged(int playerState) { }
            @Override public void onPlayStateChanged(int playState) {
                playerVV.setVisibility(playState != VideoView.STATE_IDLE ? View.VISIBLE : View.GONE);
                coverFL.setVisibility(playState == VideoView.STATE_IDLE ? View.VISIBLE : View.GONE);
            }
        });

        VideoViewRepository.getInstance().addVideoView(playerVV);
    }

    @Override public void bind(IViewModel model) {
        cm = (VideoCM) model;
        playerVV.release();
        playerVV.setVisibility(View.GONE);
        coverFL.setVisibility(View.VISIBLE);
        ImageUtils.loadImage(cm.cover, R.drawable.drawable_loading, coverIV);
        titleTV.setText(cm.title);
        ImageUtils.loadImage(cm.cover, R.drawable.drawable_loading, prepareView.findViewById(R.id.thumb));
        titleView.setTitle(cm.title);
        playerVV.setUrl(cm.playUrl);
    }

    @ViewClick(ids = R.id.coverFL) private void coverFL(View v) {
        playerVV.start();
    }

    public boolean isPlaying() {
        return playerVV.isPlaying();
    }
}