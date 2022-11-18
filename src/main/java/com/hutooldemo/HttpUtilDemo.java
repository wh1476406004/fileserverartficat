package com.hutooldemo;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpUtil;

import java.net.InetAddress;
import java.util.HashMap;

/**
 * @description:
 * @author: wanghai
 * @time: 2021/5/24 20:30
 */
public class HttpUtilDemo {
    public static void main(String[] args) {
        // 最简单的HTTP请求，可以自动通过header等信息判断编码，不区分HTTP和HTTPS
        String result1= HttpUtil.get("https://www.baidu.com");
        System.out.println(result1);

        // 当无法识别页面编码的时候，可以自定义请求页面的编码
        String result2= HttpUtil.get("https://www.baidu.com", CharsetUtil.CHARSET_UTF_8);

        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("city", "北京");

        String result3= HttpUtil.get("https://www.baidu.com", paramMap);


        //post

        HashMap<String, Object> paramMaps = new HashMap<>();
        paramMap.put("city", "北京");

        String result= HttpUtil.post("https://www.baidu.com", paramMaps);

        //文件上传
        HashMap<String, Object> FileMap = new HashMap<>();
        //文件上传只需将参数中的键指定（默认file），值设为文件对象即可，对于使用者来说，文件上传与普通表单提交并无区别
        FileMap.put("file", FileUtil.file("D:\\face.jpg"));

        String Files= HttpUtil.post("https://www.baidu.com", FileMap);

        //文件下载
        String fileUrl = "http://mirrors.sohu.com/centos/7.3.1611/isos/x86_64/CentOS-7-x86_64-DVD-1611.iso";

        //将文件下载后保存在E盘，返回结果为下载文件大小
        long size = HttpUtil.downloadFile(fileUrl, FileUtil.file("D:/"));
        System.out.println("Download size: " + size);
    }


    public String getLocalHost(){
        InetAddress localhost = NetUtil.getLocalhost();
        System.out.println(localhost);
        return localhost.getHostAddress();
    }
}
