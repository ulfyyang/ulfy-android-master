package com.ulfy.master.infrastructure.http;

import com.alibaba.fastjson.JSON;
import com.ulfy.android.okhttp.HttpUtils;
import com.ulfy.master.BuildConfig;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 网络访问底层封装，提供了 form、json 两种方式访问网络
 */
class BaseHttpUtils {

    /**
     * 接收请求最外层的结构，其中 data 为具体的业务数据
     */
    public static class BaseRecv {
        public int status;          // 状态码
        public String message;      // 提示信息
        public String data;         // 具体的业务数据
    }

    /**
     * 请求网络数据并返回其中的内容，该方法返回的内容已经去除了错误码和消息
     * @param urlPart       部分 url 地址，如果以 http/htts 开头则不需要拼接根地址
     * @param requestBody   请求体
     * @param haveTryed     是否尝试了一次，如果没有尝试则可以选择性的再尝试一次（用于请求重试）
     * @return  请求到的内容
     */
    public static String requestStringInner(String urlPart, RequestBody requestBody, boolean haveTryed) throws Exception {
        BaseRecv baseRecv;

        try {
            String wholeUrl = urlPart;

            // 如果不是完整的地址则拼接公共根目录
            if (!urlPart.startsWith("http://") && !urlPart.startsWith("https://")) {
                wholeUrl = BuildConfig.HTTP_BASE + urlPart;
            }

            // 构建请求，实际情况可根据业务规则进行定制
            Request request = new Request.Builder().url(wholeUrl).post(requestBody).build();

            // 执行请求访问网络
            String response = HttpUtils.execute(HttpUtils.defaultClient(), request);

            // 解析结果的外层结构
            baseRecv = JSON.parseObject(response, BaseRecv.class);
        } catch (Exception e) {
            // 不使用二次重试
            throw e;
            // 尝试进行二次重试
//            if (!haveTryed) {
//                return requestStringInner(urlPart, requestBody, true);
//            } else {
//                throw e;
//            }
        }

        // 走到这里说明网络访问本身没有问题，之后就是根据返回码进行具体的处理
        if (baseRecv.status == 200) {          // 业务访问成功
            return baseRecv.data;
        } else if (baseRecv.status == 400) {   // 登录超时
//            AppConfig.logout();
//            UiUtils.runOnUiThread(new Runnable() {
//                @Override public void run() {
//                    LoginActivity.startActivity();
//                }
//            });
            throw new IllegalStateException(baseRecv.message);
        } else {                                    // 其它错误统一处理
            throw new IllegalStateException(baseRecv.message);
        }
    }
}
