package fun.wuqian.simple;

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
            if (obj == null || obj.toString().trim().isEmpty()) {
                return true;
            }
        }
        return false;
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

}
