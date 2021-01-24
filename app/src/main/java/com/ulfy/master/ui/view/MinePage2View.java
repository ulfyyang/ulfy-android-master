package com.ulfy.master.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ulfy.android.adapter.RecyclerAdapter;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task.Transponder;
import com.ulfy.android.task_transponder.ContentDataRecyclerLoader;
import com.ulfy.android.task_transponder.RecyclerViewPageLoader;
import com.ulfy.android.task_transponder.SilentProcesser;
import com.ulfy.android.task_transponder_smart.SmartRefresher;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_linkage.MagicTabPagerLinkage;
import com.ulfy.android.utils.RecyclerViewUtils;
import com.ulfy.android.utils.StatusBarUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.MinePageVideoCM;
import com.ulfy.master.application.vm.MinePage2VM;
import com.ulfy.master.ui.base.BaseView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;

@Layout(id = R.layout.view_mine_page2)
public class MinePage2View extends BaseView {
    @ViewById(id = R.id.middleFL) private FrameLayout middleFL;
    @ViewById(id = R.id.tabsMI) private MagicIndicator tabsMI;
    @ViewById(id = R.id.containerVP) private ViewPager containerVP;
    @ViewById(id = R.id.zuopinContainerFL) private FrameLayout zuopinContainerFL;
    @ViewById(id = R.id.zuopinSRL) private SmartRefreshLayout zuopinSRL;
    @ViewById(id = R.id.zuopinRV) private RecyclerView zuopinRV;
    @ViewById(id = R.id.zuopinEmptyLL) private LinearLayout zuopinEmptyLL;
    @ViewById(id = R.id.shoucangContainerFL) private FrameLayout shoucangContainerFL;
    @ViewById(id = R.id.shoucangSRL) private SmartRefreshLayout shoucangSRL;
    @ViewById(id = R.id.shoucangRV) private RecyclerView shoucangRV;
    @ViewById(id = R.id.shoucangEmptyLL) private LinearLayout shoucangEmptyLL;
    private MagicTabPagerLinkage linkage = new MagicTabPagerLinkage();
    private RecyclerAdapter<MinePageVideoCM> zuopinAdapter = new RecyclerAdapter<>();
    private RecyclerAdapter<MinePageVideoCM> shoucangAdapter = new RecyclerAdapter<>();
    private SmartRefresher zuopinRefresher;
    private SmartRefresher shoucangRefresher;
    private RecyclerViewPageLoader zuopinLoader;
    private RecyclerViewPageLoader shoucangLoader;
    private MinePage2VM vm;

    public MinePage2View(Context context) {
        super(context);
        init(context, null);
    }

    public MinePage2View(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        StatusBarUtils.offsetForStatusBar(getContext(), middleFL);
        linkage.setMagicIndicator(tabsMI).setContainer(containerVP)
                .setTitleSize(16)                                                        // 设置标签文字大小
                .setTitleNormalColor(Color.parseColor("#FFFFFF"))              // 设置标签常规字体颜色
                .setTitleSelectedColor(Color.parseColor("#FFCC03"))            // 设置标签选中文字颜色
                .setTitleScale(false)                                                    // 设置标签文字是否有大小缩放效果
                .setTitleBold(false)                                                     // 设置标签文字是否加粗
                .setIndicatorBounce(true)                                                // 设置是否有弹性回滚效果
                .setIndicatorYOffsetDp(4)                                                // 设置指示器线条的偏移
                .setIndicatorColor(Color.parseColor("#FFCC03"))                // 设置指示器线条的颜色
                .setIndicatorMode(LinePagerIndicator.MODE_EXACTLY)
                .setIndicatorWidthDP(12)
                .setIndicatorHeightDP(1)                                                 // 设置指示器线条的高度
        ;

        zuopinAdapter.setEmptyView(zuopinEmptyLL);
        RecyclerViewUtils.gridLayout(zuopinRV).vertical(2).dividerDp(Color.TRANSPARENT, 1, 5, 0, 1);
        zuopinRV.setAdapter(zuopinAdapter);
        shoucangAdapter.setEmptyView(shoucangEmptyLL);
        RecyclerViewUtils.gridLayout(shoucangRV).vertical(2).dividerDp(Color.TRANSPARENT, 1, 5, 0, 1);
        shoucangRV.setAdapter(shoucangAdapter);

        zuopinRefresher = new SmartRefresher(zuopinSRL, new SmartRefresher.OnRefreshSuccessListener() {
            @Override public void onRefreshSuccess(SmartRefresher smartRefresher) {
                zuopinAdapter.notifyDataSetChanged();
            }
        });
        shoucangRefresher = new SmartRefresher(shoucangSRL, new SmartRefresher.OnRefreshSuccessListener() {
            @Override public void onRefreshSuccess(SmartRefresher smartRefresher) {
                shoucangAdapter.notifyDataSetChanged();
            }
        });
        zuopinLoader = new RecyclerViewPageLoader(zuopinRV, zuopinAdapter, null);
        shoucangLoader = new RecyclerViewPageLoader(shoucangRV, shoucangAdapter, null);
    }

    @Override public void bind(IViewModel model) {
        vm = (MinePage2VM) model;
        linkage.initStringTabs("作品", "收藏")
                .initViewPages(zuopinContainerFL, shoucangContainerFL)
                .build().select(0);

        zuopinRefresher.updateExecuteBody(vm.zuopinTaskInfo, vm.loadZuopinDataPerPageOnExe());
        shoucangRefresher.updateExecuteBody(vm.shoucangTaskInfo, vm.loadShoucangDataPerPageOnExe());
        zuopinLoader.updateExecuteBody(vm.zuopinTaskInfo, vm.loadZuopinDataPerPageOnExe());
        shoucangLoader.updateExecuteBody(vm.shoucangTaskInfo, vm.loadShoucangDataPerPageOnExe());

        zuopinAdapter.setData(vm.zuopinCMList);
        zuopinAdapter.notifyDataSetChanged();
        shoucangAdapter.setData(vm.shoucangCMList);
        shoucangAdapter.notifyDataSetChanged();
    }

    public void loadListDataAndRefreshUI() {
        vm.zuopinTaskInfo.loadStartPage();
        Transponder zuopinTransponder = vm.zuopinCMList.size() == 0 ? new ContentDataRecyclerLoader(getContext(), zuopinAdapter) : new SilentProcesser() {
            @Override protected void onSuccess(Object data) {
                zuopinAdapter.notifyDataSetChanged();
            }
        };
        TaskUtils.loadData(getContext(), vm.zuopinTaskInfo, vm.loadZuopinDataPerPageOnExe(), zuopinTransponder);

        vm.shoucangTaskInfo.loadStartPage();
        Transponder shoucangTransponder = vm.shoucangCMList.size() == 0 ? new ContentDataRecyclerLoader(getContext(), shoucangAdapter) : new SilentProcesser() {
            @Override protected void onSuccess(Object data) {
                shoucangAdapter.notifyDataSetChanged();
            }
        };
        TaskUtils.loadData(getContext(), vm.shoucangTaskInfo, vm.loadShoucangDataPerPageOnExe(), shoucangTransponder);
    }
}