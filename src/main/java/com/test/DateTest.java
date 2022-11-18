package com.test;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @description:
 * @author: GooGoo
 * @time: 2022/3/24 9:15
 */
public class DateTest {
    public static boolean compare() {
        Date date = new Date(2022, Calendar.APRIL, 1);
        Date now = new Date();
        return now.after(date);
    }

    public static void main(String[] args) {
//        String str = " WIREMAN_DAY+WIREMAN_PAY+ELECTROWELDING_DAY+ELECTROWELDING_PAY+ONDUTY_PAY+BASE_PAY+OTHER_PAY+TWO_BASE_PAY+TWO_ONDUTY_MONEY+ TWO_OTHER_PAY + TWO_OTHER_DEDUCT";
//        String[] split = str.split("+");
//        Arrays.stream(split).forEach(s -> s = "record.data." + s);
//        System.out.println(split);
//        System.out.println(new DateTime().year());
        System.out.println(Convert.digitToChinese(2766));

    }

    //获取当前4个季度
    public static List<String> getLatest4Quater(LocalDate date, int num){
        List<String> quaterList = new ArrayList<>();
        for(int i = 0;i <= num-1; i++){
            LocalDate localDate = date.minusMonths(3*i);
            String year = localDate.toString().substring(0,4);
            String monthstr = localDate.toString().substring(5, 7);
            int quaterNum = ((Integer.valueOf(monthstr)+2)/3);
            String quater = year + "Q" + quaterNum;
            quaterList.add(quater);
        }
        return quaterList;
    }
}
