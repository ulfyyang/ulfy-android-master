package com.ulfy.master.ui.custom_dkplayer;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.dueeeke.videoplayer.controller.BaseVideoController;
import com.dueeeke.videoplayer.player.VideoView;
import com.ulfy.android.utils.LogUtils;
import com.ulfy.master.R;
import com.yqw.hotheart.HeartFrameLayout;
import com.yqw.hotheart.MyClickListener;

/**
 * 抖音界面控制器
 */
public class TikTokController extends BaseVideoController {
    private ImageView thumbIV;
    private HeartFrameLayout heartFL;
    private ImageView playIV;
    private OnDoubleClickListener onDoubleClickListener;                // 由外部设置的双击回调事件
    private FrameLayout loadingFL;
    private DouyinLoadingBarView loadingDLBV;
    private SeekBar loadingSB;
    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener;    // 由外部设置的进度条拖动事件
    private boolean mIsDragging;

    public TikTokController(Context context) {
        super(context);
    }

    public ImageView getThumbIV() {
        return thumbIV;
    }

    @Override protected int getLayoutId() {
        return R.layout.view_tiktok_controller;
    }

    @Override protected void initView() {
        super.initView();
        thumbIV = findViewById(R.id.thumbIV);
        heartFL = findViewById(R.id.heartFL);
        playIV = findViewById(R.id.playIV);
        loadingFL = findViewById(R.id.loadingFL);
        loadingDLBV = findViewById(R.id.loadingDLBV);
        loadingSB = findViewById(R.id.loadingSB);
        heartFL.setOnClickListener(new MyClickListener(new OnHeartClickListener()));
        loadingSB.setOnSeekBarChangeListener(new OnSeekListener());
    }

    @Override protected void onPlayStateChanged(int playState) {
        super.onPlayStateChanged(playState);
        if (playState == VideoView.STATE_PLAYING) {
            startProgress();
        }
        updateThumbUI(playState);
        updateHotHeartUI(playState);
        updateLoadingUI(playState);
    }

    private void updateThumbUI(int playState) {
        switch (playState) {
            case VideoView.STATE_IDLE:
                thumbIV.setVisibility(VISIBLE);
                break;
            case VideoView.STATE_PLAYING:
                thumbIV.setVisibility(GONE);
                break;
        }
    }

    private void updateHotHeartUI(int playState) {
        switch (playState) {
            case VideoView.STATE_PLAYING:
                playIV.setVisibility(GONE);
                break;
            case VideoView.STATE_PAUSED:
                playIV.setVisibility(VISIBLE);
                break;
        }
    }

    private void updateLoadingUI(int playState) {
        switch (playState) {
            case VideoView.STATE_PREPARING:
            case VideoView.STATE_BUFFERING:
                loadingDLBV.setVisibility(View.VISIBLE);
                loadingDLBV.startAnimator();
                break;
            case VideoView.STATE_PLAYING:
            case VideoView.STATE_BUFFERED:
                loadingDLBV.stopAnimator();
                loadingDLBV.setVisibility(View.GONE);
                break;
        }
    }

    @Override protected void setProgress(int duration, int position) {
        LogUtils.log("d:" + duration + " p:" + position);
        if (!mIsDragging) {
            if (duration > 0) {
                loadingSB.setEnabled(true);
                int pos = (int) (position * 1.0 / duration * loadingSB.getMax());
                loadingSB.setProgress(pos);
            } else {
                loadingSB.setEnabled(false);
            }

            int percent = mControlWrapper.getBufferedPercentage();
            if (percent >= 95) { //解决缓冲进度不能100%问题
                loadingSB.setSecondaryProgress(loadingSB.getMax());
            } else {
                loadingSB.setSecondaryProgress(percent * 10);
            }
        }
    }

    /**
     * 红心单击双击回调
     */
    private class OnHeartClickListener implements MyClickListener.MyClickCallBack {
        @Override public void onSimpleClick() {
            if (mControlWrapper.isPlaying()) {
                mControlWrapper.pause();
            } else {
                mControlWrapper.start();
            }
        }
        @Override public void onDoubleClick() {
            if (onDoubleClickListener != null) {
                onDoubleClickListener.onDoubleClick();
            }
        }
    }

    /**
     * 拖动进度条监听
     */
    private class OnSeekListener implements SeekBar.OnSeekBarChangeListener {
        @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (onSeekBarChangeListener != null) {
                onSeekBarChangeListener.onProgressChanged(seekBar, progress, fromUser);
            }
        }
        @Override public void onStartTrackingTouch(SeekBar seekBar) {
            mIsDragging = true;
            stopProgress();
            if (onSeekBarChangeListener != null) {
                onSeekBarChangeListener.onStartTrackingTouch(seekBar);
            }
        }
        @Override public void onStopTrackingTouch(SeekBar seekBar) {
            mIsDragging = false;
            long duration = mControlWrapper.getDuration();
            long newPosition = (duration * seekBar.getProgress()) / seekBar.getMax();
            mControlWrapper.seekTo((int) newPosition);
            startProgress();
            if (onSeekBarChangeListener != null) {
                onSeekBarChangeListener.onStopTrackingTouch(seekBar);
            }
        }
    }

    /**
     * 设置是否开启双击红心特效，该设置不影响双击回调的开关
     */
    public void setEnableDoubleEffect(boolean enableDoubleEffect) {
        heartFL.setEnableDoubleEffect(enableDoubleEffect);
    }

    /**
     * 设置双击回调，设置了双击就会启用
     */
    public void setOnDoubleClickListener(OnDoubleClickListener onDoubleClickListener) {
        this.onDoubleClickListener = onDoubleClickListener;
    }

    /**
     * 设置拖动条回调
     */
    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        this.onSeekBarChangeListener = onSeekBarChangeListener;
    }

    public interface OnDoubleClickListener {
        void onDoubleClick();
    }
}
