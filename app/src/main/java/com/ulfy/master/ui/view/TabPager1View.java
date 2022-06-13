package com.ulfy.master.ui.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.ui_linkage.TabPagerLinkage;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.TabPager1VM;
import com.ulfy.master.ui.base.BaseView;

import java.util.Random;

@Layout(id = R.layout.view_tab_pager1)
public class TabPager1View extends BaseView {
    @ViewById(id = R.id.tabsTL) private TabLayout tabsTL;
    @ViewById(id = R.id.tab1FL) private FrameLayout tab1FL;
    @ViewById(id = R.id.tab1TV) private TextView tab1TV;
    @ViewById(id = R.id.tab2FL) private FrameLayout tab2FL;
    @ViewById(id = R.id.tab2TV) private TextView tab2TV;
    @ViewById(id = R.id.tab3FL) private FrameLayout tab3FL;
    @ViewById(id = R.id.tab3TV) private TextView tab3TV;
    @ViewById(id = R.id.tab4FL) private FrameLayout tab4FL;
    @ViewById(id = R.id.tab4TV) private TextView tab4TV;
    @ViewById(id = R.id.tab5FL) private FrameLayout tab5FL;
    @ViewById(id = R.id.tab5TV) private TextView tab5TV;
    @ViewById(id = R.id.tab6FL) private FrameLayout tab6FL;
    @ViewById(id = R.id.tab6TV) private TextView tab6TV;
    @ViewById(id = R.id.containerVP) private ViewPager containerVP;
    @ViewById(id = R.id.containerFL) private FrameLayout containerFL;
    @ViewById(id = R.id.tabPage1LL) private LinearLayout tabPage1LL;
    @ViewById(id = R.id.modifyTabBT) private Button modifyTabBT;
    @ViewById(id = R.id.tabPage2LL) private LinearLayout tabPage2LL;
    private TabPagerLinkage linkage = new TabPagerLinkage();
    private TabPager1VM vm;

    public TabPager1View(Context context) {
        super(context);
        init(context, null);
    }

