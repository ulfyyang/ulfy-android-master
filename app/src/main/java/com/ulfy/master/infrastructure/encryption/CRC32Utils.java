package com.ulfy.master.infrastructure.encryption;

import java.io.File;
import java.io.FileInputStream;
import java.util.zip.CRC32;

/**
 * CRC32工具
 */
public class CRC32Utils {

    private CRC32Utils() {
        throw new UnsupportedOperationException("constrontor cannot be init");
    }

    /**
     * 将字符串编码为CRC32
     */
    public static long encode(String content) throws Exception {
        CRC32 crc32 = new CRC32();
        crc32.update(content.getBytes());
        return crc32.getValue();
    }

    /**
     * 将文件编码为CRC32，取文件的前32个字节
     */
    public static long encode(File file) throws Exception {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[32];
            fileInputStream.read(buffer);
            CRC32 crc32 = new CRC32();
            crc32.update(buffer);
            return crc32.getValue();
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
    }
}
