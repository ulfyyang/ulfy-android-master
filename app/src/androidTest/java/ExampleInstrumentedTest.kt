package com.ulfy.master

import android.content.Intent
import android.net.Uri
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.ulfy.extra.System
import com.ulfy.master.ui.activity.List1Activity
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

}

/**
 * 测试卸载指定的App
 */
@RunWith(AndroidJUnit4::class)
class TestUninstallApp {
    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    /*
    拆分步骤测试
     */

    @Test fun checkAppInstalled() { // 检查一个app是否安装了
        val packageName = "com.ulfy.master"
//        val packageName = "com.ulfy.animal"
        val find = try {
            appContext.packageManager.getPackageInfo(packageName, 0)
            true
        } catch (e: Exception) {
            false
        }
        Assert.assertTrue(find)
    }

    @Test fun uninstallApp() {      // 以弹出框提示方式卸载app
        // <uses-permission android:name="android.permission.REQUEST_DELETE_PACKAGES"/>    <!--适配9.0卸载问题-->
        // Intent.FLAG_ACTIVITY_NEW_TASK 在非Activity环境中启动Activity
        val packageName = "com.ulfy.master"
        val intent = Uri.fromParts("package", packageName, null)
            .run { Intent(Intent.ACTION_DELETE, this).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
        appContext.startActivity(intent)
    }

    /*
    主步骤测试：必须在清单文件中添加以下权限
    <uses-permission android:name="android.permission.REQUEST_DELETE_PACKAGES"/>    <!--适配9.0卸载问题-->
     */
    @Test fun uninstallAppByMessage() {
        val packageNames = arrayOf("", "com.ulfy.animal", "com.ulfy.master", "com.google.android.youtube")
        val message = "发现旧安装包，是否删除？"
        ActivityScenario.launch(List1Activity::class.java).onActivity {
            System.uninstallApp(it, packageNames, message)
        }.result.resultCode
    }
}