    public TabPager1View(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /*
        显示容器与页面类型设置规则：
            1. 当设置 ViewGroup 为 Fragment 显示容器时需要通过 id 来设置，如：R.id.containerFL
            2. 其它情况根据业务要求设置 ViewPager、ViewGroup 即可
        自动滚动模式的解释：
            默认情况下标签显示是按照屏幕权重均分的，当标签很多时往往会因为屏幕太小而挤压变形，该模式可以
            在屏幕没有足够空间的时候改变标签显示为滚动模式，这样用户可以通过拖拽的方式移动标签页
        切换按钮的设置：
            1. 切换按钮支持 可变数组 和 List，方便灵活的编码
            2. 字符串适合单纯的只显示文字的切换按钮，这时候和原生的表现一致
        其它事项：
            1. 标签显示样式由 select 属性设定，可以通过开发 selector 资源自定义显示的样式
            2. TabLayout 在下划线包裹内容时在 AppBarLayout 下有显示的 bug
            3. 如果对标签设置了点击事件会屏蔽 TabLayout 的点击事件，导致无法进行切换。如果有较高的定制需求可使用 PagerLinkage
     */
    private void init(Context context, AttributeSet attrs) {
        // 用于快速切换TabLayout的模式，通过注释快速开关代码
        tabsTL.setTabMode(TabLayout.MODE_FIXED);
//        tabsTL.setTabMode(TabLayout.MODE_SCROLLABLE);
//        tabsTL.setTabMode(TabLayout.MODE_AUTO);
        // 基本属性设置
        linkage.setTabLayout(tabsTL).setContainer(containerVP)          // 设置关联的 TabLayout、显示页面容器（ViewPager 可滚动、ViewGroup 不可滚动）
            .setAutoScrollMode(true)                                    // 当一屏无法显示所有的 Tab按钮 时自动切换为可滚动模式
//            .setLineWidth(TabPagerLinkage.LINE_WIDTH_MATCH_PARENT)      // 设置指示器下划线。线宽的三种模式：填充、包裹、固定长度。默认填充。具体值查看源码即可
            .setLineWidth(TabPagerLinkage.LINE_WIDTH_WRAP_CONTENT)      // 设置指示器下划线。线宽的三种模式：填充、包裹、固定长度。默认填充。具体值查看源码即可
//            .setLineWidthDP(30)                                         // 设置指示器下划线。线宽的三种模式：填充、包裹、固定长度。默认填充。具体值查看源码即可
            .setDividerWidthDP(10)      // 设置 Tab按钮 之间的间隔。对 LINE_WIDTH_WRAP_CONTENT、MODE_FIXED 模式下设置无效
            .addOnTabSelectedListener(index -> UiUtils.show("选中了标签页：" + index))     // 添加标签页被选中时的回调
            .addOnTabSelectedListener(index -> {
                tab1TV.setTypeface(Typeface.DEFAULT);tab1TV.setTextSize(14);
                tab2TV.setTypeface(Typeface.DEFAULT);tab2TV.setTextSize(14);
                if (index == 0) {
                    tab1TV.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    tab1TV.setTextSize(20);
                } else if (index == 1) {
                    tab2TV.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    tab2TV.setTextSize(20);
                }
                linkage.updateOnLayoutChanged();
            })
        ;
    }

    @Override public void bind(IViewModel model) {
        vm = (TabPager1VM) model;

        /*
            标签按钮设置，实际测试的时候只需要放开对应的注释代码即可查看不同情况下的表现
            标签按钮的数量需要和标签页的数量相同，否则会出现数量对不上的情况
         */
//        linkage.initStringTabs("标签页一");
        // 设置两个字符串，不占满一屏。常规标签页测试
//        linkage.initStringTabs("标签页一", "标签页二标签页二");
        // 多设置几个字符串，使其宽度超过屏幕。测试不同宽度标签与超过屏幕宽度，需下方标签页数组足够
//        linkage.initStringTabs("1", "标签页22", "标签页333", "标签页4444", "标签页55555", "标签页666666标签页");
        linkage.initViewTabs(tab1FL, tab2FL);
//        linkage.initViewTabs(tab1FL, tab2FL, tab3FL, tab4FL, tab5FL, tab6FL);

        /*
            标签页设置，实际测试的时候只需要放开对应的注释代码即可查看不同情况下的表现
            标签按钮的数量需要和标签页的数量相同，否则会出现数量对不上的情况
         */
//        linkage.initViewPages(tabPage1LL);
        // 设置两个标签页（支持View、Fragment）
        linkage.initViewPages(tabPage1LL, tabPage2LL);
        // 多设置几个标签页，和标签按钮数量相同
//        linkage.initViewPages(
//            tabPage1LL, tabPage2LL, generateTabPage(2), generateTabPage(3),
//            generateTabPage(4), generateTabPage(5)
//        );

        /*
            构造联动并显示第一个标签页
         */
        linkage.build().select(0);
    }

    /**
     * 生成标签页的分页，每一页都是一个铺满父容器的 文本页面
     */
    private View generateTabPage(int index) {
        TextView textView = new TextView(getContext());
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        textView.setGravity(Gravity.CENTER);
        textView.setText(String.format("标签页%d", index + 1));
        return textView;
    }

    /**
     * 点击：修改 Tab 内容，仅针对自定义 View 的标签按钮（暂时存在问题，尽量不要动态修改标签按钮内容）
     *      当修改了了标签按钮的内容时需要调用 wrapContentIfNeed 方法来修复页面的布局
     */
    @ViewClick(ids = R.id.modifyTabBT) private void modifyTabBT(View v) {
        tab1TV.setText("随机Tab：" + new Random().nextInt(10000));
        tab2TV.setText("随机Tab：" + new Random().nextInt(10000));
        linkage.updateOnLayoutChanged();
    }
}