package com.test;


import java.util.*;

/**
 * @description:
 * @author: wanghai
 * @time: 2021/7/6 14:58
 */
public class gmTest {
    public static void main(String[] args) {
        String str ="fee_ele2  ,\n" +
                "            fee_ele3  ,\n" +
                "            fee_ele4  ,\n" +
                "            fee_ele5  ,\n" +
                "            fee_ele6  ,\n" +
                "            fee_ele7  ,\n" +
                "            fee_ele8  ,\n" +
                "            fee_ele9  ,\n" +
                "            fee_ele10  ,\n" +
                "            fee_ele11  ,\n" +
                "            fee_ele12  ,\n" +
                "            fee_ele13  ,\n" +
                "            fee_ele14  ,\n" +
                "            fee_ele15  ,\n" +
                "            fee_ele16  ,\n" +
                "            fee_ele17  ,\n" +
                "            fee_ele18  ,\n" +
                "            fee_ele19  ,\n" +
                "            fee_ele20 ";
        String[] split = str.split(",");
        StringBuilder sb = new StringBuilder();
        for (String s : split) {
            sb.append("IFNULL("+s.replace("\n","").trim()+"),\n");
        }
        System.out.println(sb.toString());
    }
}
