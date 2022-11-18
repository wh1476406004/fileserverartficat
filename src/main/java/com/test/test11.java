package com.test;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.BiMap;
import com.pojo.Person;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: GooGoo
 * @time: 2021/7/24 12:36
 */
public class test11 {
    public static void main(String[] args) {
        List<Map> list = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            HashMap<String, String> objectObjectHashMap = new HashMap<>();
            objectObjectHashMap.put("K", i + 1 + "");
            objectObjectHashMap.put("V", i + "");
            list.add(objectObjectHashMap);
        }
        long begin = System.currentTimeMillis();
        String listMapKey = getListMapKey(list, "4723");
        System.out.println(listMapKey);
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }

    public static String getListMapKey(List<Map> list, String v) {

        for (Map map : list) {
            for (Object key : map.keySet()) {
                if (v.equals(map.get(key))) {
                    return map.get("K").toString();
                }
            }
        }
        return v;
    }

    // STREAM
    public static String getListMapKeyByStream(List<Map> list, String v) {
        return list.stream().filter(map -> map.get("V").equals(v)).
                map(map -> map.get("K").toString()).findFirst().orElse(v);
    }
}
