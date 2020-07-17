package com.ulfy.master.infrastructure.encryption;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Base64编码工具
 *      已验证，没有标注已验证的表示还没有验证其正确性
 */
public class Base64Utils {

    /**
     * 编码常规字符串为base64字符串
     */
    public static String encode(byte[] content) {
        return Base64.encodeToString(content, Base64.DEFAULT);
    }

    /**
     * 解码base64字符串为常规字符串
     */
    public static byte[] decode(String content) throws UnsupportedEncodingException {
        return Base64.decode(content, Base64.DEFAULT);
    }
}
