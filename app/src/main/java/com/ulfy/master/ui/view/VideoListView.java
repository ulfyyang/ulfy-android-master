package com.ulfy.master.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_linkage.MagicTabPagerLinkage;
import com.ulfy.android.utils.StatusBarUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.VideoListVM;
import com.ulfy.master.ui.base.BaseView;
import com.ulfy.master.ui.fragment.VideoListPageFragment;
import com.ulfy.master.ui.fragment.VideoListPageRecommendFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.List;

@Layout(id = R.layout.view_video_list)
public class VideoListView extends BaseView {
    @ViewById(id = R.id.headLL) private LinearLayout headLL;
    @ViewById(id = R.id.dailyLL) private LinearLayout dailyLL;
    @ViewById(id = R.id.searchLL) private LinearLayout searchLL;
    @ViewById(id = R.id.searchTV) private TextView searchTV;
    @ViewById(id = R.id.publishLL) private LinearLayout publishLL;
    @ViewById(id = R.id.tabsMI) private MagicIndicator tabsMI;
    @ViewById(id = R.id.moreIV) private ImageView moreIV;
    @ViewById(id = R.id.containerVP) private ViewPager containerVP;
    @ViewById(id = R.id.containerFL) private FrameLayout containerFL;
    private MagicTabPagerLinkage linkage = new MagicTabPagerLinkage();
    private VideoListVM vm;

    public VideoListView(Context context) {
        super(context);
        init(context, null);
    }

    public VideoListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        StatusBarUtils.offsetForStatusBar(getContext(), headLL);
        linkage.setMagicIndicator(tabsMI).setContainer(containerVP)
                .setTitleSize(18)                                                        // 设置标签文字大小
                .setTitleNormalColor(Color.parseColor("#141414"))              // 设置标签常规字体颜色
                .setTitleSelectedColor(Color.parseColor("#eb1b32"))            // 设置标签选中文字颜色
                .setTitleScale(true)                                                     // 设置标签文字是否有大小缩放效果
                .setTitleBold(true)                                                      // 设置标签文字是否加粗
                .setIndicatorBounce(false)                                               // 设置是否有弹性回滚效果
                .setIndicatorHeightDP(0)                                                 // 设置指示器线条的高度
        ;
    }

    @Override public void bind(IViewModel model) {
        vm = (VideoListVM) model;
        linkage.initStringTabs(vm.categoryList)
                .initFragmentPages(generatePagesByCategoryList())
                .build().select(1);
    }

    private List<Fragment> generatePagesByCategoryList() {
        List<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < vm.categoryList.size(); i++) {
            switch (vm.categoryList.get(i)) {
                case "推荐":
                    fragmentList.add(new VideoListPageRecommendFragment());
                    break;
                default:
                    fragmentList.add(new VideoListPageFragment());
                    break;
            }
        }
        return fragmentList;
    }
}