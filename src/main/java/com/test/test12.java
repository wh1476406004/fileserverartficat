package com.test;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.pojo.timeField;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: GooGoo
 * @time: 2022/7/6 16:21
 */
public class test12 {
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) {
        Short aShort = Convert.toShort("90");
        System.out.println(aShort);
    }
    /**
     * 根据经纬度，计算两点间的距离
     *
     * @return 返回距离 单位千米
     */
    private static double getDistance(String longitude1, String latitude1, String longitude2, String latitude2) {
        double EARTH_RADIUS = 6378.137;
        // 纬度
        double lat1 = Math.toRadians(Double.parseDouble(latitude1));
        double lat2 = Math.toRadians(Double.parseDouble(latitude2));
        // 经度
        double lng1 = Math.toRadians(Double.parseDouble(longitude1));
        double lng2 = Math.toRadians(Double.parseDouble(longitude2));
        // 纬度之差
        double a = lat1 - lat2;
        // 经度之差
        double b = lng1 - lng2;
        // 计算两点距离的公式
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(b / 2), 2)));
        // 弧长乘地球半径, 返回单位: 千米
        s = s * EARTH_RADIUS;
        return s;
    }
}
