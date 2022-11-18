package com.test;
 
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
 
/**
 *
 * 获取两个日期之间的所有周四 并计算每个周四在当月是第几周
 * @author lzw
 * @Date 2019年3月6日
 */
public class DateUtil {
 
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String week = "星期日";
        List<String> test = getWeekly("2020-10-01 14:19:29","2021-04-30 00:00:00",week);
        System.err.println(test);
 
    }
 
 
    public static boolean weekdayOrNot(String date,String week) throws ParseException{
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = sdf.parse(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        String currSun = simpleDateFormat.format(currentDate);
        //判断当前是星期几
        if (currSun.equals(week)) {
            return true;
        }
        return false;
    }
 
 
    public static String getWeek(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        String substring = format.substring(0, 7);
        int number = calendar.get(Calendar.WEEK_OF_MONTH);
        String week = substring + "-0" + number;
        return week;
    }
 
    /**
     * 根据开始时间和结束时间计算之间的星期
     * @param beginDate
     * @param endDate
     * @return
     * @throws Exception
     */
    public static List<String> getWeekly(String beginDate,String endDate,String week) throws Exception{
        //获取俩个日期之间的日期
        List<String> list = findDates(beginDate, endDate);
        List<String> weekDateList = new ArrayList<>();
        //遍历
        for (String date : list) {
            //判断当前是星期几
            boolean thursday = weekdayOrNot(date,week);
            if (thursday==true) {
                weekDateList.add(date);
            }
 
        }
        return weekDateList;
    }
 
    public static List<String> findDates(String dBegin, String dEnd) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(format.parse(dBegin));
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(format.parse(dEnd));
        List<String> Datelist = new ArrayList<String>();
        while (format.parse(dEnd).after(calBegin.getTime())) {
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            Datelist.add(format.format(calBegin.getTime()));
        }
        Datelist.add(dBegin);
        return Datelist;
    }
 
    /**
     * 日期转星期
     *
     * @param datetime
     * @return
     */
    public static String currentDataToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        // 获得一个日历
        Calendar cal = Calendar.getInstance();
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 指示一个星期中的某天。
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
}