package com.test;


import java.util.List;

import static com.test.DateUtil.getWeekly;

/**
 * @description:
 * @author: GooGoo
 * @time: 2021/7/18 17:28
 */
public class test10 {
    public static void main(String[] args) throws Exception {
        String week = "星期日";
        List<String> test = getWeekly("2020-10-01 14:19:29","2021-04-30 00:00:00",week);
        for (int i = 0; i <test.size() ; i++) {
//            if (i%2==0){
//                System.out.println(i);
//            }
            if (i%3==0){
                System.out.println(i);
            }
        }
    }
}
