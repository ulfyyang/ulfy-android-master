package com.ulfy.master.domain.entity;

import com.ulfy.android.download_manager.DownloadTaskInfo;
import com.ulfy.master.infrastructure.encryption.MD5Utils;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.Serializable;

/**
 * 下载管理器管理的实体对象，需要实现DownloadTaskInfo和Serializable接口
 */
public class DownloadMovie implements DownloadTaskInfo, Serializable {
    private static final long serialVersionUID = 3454042710537369581L;
    public String name;
    public String downloadLink;

    public DownloadMovie(String name, String downloadLink) {
        this.name = name;
        this.downloadLink = downloadLink;
    }

    /**
     * 指定唯一标识
     *      该方法用于确定信息的唯一性，通常可考虑使用id或者下载链接
     */
    @Override public String getUniquelyIdentifies() {
        return name;
    }

    /**
     * 提供模块下载文件的下载链接地址
     */
    @Override public String provideDownloadFileLink() {
        return downloadLink;
    }

    /**
     * 提供保存文件时的文件名
     *      文件名要有唯一性，通常使用id或使用下载链接的md5处理
     *      文件名不能太长，因为系统有最长文件名255的限制
     */
    @Override public String provideDownloadFileName() {
        String ext = FilenameUtils.getExtension(new File(downloadLink).getName());
        if (ext.length() == 0) {    // 如果没有后缀，则可根据业务需求设置一个默认的后缀
            ext = ".mp4";
        }
        if (name.length() > 255 - ext.length()) {   // 如果文件名超过了255则缩短，这里使用md5
            try {
                name = MD5Utils.encode(name);
            } catch (Exception e) {                 // 如果编码失败则直接截取
                e.printStackTrace();
                name = name.substring(0, 255 - ext.length());
            }
        }
        return name + ext;
    }
}
