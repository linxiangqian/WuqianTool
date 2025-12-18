package fun.wuqian.simple.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;

/**
 * 常用的工具类
 * author：悟纤「公众号SpringBoot」
 * date：2023/4/20
 */
public class SimpleUtil {


    /**
     * 是否Null or Empty - 支持多个对象.
     * @param objs 要检验的参数值
     * @return 如果有为null或者空字符的话，就返回true，否则返回false.
     */
    public static boolean isNullOrEmpty(Object... objs) {
        for (Object obj : objs) {
            if (obj == null || obj.toString().trim().isEmpty() ) {
                return true;
            }
        }
        return false;
    }


    /**
     * 是否Null or Empty.支持多个对象.
     *
     * @param objs 对象数组
     * @return 当所有的字段都不为null/empty的时候，才会返回true.
     */
    public static boolean isNotNullAndEmpty(Object... objs) {
        int size = 0;
        for (Object obj : objs) {
            if (obj != null && !obj.toString().trim().isEmpty()  ) {
                size++;
            }
        }
        boolean flag = false;
        if(size == objs.length){
            return true;
        }
        return flag;
    }

    /**
     * 当其中有字段不为null或者空的情况下，就会返回true
     * @param objs objects
     * @return 当有一个字段不为null或者空的情况下，就会返回true.
     */
    public static boolean isNotNullAndEmptyAnyRtn(Object... objs) {
        boolean b = false;
        for (Object obj : objs) {
            if ( obj != null && !obj.toString().trim().isEmpty() ) {
                b = true;
                break;
            }
        }
        return b;
    }

    /**
     * 是否包等于某些数据，包含返回true.
     * @param  source object
     * @param targets 待比较的数据
     * @return  true-包含了，false-全部未包含
     */
    public static  boolean isEqualsOr(String source, String... targets) {
        boolean b = false;
        if(source != null){
            for (String str : targets) {
                if (source.equals(str)) {
                    b = true;
                    break;
                }
            }
        }
        return b;
    }

    /**
     * 是否包含某数据，包含返回true.
     * @param source 比对数据源
     * @param targets 比对的数据信息。
     * @return true:包含；false：未包含
     */
    public static boolean isContains(String source, String... targets) {
        boolean b = false;
        if(source != null){
            for (String str : targets) {
                if (source.contains(str)) {
                    b = true;
                    break;
                }
            }
        }
        return b;
    }

    /**
     * 获取随机数[包括开始，包括结束.]. [startNumber,endNumber]
     *
     * @param startNumber  开始
     * @param endNumber  结束
     * @return 随机数
     */
    public static int rand(int startNumber, int endNumber) {
        int rand = startNumber + new Random().nextInt(endNumber - startNumber +1);
        return rand;
    }

    /**
     * 获取随机数[包括开始，包括结束.]. [startNumber,endNumber]
     *
     * @param startNumber 开始
     * @param endNumber 结束
     * @return  随机数
     */
    public static long randLong(long startNumber, long endNumber) {
        long rand = startNumber +  (long)(Math.random()*(endNumber-startNumber+1));
        return rand;
    }

    /***
     * [0123456789]产生随机数
     * 举例说明：count=4,返回值的结果形如：6124
     * @param count 生成位数
     * @return 生成的随机数
     */
    public static String randNumber(int count) {
        StringBuffer sRand = new StringBuffer();
        Random random = new Random();
        String codeList = "0123456789";
        for (int i = 0; i < count; i++) {
            int a = random.nextInt(codeList.length() - 1);
            String rand = codeList.substring(a, a + 1);
            sRand.append(rand);
        }
        return sRand.toString();
    }


    /**
     * md5加密，32位加密方式.
     * @param str 字符串
     * @return md5之后的字符串
     */
    public static String md5(String str) {
        return md5For32(str);
    }


