package com.ulfy.master

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ulfy.android.ui_linkage.TabPagerLinkage
import com.ulfy.master.ui.activity.TabPager1Activity
import com.ulfy.master.ui.activity.TabPager2Activity
import com.ulfy.master.ui.activity.TabPager3Activity
import com.ulfy.master.ui.activity.TabPager4Activity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleLinkage {

    /**
     * 系统原生 TabLayout 标签页封装
     * 实现类：[TabPagerLinkage]
     */
    @Test fun launch_TabLayout() = TabPager1Activity::class.java.launchWait()
    // MagicIndicator 标签页封装
    @Test fun launch_MagicIndicator() = TabPager2Activity::class.java.launchWait()
    // ViewGroup 标签页封装
    @Test fun launch_ViewGroup() = TabPager3Activity::class.java.launchWait()
    // 完全手动实现标签页
    @Test fun launch_Custom() = TabPager4Activity::class.java.launchWait()

}