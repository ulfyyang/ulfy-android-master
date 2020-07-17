package com.ulfy.master.ui.activity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.google.common.base.Joiner;
import com.just.agentweb.AgentWeb;
import com.ulfy.android.system.ActivityUtils;
import com.ulfy.android.utils.StringUtils;
import com.ulfy.master.ui.base.TitleContentActivity;

import java.util.ArrayList;
import java.util.List;

public class AgentWebActivity extends TitleContentActivity {
    public AgentWeb agentWeb;

    public static void startActivity() {
        ActivityUtils.startActivity(AgentWebActivity.class);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 模拟从页面传过来的参数
        Bundle data = new Bundle();
        data.putString("url", "www.baidu.com");

        // 处理接收到的参数，然后加载之
        loadUrl(generateUrlFromBundle(data));
    }

    public static String generateUrlFromBundle(Bundle bundle) {
        if (bundle != null) {

            // 获取url参数
            String url = "", params = "";
            // 获取url地址
            url = StringUtils.complementUrl(bundle.getString("url"));
            // 去除url，剩余的是参数
            bundle.remove("url");

//            // 添加自定义参数
//            bundle.putInt("deviceType", 2);
//            if (User.isLogin()) {
//                bundle.putString("userIDStr", User.getCurrentUser().userIDStr);
//                bundle.putString("token", User.getCurrentUser().token);
//            }

            // 生成参数
            List<String> paramsStringList = new ArrayList<>();
            for(String key : bundle.keySet()) {
                if (key != null && bundle.get(key) != null) {
                    paramsStringList.add(String.format("%s=%s", key, bundle.get(key).toString()));
                }
            }
            params = Joiner.on("&").join(paramsStringList);

            // 如果目标url存在?说明有参数,则将自己的参数追加到后面;否则设置自己的参数到url后
            if (!StringUtils.isEmpty(params)) {
                url = String.format("%s%s%s", url, url.contains("?") ? "&" : "?", params);
            }

            return url;
        } else {
            return "";
        }
    }

    /**
     * 简化版loadUrl，如需更多功能请查看下方完整版
     */
    private void loadUrl(String url) {
        if (agentWeb == null) {
            agentWeb = AgentWeb.with(this)
                    .setAgentWebParent((ViewGroup) contentFL, new FrameLayout.LayoutParams(-1, -1))
                    .useDefaultIndicator()
                    .setWebChromeClient(new WebChromeClient() {
                        public void onReceivedTitle(WebView view, String title) {
                            titleTV.setText(title);
                        }
                    }).createAgentWeb().ready().go(url);
        } else {
            agentWeb.getUrlLoader().loadUrl(url);
        }
    }

    /**
     * 建议配置
     */
    @Override public void onBackPressed() {
        if (agentWeb == null || !agentWeb.back()) {
            super.onBackPressed();
        }
    }

    /**
     * 必须配置，否则很容导致崩溃
     */
    @Override public void onDestroy() {
        if (agentWeb != null) {
            agentWeb.getWebLifeCycle().onDestroy();
        }
        super.onDestroy();
    }
}