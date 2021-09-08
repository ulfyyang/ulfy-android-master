package com.ulfy.master.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.viewpager.widget.ViewPager;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_linkage.MagicTabPagerLinkage;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.TabPager2VM;
import com.ulfy.master.ui.base.BaseView;

import net.lucode.hackware.magicindicator.MagicIndicator;

@Layout(id = R.layout.view_tab_pager2)
public class TabPager2View extends BaseView {
    @ViewById(id = R.id.tabsMI) private MagicIndicator tabsMI;
    @ViewById(id = R.id.containerVP) private ViewPager containerVP;
    @ViewById(id = R.id.containerFL) private FrameLayout containerFL;
    @ViewById(id = R.id.page1FL) private FrameLayout page1FL;
    @ViewById(id = R.id.page2FL) private FrameLayout page2FL;
    @ViewById(id = R.id.page3FL) private FrameLayout page3FL;
    private MagicTabPagerLinkage linkage = new MagicTabPagerLinkage();
    private TabPager2VM vm;

    public TabPager2View(Context context) {
        super(context);
        init(context, null);
    }

    public TabPager2View(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /*
        注意事项：
            1. 显示容器只支持 ViewPager
            2. 标签按钮只支持字符串
            3. 标签页支持 View 和 Fragment
            4. 下划线和图像指示器只能选一种
     */
    private void init(Context context, AttributeSet attrs) {
        linkage.setMagicIndicator(tabsMI).setContainer(containerVP)
                .setAverage(true)                                                       // 均分模式（MagicIndicator需填充父控件）
                .setTitleSize(18)                                                       // 设置标签文字大小
                .setTitleNormalColor(Color.parseColor("#616161"))             // 设置标签常规字体颜色
                .setTitleSelectedColor(Color.parseColor("#f57c00"))           // 设置标签选中文字颜色
                .setTitleScale(false)                                                   // 设置标签文字是否有大小缩放效果
                .setTitleBold(true)                                                     // 设置是否字体加粗
//                .setIndicatorBounce(false)                                              // 设置是否有弹性回滚效果
//                .setIndicatorMode(LinePagerIndicator.MODE_WRAP_CONTENT)                 // 设置标签下划线的宽度模式
//                .setIndicatorWidthDP(20)                                                // 设置指示器线条宽度，设置 MODE_EXACTLY 生效
//                .setIndicatorHeightDP(2)                                                // 设置指示器线条的高度
//                .setIndicatorYOffsetDp(0)                                               // 设置指示器线条距离文字的偏移量
//                .setIndicatorColor(Color.parseColor("#f57c00"))               // 设置指示器线条的颜色
                .setIndicatorResource(R.drawable.shape_shadow)                          // 设置指示器图像
                .setIndicatorPaddingDp(8)                                              // 设置指示器图像到内容的内边距
                .setIndicatorPaddingLeftDp(12)      // 因为阴影占了一部分宽度，所以重新设置下方三个边的边距
                .setIndicatorPaddingRightDp(12)
                .setIndicatorPaddingBottonDp(12)
                .addOnTabSelectedListener(index -> UiUtils.show("选中了标签页：" + index))// 当标签页被选中时回调
        ;
    }

    @Override public void bind(IViewModel model) {
        vm = (TabPager2VM) model;
        linkage.initStringTabs("页码1", "第二个标签页", "标签页三")
                .initViewPages(page1FL, page2FL, page3FL)
                .build().select(0);
    }
}