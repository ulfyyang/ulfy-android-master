package com.ulfy.master.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_transponder.ContentDataLoader;
import com.ulfy.android.task_transponder.OnReloadListener;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.application.vm.VideoCommentVM;
import com.ulfy.master.ui.base.ContentView;

public class VideoCommentContentView extends ContentView {
    private VideoCommentVM vm;
    private VideoCommentView view;

    public VideoCommentContentView(Context context) {
        super(context);
        setBackgroundColor(Color.parseColor("#161824"));      // 这里需要有个背景色，否则数据加载的时候会是透明的
        setLayoutParams(new ViewGroup.LayoutParams(                     // 这里设置为BottomSheet的最大高度，否则数据加载是会塌陷
                ViewGroup.LayoutParams.MATCH_PARENT, (int) (UiUtils.screenHeight() * 0.618)));
        initModel(null);
        initContent(null);
    }

    /**
     * 初始化模型和界面
     */
    private void initModel(Bundle savedInstanceState) {
        vm = new VideoCommentVM();
    }

    /**
     * 初始化界面的数据
     */
    private void initContent(final Bundle savedInstanceState) {
        TaskUtils.loadData(getContext(), vm.taskInfo, vm.loadCommentDataPerPageOnExe(), new ContentDataLoader(contentFL, vm, false) {
                    @Override protected void onCreateView(ContentDataLoader loader, View createdView) {
                        view = (VideoCommentView) createdView;
                    }
                }.setOnReloadListener(new OnReloadListener() {
                    @Override public void onReload() {
                        initContent(savedInstanceState);
                    }
                })
        );
    }
}