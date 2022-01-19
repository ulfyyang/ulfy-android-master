package com.ulfy.extra

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.ulfy.android.dialog.DialogUtils
import java.lang.Exception

object System {

    /**
     * 卸载多个App，每次卸载时弹出一个提示(由于卸载过程是异步过程，因此只会卸载列表中找到的第一个App)
     * 必须在清单文件中添加以下权限
     * <uses-permission android:name="android.permission.REQUEST_DELETE_PACKAGES"/>    <!--适配9.0卸载问题-->
     * @param context 上下文，必须是Activity上下文
     * @param packageNames 卸载的App包名数组
     * @param message 卸载之前弹出的确认提示框文字
     */
    fun uninstallApp(context: Context, packageNames: Array<String>?, message: String) {
        if (context !is Activity) {
            throw Exception("context must be a activity context!")
        }
        if (packageNames == null || packageNames.isEmpty()) {
            return
        }
        packageNames.filter { it.isNotEmpty() }.firstOrNull { it.checkAppInstalled(context) }?.let {
            showTipThenDo(context, message) {
                it.uninstallApp(context)
            }
        }
    }

    private fun showTipThenDo(context: Context, message: String, runnable: Runnable) {
        DialogUtils.showAlertTwoButtonDialog(context, "卸载应用", message) {
            runnable.run()
        }
    }
    private fun String.checkAppInstalled(context: Context) = try {
        context.packageManager.getPackageInfo(this, 0)
        true
    } catch (e: Exception) {
        false
    }
    private fun String.uninstallApp(context: Context) {
        val intent = Uri.fromParts("package", this, null)
            .run { Intent(Intent.ACTION_DELETE, this) }
        context.startActivity(intent)
    }
}