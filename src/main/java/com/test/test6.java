package com.test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.TypeUtils;
import com.eee.SeasonEnum;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: wanghai
 * @time: 2021/6/4 11:53
 */
public class test6 {
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
//        String  str  ="货主,计划单号,日期,货名,仓库名称,箱号1,箱号2,封条号1,封条号2,箱重,车号,皮重,毛重,净重,备注,条形码,流水编号,核销人,条款,库房,堆位,计划件数,计划吨重";
//        String[] split = str.split(",");
//        System.out.println(split.length);

//        StringBuilder sb = new StringBuilder();
//        for (int i = 1; i < 20; i++) {
//            sb.append("            when '"+i+"' then\n" +
//                    "                concat(t.FEE_ELE"+i+",'/柜')");
//        }
//        System.out.println(sb);


        List<String> strings = new ArrayList<>();
        strings.add("123");
        strings.add("321");
        System.out.println(strings.indexOf("123"));
    }
}
