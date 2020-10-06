package com.dueeeke.videoplayer.controller;

import android.view.View;
import android.view.animation.Animation;

public interface IControlComponent {

    void attach(ControlWrapper controlWrapper);

    View getView();

    void onVisibilityChanged(boolean isVisible, Animation anim);

    void onPlayStateChanged(int playState);

    void onPlayerStateChanged(int playerState);

    void setProgress(int duration, int position);

    void onLockStateChanged(boolean isLocked);

}
