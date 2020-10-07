package com.ulfy.master.ui.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.dueeeke.videoplayer.player.VideoView;
import com.ulfy.android.adapter.PagerAdapter;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.android.views.VerticalViewPager;
import com.ulfy.master.BuildConfig;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.DouyinCM;
import com.ulfy.master.application.vm.DouyinVM;
import com.ulfy.master.ui.base.BaseView;
import com.ulfy.master.ui.cell.DouyinCell;
import com.ulfy.master.ui.custom_dkplayer.DouyinPageLoader;
import com.ulfy.master.ui.custom_dkplayer.TikTokController;
import com.ulfy.master.ui.custom_dkplayer.cache.PreloadManager;

@Layout(id = R.layout.view_douyin)
public class DouyinView extends BaseView {
    @ViewById(id = R.id.douyinVP) private VerticalViewPager douyinVP;
    private PagerAdapter<DouyinCM> douyinAdapter = new DouyinAdapter<>();
    private VideoView videoView;
    private TikTokController tikTokController;
    private int currentPosition;
    private DouyinPageLoader loader;
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
        videoView = new VideoView(getContext());
        videoView.setLooping(true);
//        videoView.setScreenScaleType(VideoView.SCREEN_SCALE_CENTER_CROP);
        tikTokController = new TikTokController(getContext());
        videoView.setVideoController(tikTokController);

        douyinVP.setOffscreenPageLimit(5);
        douyinVP.setVertical(true);
        douyinVP.setAdapter(douyinAdapter);

        douyinVP.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private int currentItem;
            private boolean isReverseScroll;
            @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position != currentItem) {
                    isReverseScroll = position < currentItem;
                }
            }
            @Override public void onPageSelected(int position) {
                if (position != currentItem) {
                    startPlay(position);
                }
            }
            @Override public void onPageScrollStateChanged(int state) {
                if (state == VerticalViewPager.SCROLL_STATE_DRAGGING) {
                    currentItem = douyinVP.getCurrentItem();
                }
                if (BuildConfig.VIDEO_PRE_LOAD) {
                    if (state == VerticalViewPager.SCROLL_STATE_IDLE) {
                        PreloadManager.getInstance(getContext()).resumePreload(currentPosition, isReverseScroll);
                    } else {
                        PreloadManager.getInstance(getContext()).pausePreload(currentPosition, isReverseScroll);
                    }
                }
            }
        });

        loader = new DouyinPageLoader(douyinVP);
    }

    @Override public void bind(IViewModel model) {
        vm = (DouyinVM) model;
        loader.updateExecuteBody(vm.douyinTaskInfo, vm.loadDataPerPageOnExe());

        douyinAdapter.setData(vm.douyinCMList);
        douyinAdapter.notifyDataSetChanged();

        // 为了保证在播放时页面已经准备完毕，通过post执行
        douyinVP.setCurrentItem(0);
        post(new Runnable() {
            @Override public void run() {
                startPlay(0);
            }
        });
    }

    private void startPlay(int position) {
        for (int i = 0; i < douyinVP.getChildCount(); i ++) {
            if (douyinVP.getChildAt(i) instanceof DouyinCell) {
                DouyinCell cell = (DouyinCell) douyinVP.getChildAt(i);
                if (cell.getIndex() == position) {
                    videoView.release();
                    UiUtils.clearParent(videoView);
                    cell.onItemSelected(videoView, tikTokController);
                    currentPosition = position;
                    break;
                }
            }
        }
    }

    /**
     * 在页面创建时添加预加载，在页面销毁时取消预加载
     */
    private class DouyinAdapter<M extends IViewModel> extends PagerAdapter {
        @Override public Object instantiateItem(ViewGroup container, int position) {
            if (BuildConfig.VIDEO_PRE_LOAD) {
                String playUrl = vm.douyinCMList.get(position).videoUrl;
                PreloadManager.getInstance(container.getContext()).addPreloadTask(playUrl, position);
            }
            return super.instantiateItem(container, position);
        }
        @Override public void destroyItem(ViewGroup container, int position, Object object) {
            if (BuildConfig.VIDEO_PRE_LOAD) {
                String playUrl = vm.douyinCMList.get(position).videoUrl;
                PreloadManager.getInstance(container.getContext()).removePreloadTask(playUrl);
            }
            super.destroyItem(container, position, object);
        }
    }

    public void onPause() {
        videoView.pause();
    }

    public void onResume() {
        videoView.resume();
    }

    public void onDestroy() {
        videoView.release();
        if (BuildConfig.VIDEO_PRE_LOAD) {
            PreloadManager.getInstance(getContext()).removeAllPreloadTask();
        }
    }
}