    /**
     * 对字符串进行 MD5 加密(32加密)
     *
     * @param str 要加密的字符串.
     * @return 返回md5
     */
    public static String md5For32(String str) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };// 用来将字节转换成
        // 16
        // 进制表示的字符
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes("UTF8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        byte[] encodedValue = md5.digest();
        int j = encodedValue.length;
        char finalValue[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte encoded = encodedValue[i];
            finalValue[k++] = hexDigits[encoded >> 4 & 0xf];
            finalValue[k++] = hexDigits[encoded & 0xf];
        }
        return new String(finalValue);
    }


    /**
     * 休眠几秒.
     * @param timeoutSecond 秒时间
     */
    public static void sleepSecond(long timeoutSecond) {
        sleep(timeoutSecond,TimeUnit.SECONDS);
    }

    public static void sleep(long time,TimeUnit timeUnit) {
        try {
            timeUnit.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取详细的异常信息.
     * @param e  异常对象
     * @return  返回异常具体的信息字符串信息。
      */
    public static String getExceptionMessage(Throwable e) {
        StringBuffer myException = new StringBuffer((e.getClass().getName() + ":" + e.getMessage()) + "\n");
        StackTraceElement[] strArr = e.getStackTrace();
        for (StackTraceElement str : strArr) {
            myException.append(("\t" + str.toString()) + "\n");
        }
        return myException.toString();
    }

    /**
     * 根据生日计算年龄.计算出来的是周岁
     * @param birthday 生日,支持yyyyMMdd、yyyy-MM-dd，yyyy/MM/dd,yyyy.MM.dd
     * @return 年龄
     */
    public static int birthday2Age(String birthday) {
        birthday = birthday.replaceAll("-","").replace("/","").replace(".","");
        return birthday2Age(birthday,"yyyyMMdd");
    }

    /**
     * 根据生日计算年龄.计算出来的是周岁
     *
     * @param birthday 格式为 年-月-日，例如:2013-09-06
     * @param format 格式化格式
     * @return 年龄
     */
    public static int birthday2Age(String birthday,String format) {
        if (birthday != null) {
            SimpleDateFormat myFormatter = new SimpleDateFormat(format);
            Date mydate = null;
            try {
                mydate = myFormatter.parse(birthday);
                return birthday2Age(mydate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /**
     * 根据生日计算年龄.计算出来的是周岁
     *
     * @param birthdayDate 生日日期格式.
     * @return 年龄
     */
    public static int birthday2Age(Date birthdayDate) {
        if (birthdayDate == null) {
            return 0;
        } else {
            java.util.Date date = new Date();
            java.util.Date mydate = birthdayDate;
            long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000) + 1;
            return (int) (day / 365f);
        }
    }

    /**
     * 解析el表达式： String str = "你好${name},年龄${age}";
     *
     * @param elString
     * @param map
     * @return
     */
    public static String elParse(String elString, Map<String, Object> map) {
        if(elString == null){
            return null;
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            //System.out.println(entry.getKey() +"--"+entry.getValue() );
            /*
            确实，在使用replaceAll()方法时，如果替换字符串中包含正则表达式的特殊字符，会导致替换失败或报错。为了避免这种情况，可以使用Matcher.quoteReplacement()方法来转义要替换的字符串。
             */
            //elString = elString.replaceAll("\\$\\{" + entry.getKey() + "\\}", entry.getValue() + "");
            Object value = entry.getValue();
            String replacement = (value == null) ? "" : value.toString();
            elString = elString.replaceAll("\\$\\{" + entry.getKey() + "\\}", Matcher.quoteReplacement(replacement));
        }
        return elString;
    }

    /**
     * ifNullOrEmpty("","添加","修改")="修改";
     *
     * @param field
     * @param nullOrEmptyValue
     * @param elseValue
     * @return
     */
    public static Object ifNullOrEmpty(Object field, Object nullOrEmptyValue, Object elseValue) {
        Object tmp = elseValue;
        if (isNullOrEmpty(field)) {
            tmp = nullOrEmptyValue;
        }
        return tmp;
    }

    /**
     * ifNullOrEmpty("你好","")="你好";
     *
     * @param field
     * @param nullOrEmptyValue
     * @return
     */
    public static Object ifNullOrEmpty(Object field, Object nullOrEmptyValue) {
        Object tmp = field;
        if (isNullOrEmpty(field)) {
            tmp = nullOrEmptyValue;
        }
        return tmp;
    }

}
