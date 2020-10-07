package com.ulfy.master.ui.custom_dkplayer;

import android.content.Context;
import android.widget.ImageView;

import com.dueeeke.videoplayer.controller.BaseVideoController;
import com.dueeeke.videoplayer.player.VideoView;
import com.ulfy.master.R;

/**
 * 抖音界面控制器
 */
public class TikTokController extends BaseVideoController {
    private ImageView thumbIV;

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
    }

    @Override public void setPlayState(int playState) {
        super.setPlayState(playState);
        switch (playState) {
            case VideoView.STATE_IDLE:
                thumbIV.setVisibility(VISIBLE);
                break;
            case VideoView.STATE_PLAYING:
                thumbIV.setVisibility(GONE);
                break;
            case VideoView.STATE_PREPARED:
                break;
        }
    }
}
