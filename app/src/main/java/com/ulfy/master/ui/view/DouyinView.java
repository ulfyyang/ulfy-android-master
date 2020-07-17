package com.ulfy.master.ui.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.dueeeke.videoplayer.player.VideoView;
import com.ulfy.android.adapter.RecyclerAdapter;
import com.ulfy.android.utils.RecyclerViewUtils;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.DouyinCM;
import com.ulfy.master.application.vm.DouyinVM;
import com.ulfy.master.ui.base.BaseView;
import com.ulfy.master.ui.cell.DouyinCell;

@Layout(id = R.layout.view_douyin)
public class DouyinView extends BaseView {
    @ViewById(id = R.id.douyinRV) private RecyclerView douyinRV;
    private RecyclerAdapter<DouyinCM> douyinCMAdapter = new RecyclerAdapter<>();
    private VideoView mVideoView;
    private TikTokController mTikTokController;
    private int mCurrentPosition = -1;              // 初始化位置
    private DouyinVM vm;

    public DouyinView(Context context) {
        super(context);
        init(context, null);
    }

    public DouyinView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(final Context context, AttributeSet attrs) {
        mVideoView = new VideoView(getContext());
        mVideoView.setLooping(true);
        mTikTokController = new TikTokController(getContext());
        mVideoView.setVideoController(mTikTokController);

        RecyclerViewUtils.viewPagerLayout(douyinRV).vertical(new RecyclerViewUtils.OnViewPagerListener() {
            @Override public void onPageSelected(View selectedView, int positionInRecyclerView, int positionInData, boolean first, boolean last) {
                if (mCurrentPosition != positionInData) {
                    ((DouyinCell)douyinRV.getChildAt(0)).onItemSelected(mVideoView, mTikTokController);
                    mCurrentPosition = positionInData;
                }
            }
            @Override public void onPageReleased(View releasedView, int positionInRecyclerView, int positionInData, boolean forward) {
                if (mCurrentPosition == positionInData) {
                    mVideoView.release();
                }
            }
        });
        douyinRV.setAdapter(douyinCMAdapter);
    }

    @Override public void bind(IViewModel model) {
        vm = (DouyinVM) model;
        douyinCMAdapter.setData(vm.douyinCMList);
        douyinCMAdapter.notifyDataSetChanged();
    }

    public void onPause() {
        mVideoView.pause();
    }

    public void onResume() {
        mVideoView.resume();
    }

    public void onDestroy() {
        mVideoView.release();
    }
}