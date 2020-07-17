package com.ulfy.master.infrastructure.encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加密工具
 *      已验证，没有标注已验证的表示还没有验证其正确性
 */
public class AESUtils {
    /*
    注意：
        1） pc版java不支持PKCS7Padding模式，需要下载补丁包
        2） php的可以设置多余16位iv，但是多余的部分会被截掉，不足的部分会补0
        3） 该类已经对iv进行了处理，采用和php相同的方案
        4） 不要使用String类进行包装，其会对非ascii字符进行处理导致字节数组发生变化
     */

    /*
    ==================================== aes-128/256-cfb ===========================================
     */

    /**
     * AES-128-CBC加密
     * @param content  加密的内容
     * @param password 秘钥
     * @param iv       偏移量 长度必须是16位
     */
    public static String encryptAES128CBC(String content, byte[] password, byte[] iv) throws Exception {
        return encryptAESCBC(content, password, iv, 128);
    }

    /**
     * AES-256-CBC加密
     * @param content  加密的内容
     * @param password 秘钥
     * @param iv       偏移量 长度必须是16位
     */
    public static String encryptAES256CBC(String content, byte[] password, byte[] iv) throws Exception {
        return encryptAESCBC(content, password, iv, 256);
    }

    /**
     * AES-CBC加密
     * @param content  加密的内容
     * @param password 秘钥
     * @param iv       偏移量 长度必须是16位
     * @param lenth    加密的长度，只支持128和256
     */
    private static String encryptAESCBC(String content, byte[] password, byte[] iv, int lenth) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(correctPasswordIfNeed(password, lenth), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(correctIvIfNeed(iv));
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] contentBytes = content.getBytes();
        byte[] resultBytes = cipher.doFinal(contentBytes);
        return Base64Utils.encode(resultBytes);
    }

    /**
     * AES-128-CBC解密
     * @param content  解密的内容
     * @param password 秘钥
     * @param iv       偏移量 长度必须是16位
     */
    public static String decryptAES128CBC(String content, byte[] password, byte[] iv) throws Exception {
        return decryptAESCBC(content, password, iv, 128);
    }

    /**
     * AES-256-CBC解密
     * @param content  解密的内容
     * @param password 秘钥
     * @param iv       偏移量 长度必须是16位
     */
    public static String decryptAES256CBC(String content, byte[] password, byte[] iv) throws Exception {
        return decryptAESCBC(content, password, iv, 256);
    }

    /**
     * AES-CBC解密
     * @param content  解密的内容
     * @param password 秘钥
     * @param iv       偏移量 长度必须是16位
     * @param lenth    加密的长度，只支持128和256
     */
    private static String decryptAESCBC(String content, byte[] password, byte[] iv, int lenth) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(correctPasswordIfNeed(password, lenth), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(correctIvIfNeed(iv));
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] contentBytes = Base64Utils.decode(content);
        byte[] resultBytes = cipher.doFinal(contentBytes);
        return new String(resultBytes);
    }

    /**
     * 生成秘钥对应的Key字节数组
     * @param password  秘钥字符串
     * @param length    加密位数
     */
    private static byte[] correctPasswordIfNeed(byte[] password, int length) {
        // 加密位数为128时字节数组为16位 加密位数为256时字节数组为32位
        if ((length == 128 && password.length != 16) || (length == 256 && password.length != 32)) {
            byte[] correctPassword = new byte[length == 128 ? 16 : 32];
            // 循环设置每位的数值，没有的初始值为默认值0
            for (int i = 0; i < correctPassword.length && i < password.length; i++) {
                correctPassword[i] = password[i];
            }
            password = correctPassword;
        }
        return password;
    }

    /**
     * 当iv长度为16位 多余的部分会被截掉，不足的部分会补0
     */
    private static byte[] correctIvIfNeed(byte[] iv) {
        // iv 必须时16位的
        if (iv.length != 16) {
            byte[] correctIv = new byte[16];
            // 循环设置每位的数值，没有的初始值为默认值0
            for (int i = 0; i < correctIv.length && i < iv.length; i++) {
                correctIv[i] = iv[i];
            }
            iv = correctIv;
        }
        return iv;
    }
}
