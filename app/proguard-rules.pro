# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile



################################ 业务 ################################
-keep class com.yulong.tomMovie.domain.**  { *; }
-keep class com.yulong.tomMovie.infrastructure.**  { *; }



################################ DKPlayer ################################
-keep class com.dueeeke.videoplayer.** { *; }
-dontwarn com.dueeeke.videoplayer.**
# IjkPlayer
-keep class tv.danmaku.ijk.** { *; }
-dontwarn tv.danmaku.ijk.**
# ExoPlayer
-keep class com.google.android.exoplayer2.** { *; }
-dontwarn com.google.android.exoplayer2.**



################################ 公共 ################################
# 代码混淆压缩比，在0~7之间，默认为5，一般不做修改
-optimizationpasses 5
# 混合时不使用大小写混合，混合后的类名为小写
-dontusemixedcaseclassnames
# 指定不去忽略非公共库的类
-dontskipnonpubliclibraryclasses
# 这句话能够使我们的项目混淆后产生映射文件
# 包含有类名->混淆后类名的映射关系
-verbose
# 指定不去忽略非公共库的类成员
-dontskipnonpubliclibraryclassmembers
# 不做预校验，preverify是proguard的四个步骤之一，Android不需要preverify，去掉这一步能够加快混淆速度
-dontpreverify
# 禁止优化，这句话会导致后面的assumenosideeffects失效
# -dontoptimize
-ignorewarnings
# 保留Annotation不混淆
-keepattributes *Annotation*
# 避免混淆泛型
-keepattributes Signature
-keepattributes Exceptions,InnerClasses
-dontnote com.google.vending.licensing.ILicensingService
-dontnote com.android.vending.licensing.ILicensingService
# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable
-keepattributes Deprecated,Synthetic,EnclosingMethod
# 重命名抛出异常时的文件名称
-renamesourcefileattribute SourceFile
# 指定混淆是采用的算法，后面的参数是一个过滤器
# 这个过滤器是谷歌推荐的算法，一般不做更改
-optimizations !code/simplification/cast,!field/*,!class/merging/*



################################ 安卓 ################################
# 默认保留系统组件
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}
# 保留自定义View
-keep public class * extends android.view.View {
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
# 保留自定义View的构造方法
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
# 保留View方法，便于直接在布局文件中写单击事件
-keepclassmembers class * {
    public void *(android.view.View);
}
# webView处理，项目中没有使用到webView忽略即可
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
    public *;
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.webView, jav.lang.String);
}
# 保留native方法
-keepclasseswithmembernames class * {
    native <methods>;
}
# 保留R文件
-keep class **.R$* { *; }
# 对于带有回调函数的onXXEvent、**On*Listener的，不能被混淆
-keepclassmembers class * {
    void *(**On*Event);
    void *(**On*Listener);
}
# 保留枚举
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
# 保留Parcelable序列化
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
# 保留Serializable序列化
-keep class * implements java.io.Serializable { *; }
# 去掉Log打印
# 需要注意的是，必须不能有关闭优化的配置-dontoptimize，否则上述方法将无效
# 安卓默认提供了proguard-android-optimize.txt、proguard-android.txt，要使用前者前者移除了-dontoptimize
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** e(...);
    public static *** i(...);
    public static *** v(...);
    public static *** println(...);
    public static *** w(...);
    public static *** wtf(...);
}
-assumenosideeffects class java.io.PrintStream {
  public *** println(...);
  public *** print(...);
}



################################ ulfy ################################


################################ agentWeb ################################
-keep class com.just.agentweb.** {
    *;
}
-dontwarn com.just.agentweb.**

# webView处理，项目中没有使用到webView忽略即可
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
    public *;
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.webView, jav.lang.String);
}


################################ ulfy-bus ################################
# 必须保留事件总线订阅方法，因为这种方法通常不会被其它地方引用
-keepclassmembers class ** {
    @com.ulfy.android.bus.Subscribe <methods>;
}


################################ ulfy-cache ################################
# 保留Serializable序列化
-keep class * implements java.io.Serializable { *; }


################################ ulfy-download-manager ################################
# 保留Serializable序列化
-keep class * implements java.io.Serializable { *; }
# 保留DownloadTaskInfo
-keep public interface com.ulfy.android.download_manager.DownloadTaskInfo { *; }
# 因为不知道okdownload的具体规则，因此保留全部
-keep class com.liulishuo.okdownload.** { *; }


################################ ulfy-image ################################
# glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}
# okhttp
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
-dontwarn com.franmontiel.persistentcookiejar.**
-keep class com.franmontiel.persistentcookiejar.**
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}


################################ ulfy-multi-domain-picker ################################
# 保留Serializable序列化
-keep class * implements java.io.Serializable { *; }


################################ ulfy-okhttp ################################
# okhttp
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
-dontwarn com.franmontiel.persistentcookiejar.**
-keep class com.franmontiel.persistentcookiejar.**
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}


################################ ulfy-smart-refresh-layout ################################
-keep class com.github.mmin18.** {*;}
-dontwarn android.support.v8.renderscript.*
-keepclassmembers class android.support.v8.renderscript.RenderScript {
  native *** rsn*(...);
  native *** n*(...);
}
-keep class com.wang.avi.** { *; }
-keep class com.wang.avi.indicators.** { *; }
-keep class com.scwang.refreshlayout.activity.practice.BannerPracticeActivity$Movie {*;}


################################ ulfy-time ################################
# 保留Serializable序列化
-keep class * implements java.io.Serializable { *; }


################################ ulfy-ui-injection ################################
# 不需要保留ViewById字段，如果有其它地方用到了会自动保留
#-keepclassmembers class ** {
#    @com.ulfy.android.ui_injection.ViewById <fields>;
#}
# 必须保留View点击方法，因为这种方法通常不会被其它地方引用
-keepclassmembers class ** {
    @com.ulfy.android.ui_injection.ViewClick <methods>;
}


################################ ulfy-umeng ################################
# 友盟
-keep class com.umeng.** {*;}
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
# 微信
-keep class com.tencent.mm.opensdk.** { *; }
-keep class com.tencent.wxop.** { *; }
-keep class com.tencent.mm.sdk.** { *; }
# QQ
-keep class com.tencent.** {*;}


################################ ulfy-utils ################################
# 关闭日志打印
-assumenosideeffects class com.ulfy.android.utils.LogUtils {
    public static void log(...);
}


################################ 三方库 ################################
# XBanner 图片轮播混淆配置
-keep class com.stx.xhb.xbanner.**{*;}