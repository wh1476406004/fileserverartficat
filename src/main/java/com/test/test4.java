package com.test;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.img.ImgUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;

import java.util.Date;

/**
 * @description:
 * @author: wanghai
 * @time: 2021/5/26 15:52
 */
public class test4 {
    public static void  main(String[] args) {
        System.out.println(intToHex(50));

    }
    public static String intToHex(int n) {
        StringBuffer s = new StringBuffer();
        String a;
        char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (n != 0) {
            s.append(b[n % 16]);
            n = n / 16;
        }
        a = s.reverse().toString();
        return a;
    }
}
