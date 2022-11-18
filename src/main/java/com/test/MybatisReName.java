package com.test;

/**
 * @description:
 * @author: wanghai
 * @time: 2021/5/28 14:30
 */
public class MybatisReName {
    public static void main(String[] args) {
//        String str = " SHIP_NAME_CN, SHIP_NAME_EN, CARGO_NAME, CARGO_VOLUME, ATA, ATB, ATD, BERTH, MMSI,\n" +
//                "                                CREATED_ON, CREATED_BY, UPDATED_ON, UPDATED_BY";
//        String[] split = str.split(",");
//        StringBuilder sb = new StringBuilder();
//        for (String s : split) {
//            sb.append("#{"+s.trim()+"},\n");
//        }
//        System.out.println(sb.substring(0,sb.lastIndexOf(",")));
        update();

    }


    public static void update(){
        String str = " ID, DRIVER_CARD, CNTR, CNTR_SEAL_NO, CNTR_ISO, CARGO_PIECES, CARGO_GROSS_WGT,\n" +
                "                                      DRIVER, TOOL_NO, MOBILE, NOTES";
        String[] split = str.split(",");
        StringBuilder sb = new StringBuilder();
        for (String s : split) {
            sb.append("set "+s.trim()+" = #{"+s.trim()+"},\n");
        }
        System.out.println(sb.substring(0,sb.lastIndexOf(",")));
    }
}
