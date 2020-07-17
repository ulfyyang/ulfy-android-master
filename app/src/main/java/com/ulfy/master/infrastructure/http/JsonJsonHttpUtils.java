package com.ulfy.master.infrastructure.http;

import com.alibaba.fastjson.JSON;
import com.ulfy.android.okhttp.HttpUtils;
import com.ulfy.android.utils.StringUtils;

import java.util.List;

import okhttp3.RequestBody;

/**
 * 发送 Json 数据，接收 Json 数据
 */
class JsonJsonHttpUtils {

    /**
     * 如果采用 json 发送数据，则该结构为最外层的公共结构
     */
    public static class BaseSend {
        public String token;        // 公共参数举例
        public Object data;         // 业务对象
    }

    /*
    这块的方法包括三种：发送对象数据请求接收一个对象、数组、字符串
     */

    public static <T> T requestObject(String urlPart, Object params, Class<T> clazz) throws Exception {
        String result = requestString(urlPart, params);
        if (clazz == null || StringUtils.isEmpty(result)) {
            return null;
        }
        return JSON.parseObject(result, clazz);
    }

    public static <T> List<T> requestArray(String urlPart, Object params, Class<T> clazz) throws Exception {
        String result = requestString(urlPart, params);
        if (clazz == null || StringUtils.isEmpty(result)) {
            return null;
        }
        return JSON.parseArray(result, clazz);
    }

    public static String requestString(String urlPart, Object params) throws Exception {
        return requestStringInner(urlPart, params, false);
    }

    /*
    这个方法是发送 Json 请求的基础方法，上边的所有方法最终都会走到这个方法里面
    在该方法中可以做一些公共的操作，例如：添加公共的请求参数，对结果进行过滤，失败重试等
     */

    /**
     * 采用 json 方式向服务器发送请求
     * @param urlPart       部分 url 地址，如果以 http/htts 开头则不需要拼接根地址
     * @param params        业务对象，最终会被编码为 json 字符串
     * @param haveTryed     是否尝试了一次，如果没有尝试则可以选择性的再尝试一次（用于请求重试）
     * @return  请求到的内容
     */
    private static String requestStringInner(String urlPart, Object params, boolean haveTryed) throws Exception {
        BaseSend requestParams = new BaseSend();

        // 添加公共字段
//        if (User.isLogin() && !StringUtils.isEmpty(User.getCurrentUser().accessToken)) {
//            requestParams.token = User.getCurrentUser().accessToken;
//        }

        // 添加业务参数
        requestParams.data = params;

        // 构建请求体
        RequestBody requestBody = HttpUtils.generateJsonBody(JSON.toJSONString(requestParams));

        // 执行请求并返回结果
        return BaseHttpUtils.requestStringInner(urlPart, requestBody, haveTryed);
    }

}
