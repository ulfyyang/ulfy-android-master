package com.ulfy.master.infrastructure.http;

import com.alibaba.fastjson.JSON;
import com.ulfy.android.okhttp.HttpUtils;
import com.ulfy.android.utils.StringUtils;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.RequestBody;

/**
 * 发送 Form 表单，接收 Json 数据
 */
class FormJsonHttpUtils {

    /*
    这块的方法包括三种：发送表单数据请求接收一个对象、数组、字符串，每个方法都有一个可以发送文件参数的重载
     */

    public static <T> T requestObject(String urlPart, Map<Object, Object> params, Class<T> clazz) throws Exception {
        return requestObject(urlPart, params, null, clazz);
    }

    public static <T> T requestObject(String urlPart, Map<Object, Object> params, Map<Object, File> fileMap, Class<T> clazz) throws Exception {
        String result = requestString(urlPart, params, fileMap);
        if (clazz == null || StringUtils.isEmpty(result)) {
            return null;
        }
        return JSON.parseObject(result, clazz);
    }

    public static <T> List<T> requestArray(String urlPart, Map<Object, Object> params, Class<T> clazz) throws Exception {
        return requestArray(urlPart, params, null, clazz);
    }

    public static <T> List<T> requestArray(String urlPart, Map<Object, Object> params, Map<Object, File> fileMap, Class<T> clazz) throws Exception {
        String result = requestString(urlPart, params, fileMap);
        if (clazz == null || StringUtils.isEmpty(result)) {
            return null;
        }
        return JSON.parseArray(result, clazz);
    }

    public static String requestString(String urlPart, Map<Object, Object> params) throws Exception {
        return requestString(urlPart, params, null);
    }

    public static String requestString(String urlPart, Map<Object, Object> params, Map<Object, File> fileMap) throws Exception {
        return requestStringInner(urlPart, params, fileMap, false);
    }

    /*
    这个方法是发送 Form 请求的基础方法，上边的所有方法最终都会走到这个方法里面
    在该方法中可以做一些公共的操作，例如：添加公共的请求参数，对结果进行过滤，失败重试等
     */

    /**
     * 采用 form 方式向服务器发送请求
     * @param urlPart       部分 url 地址，如果以 http/htts 开头则不需要拼接根地址
     * @param params        表单普通参数
     * @param fileMap       表单文件参数
     * @param haveTryed     是否尝试了一次，如果没有尝试则可以选择性的再尝试一次（用于请求重试）
     * @return  请求到的内容
     */
    public static String requestStringInner(String urlPart, Map<Object, Object> params, Map<Object, File> fileMap, boolean haveTryed) throws Exception {
        Map<Object, Object> requestParams = new TreeMap<>();

        // 添加公共字段
//        if (User.isLogin() && !StringUtils.isEmpty(User.getCurrentUser().accessToken)) {
//            requestParams.put("token", User.getCurrentUser().accessToken);
//        }

        // 添加业务参数
        if (params != null) {
            requestParams.putAll(params);
        }

        // 构建请求体
        RequestBody requestBody = null;

        if (fileMap == null) {
            requestBody = HttpUtils.generateFormBody(requestParams, false);
        } else {
            requestBody = HttpUtils.generateMultipartFormBody(requestParams, fileMap);
        }

        // 执行请求并返回结果
        return BaseHttpUtils.requestStringInner(urlPart, requestBody, haveTryed);
    }

}
