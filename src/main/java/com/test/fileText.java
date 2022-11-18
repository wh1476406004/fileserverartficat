package com.test;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @author: GooGoo
 * @time: 2021/12/29 17:21
 */
public class fileText {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String fileUrl = "http://lfs.circlelog.com/wb/upload/A74B2C4759CE8A7ABA111D92A7755002.jpg";

        //将文件下载后保存在E盘，返回结果为下载文件大小   自己创建一个文件夹 files
        long size = HttpUtil.downloadFile(fileUrl, FileUtil.file("e:/files/A74B2C4759CE8A7ABA111D92A7755002.jpg"));
        System.out.println("Download size: " + size);
    }
}
