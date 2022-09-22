package com.ulfy.master;

import androidx.multidex.MultiDexApplication;

import com.dueeeke.videoplayer.exo.ExoMediaPlayerFactory;
import com.dueeeke.videoplayer.player.VideoViewConfig;
import com.dueeeke.videoplayer.player.VideoViewManager;
import com.ulfy.android.multi_domain_picker.MultiDomainPicker;
import com.ulfy.android.multi_domain_picker.MultiDomainPickerConfig;
import com.ulfy.android.system.AppUtils;
import com.umeng.commonsdk.UMConfigure;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.Arrays;

public class MainApplication extends MultiDexApplication {
    public static MainApplication application;

//    static {        // 缓存配置
//        CacheConfig.Config.recordInfoCacheDirName = "local_entity_cache";       // 用于跟踪下载信息的缓存目录
//    }

//    static {        // 任务引擎配置
//        TaskConfig.Config.checkInternet = true;             // 默认不检查网络状态
//        TaskConfig.Config.NO_NET_CONNECTION_TIP = "无网络链接";
//        TaskConfig.Config.LOAD_DATA_SUCCESS_TIP = "加载完成";
//        TaskConfig.Config.LOAD_LIST_PAGE_START_TIP = "正在刷新数据...";
//        TaskConfig.Config.LOAD_LIST_PAGE_SUCCESS_START_PAGE_TIP = "刷新成功";
//        TaskConfig.Config.LOAD_LIST_PAGE_SUCCESS_NEXT_PAGE_TIP = "加载成功";
//        LoadListPageUiTask.DEFAULT_START_PAGE = 1;          // 分页任务的默认起始页
//        LoadListPageUiTask.DEFAULT_PAGE_SIZE = 20;          // 分页任务的默认页大小
//    }

//    static {        // 任务应答器配置
//        TaskTransponderConfig.Config.contentDataLoaderConfig = new TaskTransponderConfig.ContentDataLoaderConfig() {
//            @Override public IReloadView getNetErrorView(Context context) {
//                return new ContentDataLoaderFailedView(context);
//            }
//            @Override public IReloadView getFailView(Context context) {
//                return new ContentDataLoaderFailedView(context);
//            }
//            @Override public ITipView getLoadingView(Context context) {
//                return new ContentDataLoaderLoadingSimpleView(context);
//            }
//        };
//    }

//    static {        // Smart应答器配置
//        SmartConfig.smartRefreshConfig = new SmartConfig.DefaultSmartRefreshConfig() {
//            @Override public RefreshHeader getRefreshHeaderView(Context context) {
//                return super.getRefreshHeaderView(context);
//            }
//        };
//    }

//    static {        // 弹出框配置
//        DialogConfig.Config.progressDialogConfig = new DialogConfig.DefaultProgressDialogConfig() {
//            @Override public IProgressView getProgressView(Context context) {
//                return new ProgressView(context, ProgressView.SHOW_MODE_NUMBER);
//            }
//        };
//    }

//    static {        // 下载管理器配置
//        DownloadManagerConfig.Config.directoryConfig = new DownloadManagerConfig.Config.DefaultDirectoryConfig() {
//            @Override public File getDownloadingDirectory(Context context) {
//                return context.getExternalCacheDir();
//            }
//            @Override public File getDownloadedDirectory(Context context) {
//                return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), AppUtils.getAppName());
//            }
//        };
//        DownloadManagerConfig.Config.limitCount.put(DownloadManagerConfig.DEFAULT_DOWNLOAD_MANAGER_ID, 3);
//    }

//    static {        // 多域名选择器
//        MultiDomainPickerConfig.Config.domainTester = new PingDomainTester();             // 自定义域名测试器
//        MultiDomainPickerConfig.Config.domainConverter = new CopyDomainConverter();       // 自定义域名转换器
//    }

//    static {        // 数据预加载
//        DataPreLoaderManager.Config.loadDataDelayTime = 100;          // 当使用延迟加载时演示的时间（毫秒）
//    }

//    static {        // 图片处理
//        ImageConfig.Config.imageLoadingAnimator = true;              // 配置占位图动画
//        ImageConfig.Config.imageTransitionAnimator = false;          // 配置过渡动画
//    }

//    static {        // 系统模块
//        SystemConfig.Config.EXIT_TWICE_INTERVAL = 2000;            // 按两次返回退出的时间（毫秒）
//        SystemConfig.Config.killForExit = false;                   // 是否使用kill的方式退出app，用在两次返回退出
//    }

//    static {        // 网络模块配置
//        HttpConfig.Config.enableGetCache = false;                  // 是否启用Get缓存
//        HttpConfig.Config.enablePostCache = false;                 // 是否启用Post缓存
//    }

//    static {        // 工具模块配置
//        UtilsConfig.Config.logTag = "ulfy-log";                   // 打印日志TAG配置
//        UtilsConfig.Config.toastGravity = Gravity.CENTER;          // 吐司显示位置
//    }

//    static {        // 时间跟踪
//        TimeConfig.Config.recordInfoCacheDirName = "time_cache";        // 用于跟踪下载信息的缓存目录
//    }

    @Override public void onCreate() {
        super.onCreate();

        application = this;
        AppUtils.enableUnValidHttpsCertificate();           // 允许访问未认证证书的 https 网络

        if (AppUtils.isPackageProcess(this)) {
            // 配置多域名选择器
            MultiDomainPickerConfig.init(Arrays.asList(BuildConfig.HTTP_BASES));
            MultiDomainPicker.getInstance().reset();    // 若每次启动重选，则先重置
        }

//        LeakCanary.install(this);
        ZXingLibrary.initDisplayOpinion(this);
        // 初始化DK播放器
        VideoViewManager.setConfig(VideoViewConfig.newBuilder().setLogEnabled(true)
//                .setPlayerFactory(IjkPlayerFactory.create())
                .setPlayerFactory(ExoMediaPlayerFactory.create())
                .build());

        UMConfigure.setLogEnabled(BuildConfig.DEBUG);
        UMConfigure.init(this, BuildConfig.UMENG_KEY, "优菲安卓Demo", UMConfigure.DEVICE_TYPE_PHONE, null);
    }
}
