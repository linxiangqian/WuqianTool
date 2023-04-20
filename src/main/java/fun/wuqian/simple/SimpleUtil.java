package fun.wuqian.simple;

/**
 * 常用的工具类
 * author：悟纤「公众号SpringBoot」
 * date：2023/4/20
 */
public class SimpleUtil {

    /**
     * 是否Null or Empty - 支持多个对象.
     *
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
}
