package com.ulfy.master.ui.custom_dkplayer;

import android.content.Context;
import android.util.Log;

import com.dueeeke.videoplayer.player.VideoView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 视频播放器仓库，用于对多个播放器进行全局管理
 */
public final class VideoViewRepository {
    private static final String TAG = VideoViewRepository.class.getName();
    private static VideoViewRepository instance = new VideoViewRepository();
    private Map<Context, List<VideoView>> videoViewMap = new HashMap<>();

    private VideoViewRepository() {

    }

    public static VideoViewRepository getInstance() {
        return instance;
    }

    /**
     * 将VideoView添加到仓库中
     */
    public void addVideoView(VideoView videoView) {
        List<VideoView> videoViewList = videoViewMap.get(videoView.getContext());
        if (videoViewList == null) {
            videoViewList = new ArrayList<>();
            videoViewMap.put(videoView.getContext(), videoViewList);
        }
        if (!videoViewList.contains(videoView)) {
            videoViewList.add(videoView);
        }
        printRepository();
    }

    /**
     * 将VideoView从仓库中移除
     */
    public void removeVideoView(VideoView videoView) {
        List<VideoView> videoViewList = videoViewMap.get(videoView.getContext());
        if (videoViewList != null) {
            videoViewList.remove(videoView);
            if (videoViewList.size() == 0) {
                videoViewMap.remove(videoView.getContext());
            }
        }
        printRepository();
    }

    /**
     * 暂停上下文中的所有播放器
     */
    public void pause(Context context) {
        List<VideoView> videoViewList = videoViewMap.get(context);
        if (videoViewList != null) {
            for (VideoView videoView : videoViewList) {
                videoView.pause();
            }
        }
    }

    /**
     * 恢复上下文中的所有播放器
     */
    public void resume(Context context) {
        List<VideoView> videoViewList = videoViewMap.get(context);
        if (videoViewList != null) {
            for (VideoView videoView : videoViewList) {
                videoView.resume();
            }
        }
    }

    /**
     * 释放指定上下文的中的VideoView
     *  通常在Activity.onDestroy中回调
     */
    public void releaseVideoView(Context context, boolean remove) {
        List<VideoView> videoViewList = videoViewMap.get(context);
        if (videoViewList != null) {
            for (VideoView videoView : videoViewList) {
                videoView.release();
            }
            if (remove) {
                videoViewMap.remove(context);
            }
        }
        printRepository();
    }

    /**
     * 用于判断当前上下文中是否有响应返回键的操作
     */
    public boolean onBackPressed(Context context) {
        List<VideoView> videoViewList = videoViewMap.get(context);
        if (videoViewList != null) {
            for (VideoView videoView : videoViewList) {
                if (videoView.onBackPressed()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 打印上下文对应的VideoView信息，当操作了添加或删除类似方法时会打印当前仓库中的VideoView信息
     */
    private void printRepository() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("showing activity video view map\n");

        for (Map.Entry<Context, List<VideoView>> entry : videoViewMap.entrySet()) {
            stringBuilder.append(entry.getKey().getClass().getName()).append("==>").append(entry.getValue().size());
            stringBuilder.append('\n');
        }

        Log.i(TAG, stringBuilder.toString());
    }
}
