package com.hutooldemo;

import cn.hutool.core.lang.Console;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.swing.clipboard.ClipboardUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;

import java.awt.datatransfer.Clipboard;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.util.HashMap;

/**
 * @description:
 * @author: wanghai
 * @time: 2021/5/24 19:56
 */
public class test1 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(getLocalHost());
    }

    public static String getLocalHost(){
        InetAddress localhost = NetUtil.getLocalhost();
        return localhost.getHostAddress();
    }
}
