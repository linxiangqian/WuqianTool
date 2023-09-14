package fun.wuqian.simple.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期组件
 * author：悟纤「公众号SpringBoot」
 * date：2023/5/23
 */
public class SimpleDateUtil {
    public final static String HH_MM_SS = "HH:mm:ss";
    public final static String YYYY_MM_DD = "yyyy-MM-dd";
    public final static String YYYY_MM = "yyyy-MM";
    public final static String YYYY_MM_HH = "yyyy-MM-dd HH";
    public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public final static String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";


    /**
     * 基于当前日期的操作.
     */
    public static class Current{

        /**
         * 当前日期和时间.
         * @return
         */
        public static String get_yMdHms() {
            return get(SimpleDateUtil.YYYY_MM_DD_HH_MM_SS);
        }

        /**
         * 返回当前的日期.(包括时间,即没有时分秒.)
         * @param format : 日期格式, 年 = y,月 = M,日=d,时=h,24小时制=H,分=m,秒=s,毫秒,S
         * @return String : 比如： 2012-09-02 ,2012/09/02
         */
        public static String get(String format) {
            Calendar calendar = Calendar.getInstance();
            return new SimpleDateFormat(format).format(calendar.getTime());
        }


        /**
         * 基于当前的时间，获取相对的日期，
         * @param secondPlusOrMinus 如果是正数的话，就是之后的时间，如果是负数的话，那么就是之前的时间
         * @return
         */
        public  static Date getRelativeDate(int secondPlusOrMinus){
            return getRelativeDate(0,secondPlusOrMinus);
        }

        /**
         * 基于当前的时间，获取相对的日期
         * @param dayPlusOrMinus 如果是正数的话，就是之后的时间，如果是负数的话，那么就是之前的时间
         * @param secondPlusOrMinus 如果是正数的话，就是之后的时间，如果是负数的话，那么就是之前的时间
         * @return
         */
        public  static  Date getRelativeDate(int dayPlusOrMinus,int secondPlusOrMinus){
            return getRelativeDate(0,0,dayPlusOrMinus,0,0,secondPlusOrMinus,false);
        }

        /**
         * 基于当前的时间，获取相对的日期
         * @param dayPlusOrMinus 如果是正数的话，就是之后的时间，如果是负数的话，那么就是之前的时间
         * @param isCurrentEndDay 是否是当天结束时间，也就是设置为23:59:59
         * @return
         */
        public  static  Date getRelativeDate(int dayPlusOrMinus,boolean isCurrentEndDay){
            return getRelativeDate(0,0,dayPlusOrMinus,0,0,0,isCurrentEndDay);
        }

        /**
         *
         * @param yearPlusOrMinus   把日期往后增加year年.整数往后推,负数往前移动
         * @param monthPlusOrMinus  把日期往后增加month月.整数往后推,负数往前移动
         * @param dayPlusOrMinus  把日期往后增加day天.整数往后推,负数往前移动
         * @param hourPlusOrMinus 把日期往后增加hour小时.整数往后推,负数往前移动
         * @param minutePlusOrMinus  把日期往后增加minute小时.整数往后推,负数往前移动
         * @param secondPlusOrMinus 把日期往后增加SECOND 秒.整数往后推,负数往前移动
         * @param isCurrentEndDay 是否是当天结束时间，也就是设置为23:59:59
         * @return
         */
        public  static  Date getRelativeDate(int yearPlusOrMinus,int monthPlusOrMinus,int dayPlusOrMinus,int hourPlusOrMinus,int minutePlusOrMinus,int secondPlusOrMinus,boolean isCurrentEndDay){
            // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar c = new GregorianCalendar();
            Date date = new Date();
            //System.out.println("系统当前时："+df.format(date));
            c.setTime(date);//设置参数时间
            c.add(Calendar.YEAR,yearPlusOrMinus);
            c.add(Calendar.MONTH,monthPlusOrMinus);
            c.add(Calendar.DAY_OF_MONTH,dayPlusOrMinus);
            c.add(Calendar.HOUR_OF_DAY, hourPlusOrMinus);
            c.add(Calendar.MINUTE, minutePlusOrMinus);
            c.add(Calendar.SECOND,secondPlusOrMinus);//把日期往后增加SECOND 秒.整数往后推,负数往前移动
            if(isCurrentEndDay){//是否是当天结束时间，也就是设置为23:59:59
                c.set(Calendar.HOUR_OF_DAY, 23);
                c.set(Calendar.MINUTE, 59);
                c.set(Calendar.SECOND, 59);
            }
            date = c.getTime(); //这个时间就是日期往后推一天的结果
            // String str = df.format(date);
            return date;
        }

        public static Date randomDate(int beginSecond){
            return SimpleDateUtil.randomDate( getRelativeDate(-beginSecond),new Date() );
        }
    }


    /**
     * 返回当前这一天的时间
     * @return 例子：2023-03-21
     */
    public static String thisDay() {
        return Current.get(SimpleDateUtil.YYYY_MM_DD);
    }

    /**
     * 获取一年中的第几周
     * @return
     */
    public static int weekOfYear(){
        return weekOfYear(new Date());
    }

    /**
     * 获取一年中的第几周
     * @return
     */
    public static int weekOfYear(Date date){
        Calendar calendar = Calendar.getInstance();//创建一个日期实例
        calendar.setTime(date);//实例化一个日期
        return calendar.get(Calendar.WEEK_OF_YEAR);//获取是第几周
    }

    /**
     * 日期格式化.
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date,String format){
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * 根据开始时间和结束时间，生成一个随机时间.
     * @param beginDate
     * @param endDate
     * @return
     */
    public static Date randomDate(Date beginDate, Date endDate ){
        if(beginDate.getTime() >= endDate.getTime()){
            return new Date();
        }
        long date = random(beginDate.getTime(),endDate.getTime());
        return new Date(date);
    }


    /////////私有方法

    /**
     * 生成随机数.
     * @param begin
     * @param end
     * @return
     */
    private static long random(long begin,long end){
        long rtn = begin + (long)(Math.random() * (end - begin));
        if(rtn == begin || rtn == end){
            return random(begin,end);
        }
        return rtn;
    }

}
