package com.ulfy.master.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.ulfy.android.ui_linkage.OnTabSelectedListener;
import com.ulfy.android.ui_linkage.PagerLinkage;
import com.ulfy.android.utils.RecyclerViewUtils;
import com.ulfy.android.utils.StatusBarUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.MinePageVideoCM;
import com.ulfy.master.application.vm.MinePageVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_mine_page)
public class MinePageView extends BaseView {
    @ViewById(id = R.id.smartSRL) private SmartRefreshLayout smartSRL;
    @ViewById(id = R.id.middleFL) private FrameLayout middleFL;
    @ViewById(id = R.id.zuopinFL) private FrameLayout zuopinFL;
    @ViewById(id = R.id.zuopinTV) private TextView zuopinTV;
    @ViewById(id = R.id.shoucangFL) private FrameLayout shoucangFL;
    @ViewById(id = R.id.shoucangTV) private TextView shoucangTV;
    @ViewById(id = R.id.containerVP) private ViewPager containerVP;
    @ViewById(id = R.id.zuopinContainerFL) private FrameLayout zuopinContainerFL;
    @ViewById(id = R.id.zuopinRV) private RecyclerView zuopinRV;
    @ViewById(id = R.id.zuopinEmptyLL) private LinearLayout zuopinEmptyLL;
    @ViewById(id = R.id.shoucangContainerFL) private FrameLayout shoucangContainerFL;
    @ViewById(id = R.id.shoucangRV) private RecyclerView shoucangRV;
    @ViewById(id = R.id.shoucangEmptyLL) private LinearLayout shoucangEmptyLL;
    private PagerLinkage linkage = new PagerLinkage();
    private RecyclerAdapter<MinePageVideoCM> zuopinAdapter = new RecyclerAdapter<>();
    private RecyclerAdapter<MinePageVideoCM> shoucangAdapter = new RecyclerAdapter<>();
    private SmartRefresher smartRefresher;
    private RecyclerViewPageLoader zuopinLoader;
    private RecyclerViewPageLoader shoucangLoader;
    private MinePageVM vm;

    public MinePageView(Context context) {
        super(context);
        init(context, null);
    }

    public MinePageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        StatusBarUtils.offsetForStatusBar(getContext(), middleFL);

        linkage.setContainer(containerVP).addOnTabSelectedListener(new OnTabSelectedListener() {
            @Override public void onTabSelected(int index) {
                if (index == 0) {
                    smartRefresher.updateExecuteBody(vm.zuopinTaskInfo, vm.loadZuopinDataPerPageOnExe());
                } else if (index == 1) {
                    smartRefresher.updateExecuteBody(vm.shoucangTaskInfo, vm.loadShoucangDataPerPageOnExe());
                }
            }
        });

        zuopinAdapter.setEmptyView(zuopinEmptyLL);
        RecyclerViewUtils.gridLayout(zuopinRV).vertical(2).dividerDp(Color.TRANSPARENT, 1, 5, 0, 1);
        zuopinRV.setAdapter(zuopinAdapter);
        shoucangAdapter.setEmptyView(shoucangEmptyLL);
        RecyclerViewUtils.gridLayout(shoucangRV).vertical(2).dividerDp(Color.TRANSPARENT, 1, 5, 0, 1);
        shoucangRV.setAdapter(shoucangAdapter);

        smartRefresher = new SmartRefresher(smartSRL, new SmartRefresher.OnRefreshSuccessListener() {
            @Override public void onRefreshSuccess(SmartRefresher smartRefresher) {
                if (containerVP.getCurrentItem() == 0) {
                    zuopinAdapter.notifyDataSetChanged();
                } else if (containerVP.getCurrentItem() == 1) {
                    shoucangAdapter.notifyDataSetChanged();
                }
            }
        });
        zuopinLoader = new RecyclerViewPageLoader(zuopinRV, zuopinAdapter, null);
        shoucangLoader = new RecyclerViewPageLoader(shoucangRV, shoucangAdapter, null);
    }

    @Override public void bind(IViewModel model) {
        vm = (MinePageVM) model;
        linkage.initViewTabs(zuopinFL, shoucangFL)
                .initViewPages(zuopinContainerFL, shoucangContainerFL)
                .build().select(0);

        smartRefresher.updateExecuteBody(vm.zuopinTaskInfo, vm.loadZuopinDataPerPageOnExe());
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