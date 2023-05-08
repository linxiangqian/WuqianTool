package fun.wuqian.simple;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

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
     *
     * @param str 字符串
     * @return
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
}
