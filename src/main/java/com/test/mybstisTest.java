package com.test;

import java.util.Date;

/**
 * @description:
 * @author: wanghai
 * @time: 2021/6/2 16:14
 */
public class mybstisTest {
    public static void main(String[] args) {
        String str = "ID, DRIVER_CARD, CNTR, CNTR_SEAL_NO, CNTR_ISO, CARGO_PIECES, CARGO_GROSS_WGT,\n" +
                "                                      DRIVER, TOOL_NO, MOBILE, NOTES";
        String[] split = str.split(",");
        StringBuilder sb = new StringBuilder();
        for (String s : split) {
            sb.append("#{" + s.trim() + ",jdbcType=VARCHAR},");
        }
        System.out.println(sb.substring(0, sb.lastIndexOf(",")));

//        String month = "2021-12";
//        Date date = new Date(month);
//        System.out.println(date);


    }
}
