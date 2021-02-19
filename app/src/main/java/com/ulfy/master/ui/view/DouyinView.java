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
import com.ulfy.master.BuildConfig;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.DouyinCM;
import com.ulfy.master.application.vm.DouyinVM;
import com.ulfy.master.ui.base.BaseView;
import com.ulfy.master.ui.cell.DouyinCell;
import com.ulfy.master.ui.custom_dkplayer.DouyinPageLoader;

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
//        douyinVP.setOffscreenPageLimit(5);    // 这里不能设置，否则在全屏小屏切换的时候布局会错乱。如果不需要全屏可以设置
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
        startPlay(vm.enterPosition);
    }

    private void startPlay(int position) {
        vm.currentPosition = position;
        /*
            为了确保播放位置的正确性，要延后一个队列执行
                因为在notifyDataSetChanged之后立刻执行，此时RecyclerView内部的数据还未调整完毕，会导致出现位置错乱的情况
         */
        post(new Runnable() {
            @Override public void run() {
                findCurrentCell().onItemSelected();
            }
        });
    }

    /**
     * 查找当前正在播放的视频Cell，该方法并不会返回null
     */
    private DouyinCell findCurrentCell() {
        RecyclerView recyclerView = (RecyclerView) douyinVP.getChildAt(0);
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            if (((DouyinCell) recyclerView.getChildAt(i)).getIndex() == vm.currentPosition) {
                return (DouyinCell) recyclerView.getChildAt(i);
            }
        }
        return null;
    }

    private class DouyinOnPageChangeListener extends ViewPager2.OnPageChangeCallback {
        private int currentItem;

        @Override public void onPageSelected(int position) {
            if (position != currentItem) {
                currentItem = position;         // 有时候会回调两次，做一下去重
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
            if (BuildConfig.VIDEO_PRE_LOAD) {
                vm.preloadByCurrentPosition();
            }
        }

    }
}