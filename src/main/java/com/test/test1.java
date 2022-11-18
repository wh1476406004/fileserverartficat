package com.test;

import cn.hutool.core.lang.ObjectId;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @description:
 * @author: wanghai
 * @time: 2021/4/6 20:04
 */
public class test1 {

    public static void main(String[] args) {
        System.out.println(ObjectId.next(true));
        String str  = "LONGITUDE\n" +
                " LATITUDE\n" +
                " SPEED\n" +
                " DIRECTION\n" +
                " LATEST_TIME\n" +
                " ONLINE_FLAG\n" +
                " FIRE_FLAG\n" +
                " STATE\n" +
                " TENANCY_ID\n" +
                " CREATED_BY\n" +
                " CREATED_ON\n" +
                " UPDATED_BY\n" +
                " UPDATED_ON\n" +
                " MILEAGE\n" +
                " SEND_TIME";

        String[] split = str.split("\n");
        System.out.println(split.length);
        StringBuilder sb = new StringBuilder();
//        System.out.println(split);
        for (String s : split) {
            sb.append(s).append(" = values(").append(s).append(" ),");
        }
        String result = sb.substring(0,sb.length()-1);
        System.out.println(result);

        String str1 = "select id,\n" +
                "       truck_id,\n" +
                "       start_time,\n" +
                "       end_time,\n" +
                "       take_place,\n" +
                "       send_place,\n" +
                "       tenancy_id,\n" +
                "       created_by,\n" +
                "       created_on,\n" +
                "       updated_by,\n" +
                "       updated_on,\n" +
                "       second_take_place,\n" +
                "       send_interval,\n" +
                "       is_raw_line,\n" +
                "       take_point,\n" +
                "       send_point,\n" +
                "       second_take_point,\n" +
                "       plan_start_flag,\n" +
                "       take_time,\n" +
                "       plan_name,\n" +
                "       duration,\n" +
                "       return_time,\n" +
                "       unloading_time\n" +
                "from truck_route_virtual\n" +
                "where 1=1";
        System.out.println(str1.toUpperCase());
//        Optional.ofNullable()
    }

}
