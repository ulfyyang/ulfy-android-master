package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.ulfy.android.adapter.RecyclerAdapter;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.utils.StringUtils;
import com.ulfy.master.BuildConfig;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.DouyinCM;
import com.ulfy.master.application.vm.DouyinVM;
import com.ulfy.master.ui.base.BaseView;
import com.ulfy.master.ui.cell.DouyinCell;
import com.ulfy.master.ui.custom_dkplayer.DouyinPageLoader;
import com.ulfy.master.ui.custom_dkplayer.cache.PreloadManager;

@Layout(id = R.layout.view_douyin)
public class DouyinView extends BaseView {
    @ViewById(id = R.id.douyinVP) private ViewPager2 douyinVP;
    private RecyclerAdapter<DouyinCM> douyinAdapter = new DouyinAdapter<>();
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
        douyinVP.setOffscreenPageLimit(5);
        douyinVP.setAdapter(douyinAdapter);
        douyinVP.setOverScrollMode(View.OVER_SCROLL_NEVER);
        douyinVP.registerOnPageChangeCallback(new DouyinOnPageChangeListener());
        loader = new DouyinPageLoader(douyinVP);
    }

    @Override public void bind(IViewModel model) {
        vm = (DouyinVM) model;
        loader.updateExecuteBody(vm.douyinTaskInfo, vm.loadDataPerPageOnExe());

        douyinAdapter.setData(vm.douyinCMList);
        douyinAdapter.notifyDataSetChanged();

        // 为了保证在播放时页面已经准备完毕，通过post执行
        vm.currentPosition = vm.enterPosition;
        douyinVP.setCurrentItem(vm.enterPosition, false);
        post(new Runnable() {
            @Override public void run() {
                startPlay(vm.enterPosition);
            }
        });
    }

    private void startPlay(int position) {
        vm.currentPosition = position;
        RecyclerView recyclerView = (RecyclerView) douyinVP.getChildAt(0);
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            if (recyclerView.getChildAt(i) instanceof DouyinCell) {
                DouyinCell cell = (DouyinCell) recyclerView.getChildAt(i);
                if (cell.getIndex() == position) {
                    cell.onItemSelected();
                    break;
                }
            }
        }
    }

    private class DouyinOnPageChangeListener extends ViewPager2.OnPageChangeCallback {
        private int currentItem;

        @Override public void onPageSelected(int position) {
            if (position != currentItem) {
                startPlay(position);
            }
        }

        @Override public void onPageScrollStateChanged(int state) {
            if (state == ViewPager2.SCROLL_STATE_DRAGGING) {
                currentItem = douyinVP.getCurrentItem();
            }
        }
    }

    /**
     * 在页面创建时添加预加载，在页面销毁时取消预加载
     */
    private class DouyinAdapter<M extends IViewModel> extends RecyclerAdapter<M> {

        @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
            // ViewPager2要求必须要填充父控件，否则会抛异常崩溃
            viewHolder.itemView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return viewHolder;
        }

        @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            super.onBindViewHolder(holder, position);
            if (BuildConfig.VIDEO_PRE_LOAD && holder.itemView instanceof DouyinCell) {
                String playUrl = ((DouyinCell) holder.itemView).getPlayUrl();
                if (!StringUtils.isEmpty(playUrl)) {
                    PreloadManager.getInstance(getContext()).addPreloadTask(playUrl, position);
                }
            }
        }

        @Override public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
            super.onViewDetachedFromWindow(holder);
            if (BuildConfig.VIDEO_PRE_LOAD && holder.itemView instanceof DouyinCell) {
                String playUrl = ((DouyinCell) holder.itemView).getPlayUrl();
                if (!StringUtils.isEmpty(playUrl)) {
                    PreloadManager.getInstance(getContext()).removePreloadTask(playUrl);
                }
            }
        }

    }
}