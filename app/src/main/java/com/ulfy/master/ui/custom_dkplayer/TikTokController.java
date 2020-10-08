package com.ulfy.master.ui.custom_dkplayer;

import android.content.Context;
import android.widget.ImageView;

import com.dueeeke.videoplayer.controller.BaseVideoController;
import com.dueeeke.videoplayer.player.VideoView;
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
    private OnDoubleClickListener onDoubleClickListener;

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

        heartFL.setOnClickListener(new MyClickListener(new MyClickListener.MyClickCallBack() {
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
        }));
    }

    @Override public void setPlayState(int playState) {
        super.setPlayState(playState);
        switch (playState) {
            case VideoView.STATE_IDLE:
                thumbIV.setVisibility(VISIBLE);
                break;
            case VideoView.STATE_PREPARED:
                break;
            case VideoView.STATE_PLAYING:
                thumbIV.setVisibility(GONE);
                playIV.setVisibility(GONE);
                break;
            case VideoView.STATE_PAUSED:
                playIV.setVisibility(VISIBLE);
                break;
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

    public interface OnDoubleClickListener {
        void onDoubleClick();
    }
}
