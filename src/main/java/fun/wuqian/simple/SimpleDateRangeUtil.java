package fun.wuqian.simple;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 时间范围工具类
 *
 * author：悟纤「公众号SpringBoot」
 *
 */
public class SimpleDateRangeUtil {
    
    /**
     * 获取今天的时间范围
     * @return 返回长度为2的字符串集合，如：[2017-11-03 00:00:00, 2017-11-03 23:59:59]
     */
    public static List<String> getToday() {
        List<String> dataList = new ArrayList<>(2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.DATE, 0);
        String today = dateFormat.format(calendar.getTime());
        dataList.add(today + " 00:00:00");
        dataList.add(today + " 23:59:59");
        return dataList;
    }
    
    /**
     * 获取昨天的时间范围
     * @return 返回长度为2的字符串集合，如：[2017-11-02 00:00:00, 2017-11-02 23:59:59
     */
    public static List<String> getYesterday() {
        List<String> dataList = new ArrayList<>(2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.DATE, -1);
        String yesterday = dateFormat.format(calendar.getTime());
        dataList.add(yesterday + " 00:00:00");
        dataList.add(yesterday + " 23:59:59");
        return dataList;
    }
    
    /**
     * 获取本周的时间范围
     * @return 返回长度为2的字符串集合，如：[2017-10-30 00:00:00, 2017-11-05 23:59:59]
     */
    public static List<String> getThisWeek() {
        List<String> dataList = new ArrayList<>(2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);//设置周一为一周之内的第一天
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String monday = dateFormat.format(calendar.getTime()) + " 00:00:00";
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        String sunday = dateFormat.format(calendar.getTime()) + " 23:59:59";
        dataList.add(monday);
        dataList.add(sunday);
        return dataList;
    }
    
    /**
     * 获取本月的时间范围
     * @return 返回长度为2的字符串集合，如：[2017-11-01 00:00:00, 2017-11-30 23:59:59]
     */
    public static List<String> getThisMonth() {
        List<String> dataList = new ArrayList<>(2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String firstDayOfMonth = dateFormat.format(calendar.getTime()) + " 00:00:00";
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        String lastDayOfMonth = dateFormat.format(calendar.getTime()) + " 23:59:59";
        dataList.add(firstDayOfMonth);
        dataList.add(lastDayOfMonth);
        return dataList;
    }
    
    /**
     * 获取本年的时间范围
     * @return 返回长度为2的字符串集合，如：[2017-01-01 00:00:00, 2017-12-31 23:59:59
     */
    public static List<String> getThisYear() {
        List<String> dataList = new ArrayList<>(2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.YEAR, 0);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        String firstDayOfYear = dateFormat.format(calendar.getTime()) + " 00:00:00";
        calendar.add(Calendar.YEAR, 1);
        calendar.set(Calendar.DAY_OF_YEAR, 0);
        String lastDayOfYear = dateFormat.format(calendar.getTime()) + " 23:59:59";
        dataList.add(firstDayOfYear);
        dataList.add(lastDayOfYear);
        return dataList;
    }

    /**
     * 根据类型来获取.
     * @param type : thisDay、thisWeek、thisMonth,thisYear
     * @return
     */
    public static List<String> getByType(String type) {
        if(type == null){
            return null;
        }
        List<String> days = null;
        if ("thisDay".equals(type)) { //今日
            days = getToday();
        }else if ("thisWeek".equals(type)) { //本周
            days = getThisWeek();
        }else if ("thisMonth".equals(type)) { //本月
            days = getThisMonth();
        }else if ("thisYear".equals(type)) { //本月
            days = getThisYear();
        }
        return days;
    }
}