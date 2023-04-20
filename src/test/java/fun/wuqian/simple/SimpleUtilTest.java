package fun.wuqian.simple;

import org.junit.Assert;
import org.junit.Test;

/**
 * author：悟纤「公众号SpringBoot」
 * date：2023/4/20
 */
public class SimpleUtilTest {

    @Test
    public void testTsNullOrEmpty(){
        String str1 = "", str2 = "hi";
        Assert.assertEquals(true,SimpleUtil.isNullOrEmpty(str1,str2));

        str1 = null;str2 = "hi";
        Assert.assertEquals(true,SimpleUtil.isNullOrEmpty(str1,str2));

        str1 = "str1";str2 = "str2";
        Assert.assertEquals(false,SimpleUtil.isNullOrEmpty(str1,str2));
    }

    @Test
    public void testRandNumber(){
        System.out.println(SimpleUtil.randNumber(4));
    }

}
