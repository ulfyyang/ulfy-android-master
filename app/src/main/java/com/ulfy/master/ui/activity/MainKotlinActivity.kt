package com.ulfy.master.ui.activity

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.ulfy.android.system.AppUtils
import com.ulfy.android.ui_injection.Layout
import com.ulfy.android.ui_injection.ViewById
import com.ulfy.android.ui_linkage.TabPagerLinkage
import com.ulfy.master.R
import com.ulfy.master.application.vm.MainVM
import com.ulfy.master.databinding.ActivityMainBinding
import com.ulfy.master.ui.base.BaseActivity
import com.ulfy.master.ui.fragment.*

class MainKotlinActivity : BaseActivity() {
    private val linkage = TabPagerLinkage()     // Tab页面管理联动
    private val homeFragment = HomeFragment()               // 声明具体显示的Tab页面
    private val chargeFragment = ChargeFragment()           // 声明具体显示的Tab页面
    private val findFragment = FindFragment()               // 声明具体显示的Tab页面
    private val controlsFragment = ControlsFragment()       // 声明具体显示的Tab页面
    private val meFragment = MeFragment()                   // 声明具体显示的Tab页面
    private lateinit var binding: ActivityMainBinding
    private val vm = MainVM()                   // 相应的业务模型

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            /*
                显示容器与页面类型设置规则：
                    1. Fragment 在 ViewGroup 中需要通过 id 来设置，这种情况下在设置容器的时候需要填写 id
                    2. 其它情况根据业务要求设置 ViewPager、ViewGroup 即可
                切换按钮的设置：
                    1. 切换按钮支持 可变数组 和 List，方便灵活的编码
                    2. 字符串适合单纯的只显示文字的切换按钮，这时候和原始的表现一致
             */
            linkage.setTabLayout(tabTL).setContainer(containerVP) // 设置 TabLayout 和 显示容器，显示容器可以是 ViewPager、ViewGroup
                .initViewTabs(homeLL, chargeLL, findLL, controlsLL, meLL) // 设置切换的按钮，支持 可变数组 和 List，类型支持 字符串、View
                .initFragmentPages(homeFragment, chargeFragment, findFragment, controlsFragment, meFragment) // 设置显示的页面，支持 可变数组 和 List，类型支持 View、Fragment
                .build().select(0) // 构造并默认显示第一个
        }
    }

    override fun onBackPressed() = AppUtils.exitTwice("再按一次退出" + AppUtils.getAppName())
}