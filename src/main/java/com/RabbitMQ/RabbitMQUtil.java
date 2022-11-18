package com.RabbitMQ;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.ReUtil;
import com.google.common.collect.Sets;

import javax.swing.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description:
 * @author: wanghai
 * @time: 2021/5/17 10:08
 */
public class RabbitMQUtil {

    public static void main(String[] args) {
        checkServer();
    }

    public static void checkServer() {

//        DateTime dateTime = DateUtil.offsetHour(DateUtil.date(), 9);
//        System.out.println(DateUtil.between(DateUtil.date(), dateTime, DateUnit.HOUR) % 3);
//        List<Integer> list = ListUtil.list(false);
//        for (int i = 1; i <= 100; i++) {
//            list.add(i);
//        }
//
//        int five = list.size() / 20;
//        List<Object> deleteList = ListUtil.list(true);
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i) % five != 0) {
//                deleteList.add(i);
//            }
//        }
//        list.removeAll(deleteList);
//        System.out.println(list);

//        System.out.println(new Date().getTime() - DateUtil.offsetMinute(new Date(),-1).getTime());

//        BigDecimal divide = new BigDecimal(10).divide(new BigDecimal(3),2, RoundingMode.HALF_UP);
//        System.out.println(divide.compareTo(new BigDecimal("3.33")));

    }
}
