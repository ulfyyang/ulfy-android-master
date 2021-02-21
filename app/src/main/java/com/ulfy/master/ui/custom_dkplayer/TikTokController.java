package com.ulfy.master.ui.custom_dkplayer;

import android.content.Context;
import android.net.TrafficStats;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.dueeeke.videocontroller.component.ErrorView;
import com.dueeeke.videoplayer.controller.BaseVideoController;
import com.dueeeke.videoplayer.player.VideoView;
import com.ulfy.android.task_extension.UiTimer;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.yqw.hotheart.HeartFrameLayout;
import com.yqw.hotheart.MyClickListener;

/**
 * 抖音界面控制器
 */
public class TikTokController extends BaseVideoController {
    private ImageView thumbIV;
    private LinearLayout speedLL;
    private TextView speedTV;
    private HeartFrameLayout heartFL;
    private ImageView playIV;
    private OnDoubleClickListener onDoubleClickListener;                // 由外部设置的双击回调事件
    private FrameLayout loadingFL;
    private DouyinLoadingBarView loadingDLBV;
    private SeekBar loadingSB;
    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener;    // 由外部设置的进度条拖动事件
    private boolean mIsDragging;
    private UiTimer speedTimer = new UiTimer(1000).setDelayStart(1000);
    private long lastTotalBytes = 0;        // 记录上一次App用的总流量，1秒后的总流量减去上一次即可得到网速

    /*
        只有 IJK 支持网速检测，需要在 MainAppliction 中配置 IJK 播放器，具体配置看 onCreate 方法
            1. 获取播放器网速方法：mControlWrapper.getTcpSpeed();
            2. 如果采用了预加载，则播放器无法判断网速。如果加载过程被预加载拦截，则播放器网速会是零；
        如果采用了预加载，则只能采用App网速来显示
     */

    public TikTokController(Context context) {
        super(context);
        speedTimer.setUiTimerExecuteBody(new UiTimer.UiTimerExecuteBody() {
            @Override public void onExecute(UiTimer timer, UiTimer.TimerDriver timerDriver) {
                // 计算网速
                long nowTotalBytes = TrafficStats.getUidRxBytes(getContext().getApplicationInfo().uid);
                long speed = nowTotalBytes - lastTotalBytes;
                lastTotalBytes = nowTotalBytes;
                // 渲染网速(放大3倍)
                speed *= 3; String speedString = "";
                if (speed < 1024) {
                    speedString = speed + "B/s";
                } else if (speed < 1024 * 1024) {
                    speedString = String.format("%.1fKB/s", speed * 1.0f / 1024);
                } else {
                    speedString = String.format("%.1fMB/s", speed * 1.0f / 1024 / 1024);
                }
                speedTV.setText(speedString);
            }
        }).setOnTimerStartListener(new UiTimer.OnTimerStartListener() {
            @Override public void onTimerStart(UiTimer uiTimer, UiTimer.TimerDriver timerDriver) {
                speedLL.setVisibility(View.VISIBLE);
            }
        }).setOnTimerFinishListener(new UiTimer.OnTimerFinishListener() {
            @Override public void onTimerFinish(UiTimer timer, UiTimer.TimerDriver timerDriver) {
                speedLL.setVisibility(View.GONE);
            }
        });
    }

    public ImageView getThumbIV() {
        return thumbIV;
    }

    @Override protected int getLayoutId() {
        return R.layout.view_tiktok_controller;
    }

    @Override public boolean showNetWarning() {
        return false;       //不显示移动网络播放警告
    }

    @Override protected void initView() {
        super.initView();
        addControlComponent(new ErrorView(getContext()));       //错误界面
        thumbIV = findViewById(R.id.thumbIV);
        speedLL = findViewById(R.id.speedLL);
        speedTV = findViewById(R.id.speedTV);
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
//                loadingDLBV.setVisibility(View.VISIBLE);
//                loadingDLBV.startAnimator();
                if (!speedTimer.isSchedule()) {
                    speedTimer.schedule();
                    lastTotalBytes = TrafficStats.getUidRxBytes(getContext().getApplicationInfo().uid);
                }
                break;
            case VideoView.STATE_ERROR:
            case VideoView.STATE_IDLE:
            case VideoView.STATE_START_ABORT:
            case VideoView.STATE_PREPARED:
            case VideoView.STATE_PLAYING:
            case VideoView.STATE_BUFFERED:
//                loadingDLBV.stopAnimator();
//                loadingDLBV.setVisibility(View.GONE);
                speedTimer.cancel();
                break;
        }
    }

    @Override protected void setProgress(int duration, int position) {
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

    @Override public void setPlayerState(int playerState) {
        LayoutParams layoutParams = (LayoutParams) loadingFL.getLayoutParams();
        switch (playerState) {
            case VideoView.PLAYER_NORMAL:
                layoutParams.bottomMargin = (int) UiUtils.dp2px(40);
                break;
            case VideoView.PLAYER_FULL_SCREEN:
                layoutParams.bottomMargin = (int) -UiUtils.dp2px(9);
                break;
        }
        loadingFL.setLayoutParams(layoutParams);
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
