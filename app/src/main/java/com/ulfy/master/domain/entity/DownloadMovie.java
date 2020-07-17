package com.ulfy.master.domain.entity;

import com.ulfy.android.download_manager.DownloadTaskInfo;

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
    @Override public String provideUniquelyIdentifies() {
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
     */
    @Override public String provideDownloadFileName() {
        return name + downloadLink.substring(downloadLink.lastIndexOf('.'));
    }
}
