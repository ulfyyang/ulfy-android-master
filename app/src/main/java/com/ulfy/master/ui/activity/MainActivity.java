package com.ulfy.master.ui.activity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.ulfy.android.system.AppUtils;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_linkage.TabPagerLinkage;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.MainVM;
import com.ulfy.master.ui.base.BaseActivity;
import com.ulfy.master.ui.fragment.ChargeFragment;
import com.ulfy.master.ui.fragment.ControlsFragment;
import com.ulfy.master.ui.fragment.FindFragment;
import com.ulfy.master.ui.fragment.HomeFragment;
import com.ulfy.master.ui.fragment.MeFragment;

@Layout(id = R.layout.activity_main)
public class MainActivity extends BaseActivity {
    /*
    声明Tab页面显示的容器，需要使用那种特性则具体配置
     */
    @ViewById(id = R.id.containerVP) private ViewPager containerVP;
    @ViewById(id = R.id.containerFL) private FrameLayout containerFL;
    /*
    底部Tab容器声明
     */
    @ViewById(id = R.id.tabTL) private TabLayout tabTL;
    /*
    对具体的子Tab的声明
     */
    @ViewById(id = R.id.homeLL) private LinearLayout homeLL;
    @ViewById(id = R.id.homeIV) private ImageView homeIV;
    @ViewById(id = R.id.homeTV) private TextView homeTV;
    @ViewById(id = R.id.chargeLL) private LinearLayout chargeLL;
    @ViewById(id = R.id.chargeIV) private ImageView chargeIV;
    @ViewById(id = R.id.chargeTV) private TextView chargeTV;
    @ViewById(id = R.id.findLL) private LinearLayout findLL;
    @ViewById(id = R.id.findIV) private ImageView findIV;
    @ViewById(id = R.id.findTV) private TextView findTV;
    @ViewById(id = R.id.controlsLL) private LinearLayout controlsLL;
    @ViewById(id = R.id.controlsIV) private ImageView controlsIV;
    @ViewById(id = R.id.controlsTV) private TextView controlsTV;
    @ViewById(id = R.id.meLL) private LinearLayout meLL;
    @ViewById(id = R.id.meIV) private ImageView meIV;
    @ViewById(id = R.id.meTV) private TextView meTV;
    /*
    Tab页面管理联动
     */
    private TabPagerLinkage linkage = new TabPagerLinkage();
    /*
    声明具体显示的Tab页面
     */
    private HomeFragment homeFragment = new HomeFragment();
    private ChargeFragment chargeFragment = new ChargeFragment();
    private FindFragment findFragment = new FindFragment();
    private ControlsFragment controlsFragment = new ControlsFragment();
    private MeFragment meFragment = new MeFragment();
    /*
    相应的业务模型
     */
    private MainVM vm = new MainVM();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        显示容器与页面类型设置规则：
            1. Fragment 在 ViewGroup 中需要通过 id 来设置，这种情况下在设置容器的时候需要填写 id
            2. 其它情况根据业务要求设置 ViewPager、ViewGroup 即可
        切换按钮的设置：
            1. 切换按钮支持 可变数组 和 List，方便灵活的编码
            2. 字符串适合单纯的只显示文字的切换按钮，这时候和原始的表现一致
         */
        linkage.setTabLayout(tabTL).setContainer(containerVP)       // 设置 TabLayout 和 显示容器，显示容器可以是 ViewPager、ViewGroup
                .initViewTabs(homeLL, chargeLL, findLL, controlsLL, meLL)   // 设置切换的按钮，支持 可变数组 和 List，类型支持 字符串、View
                .initFragmentPages(homeFragment, chargeFragment, findFragment, controlsFragment, meFragment)    // 设置显示的页面，支持 可变数组 和 List，类型支持 View、Fragment
                .build().select(0);     // 构造并默认显示第一个
    }

    @Override public void onBackPressed() {
        AppUtils.exitTwice("再按一次退出" + AppUtils.getAppName());
    }
}
