package com.ulfy.master.infrastructure.encryption;

import java.io.File;
import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

/**
 * Md5编码工具
 *      已验证，没有标注已验证的表示还没有验证其正确性
 */
public class MD5Utils {

    private MD5Utils() {
        throw new UnsupportedOperationException("constrontor cannot be init");
    }

    /**
     * 将一个字符串转化为为MD5字符串
     */
    public static String encode(String content) throws Exception {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        byte[] strTemp = content.getBytes();
        MessageDigest mdTemp = MessageDigest.getInstance("MD5");
        mdTemp.update(strTemp);
        byte tmp[] = mdTemp.digest(); 			// MD5 的计算结果是一个 128 位的长整数， 用字节表示就是 16 个字节
        char strs[] = new char[16 * 2]; 		// 每个字节用 16 进制表示的话，使用两个字符，所以表示成 16 进制需要 32 个字符
        int k = 0; 								// 表示转换结果中对应的字符位置
        for (int i = 0; i < 16; i++) { 			// 从第一个字节开始，对 MD5 的每一个字节,转换成 16 进制字符的转换
            byte byte0 = tmp[i]; 				// 取第 i 个字节
            strs[k++] = hexDigits[byte0 >>> 4 & 0xf]; 		// 取字节中高 4 位的数字转换,>>> 为逻辑右移，将符号位一起右移
            strs[k++] = hexDigits[byte0 & 0xf]; 			// 取字节中低 4 位的数字转换
        }
        return new String(strs); 				// 换后的结果转换为字符串
    }

    /**
     * 将一个文件转化为MD5
     */
    public static String encode(File file) throws Exception {
        int bufferSize = 1024;
        // 创建MD5转换器和文件流
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        FileInputStream fileInputStream = new FileInputStream(file);
        DigestInputStream digestInputStream = new DigestInputStream(fileInputStream, messageDigest);

        byte[] buffer = new byte[bufferSize];
        // DigestInputStream实际上在流处理文件时就在内部就进行了一定的处理
        while (digestInputStream.read(buffer) > 0) ;
        // 通过DigestInputStream对象得到一个最终的MessageDigest对象。
        messageDigest = digestInputStream.getMessageDigest();
        // 通过messageDigest拿到结果，也是字节数组，包含16个元素
        byte[] array = messageDigest.digest();
        // 同样，把字节数组转换成字符串
        StringBuilder hex = new StringBuilder(array.length * 2);
        for (byte b : array) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

}
