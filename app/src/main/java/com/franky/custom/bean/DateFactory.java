package com.franky.custom.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/1/13.
 * 生成某年所有的日期
 */

public class DateFactory {

    /**平年map，对应月份和天数**/
    private static HashMap<Integer,Integer> monthMap = new LinkedHashMap<>(12);
    /**闰年map，对应月份和天数**/
    private static HashMap<Integer,Integer> leapMonthMap = new LinkedHashMap<>(12);

    static {
        //初始化map,只有2月份不同
        monthMap.put(1,31);leapMonthMap.put(1,31);
        monthMap.put(2,28);leapMonthMap.put(2,29);
        monthMap.put(3,31);leapMonthMap.put(3,31);
        monthMap.put(4,30);leapMonthMap.put(4,30);
        monthMap.put(5,31);leapMonthMap.put(5,31);
        monthMap.put(6,30);leapMonthMap.put(6,30);
        monthMap.put(7,31);leapMonthMap.put(7,31);
        monthMap.put(8,31);leapMonthMap.put(8,31);
        monthMap.put(9,30);leapMonthMap.put(9,30);
        monthMap.put(10,31);leapMonthMap.put(10,31);
        monthMap.put(11,30);leapMonthMap.put(11,30);
        monthMap.put(12,31);leapMonthMap.put(12,31);
    }

    /**
     * 输入年份和1月1日是周几
     * 闰年为366天,平年为365天
     * @param year    年份
     * @param weekday 该年1月1日为周几
     * @return 该年1月1日到12月31日所有的天数
     */
    public static List<Day> getDays(int year, int weekday) {
        List<Day> days = new ArrayList<>();
        boolean isLeapYear = isLeapYear(year);
        int dayNum = isLeapYear ? 366 : 365;
        Day day;
        int lastWeekday = weekday;
        for (int i = 1; i <= dayNum; i++) {
            day = new Day();
            day.year = year;
            //计算当天为周几,如果大于7就重置1
            day.week = lastWeekday<= 7 ? lastWeekday : 1;
            //计算当天为几月几号
            int[] monthAndDay = getMonthAndDay(isLeapYear, i);
            day.month = monthAndDay[0];
            day.date = monthAndDay[1];
            //记录下昨天是周几并+1
            lastWeekday = day.week;
            lastWeekday++;
            days.add(day);
        }
        checkDays(days);
        return days;
    }


    /**
     * 获取月和日
     * @param isLeapYear 是否闰年
     * @param currentDay 当前天数
     * @return 包含月和天的数组
     */
    public static int[] getMonthAndDay(boolean isLeapYear,int currentDay) {
        HashMap<Integer,Integer> maps = isLeapYear?leapMonthMap:monthMap;
        Set<Map.Entry<Integer,Integer>> set = maps.entrySet();
        int count = 0;
        Map.Entry<Integer, Integer> month = null;
        for (Map.Entry<Integer, Integer> entry : set) {
            count+=entry.getValue();
            if (currentDay<=count){
                month = entry;
                break;
            }
        }
        if (month == null){
            throw new IllegalStateException("未找到所在的月份");
        }
        int day = month.getValue()-(count-currentDay);
        return new int[]{month.getKey(),day};
    }


    /**
     * 判断是闰年还是平年
     * @param year 年份
     * @return true 为闰年
     */
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    /**
     * 检测生成的天数是否正常
     * @param days
     */
    private static void checkDays(List<Day> days) {
        if (days == null) {
            throw new IllegalArgumentException("天数为空");
        }
        if (days.size() != 365 && days.size() != 366) {
            throw new IllegalArgumentException("天数异常:" + days.size());
        }
    }

    public static void main(String[] args){
        //test
        List<Day> days = DateFactory.getDays(2016, 5);
        for (int i = 0; i < days.size(); i++) {
            System.out.println(days.get(i).toString());
        }
    }

}
