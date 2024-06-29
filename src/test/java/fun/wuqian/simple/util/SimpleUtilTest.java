package fun.wuqian.simple.util;

import fun.wuqian.simple.encode.SimpleAES_ECB_128;
import fun.wuqian.simple.util.SimpleUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * author：悟纤「公众号SpringBoot」
 * date：2023/4/20
 */
public class SimpleUtilTest {

    @Test
    public void testIsContainer(){
        Assert.assertEquals(true,SimpleUtil.isContains("http://suno4.cn","suno4.cn","noisee.com.cn")  );
        Assert.assertEquals(true,SimpleUtil.isContains("http://noisee.com.cn","suno4.cn","noisee.com.cn")  );
        Assert.assertEquals(false,SimpleUtil.isContains("http://ai.dzwlai.com","suno4.cn","noisee.com.cn")  );

    }

    @Test
    public void testBirthday2Age(){
        int age1 = SimpleUtil.birthday2Age("1988-11-29");
        int age2 = SimpleUtil.birthday2Age("1988/11/29");
        int age3 = SimpleUtil.birthday2Age("19881129");
        int age4 = SimpleUtil.birthday2Age("1988.11.29");
        System.out.println("SimpleUtilTest.testBirthday2Age->age1="+age1);
        Assert.assertEquals(true,age1==age2);
        Assert.assertEquals(true,age1==age3);
        Assert.assertEquals(true,age1==age4);
    }

    @Test
    public void testIsEqualsOr(){
        Assert.assertEquals(false,SimpleUtil.isEqualsOr("prod","test"));
        Assert.assertEquals(false,SimpleUtil.isEqualsOr("prod","test","dev"));
        Assert.assertEquals(true,SimpleUtil.isEqualsOr("prod","test","prod"));
    }

    @Test
    public void testIsNotNullOrEmpty(){
        String str1 = "", str2 = "hi";
        Assert.assertEquals(false,SimpleUtil.isNotNullAndEmpty(str1,str2));
        Assert.assertEquals(true,SimpleUtil.isNotNullAndEmptyAnyRtn(str1,str2));

        str1 = null;str2 = "hi";
        Assert.assertEquals(false,SimpleUtil.isNotNullAndEmpty(str1,str2));
        Assert.assertEquals(true,SimpleUtil.isNotNullAndEmptyAnyRtn(str1,str2));

        str1 = "str1";str2 = "str2";
        Assert.assertEquals(true,SimpleUtil.isNotNullAndEmpty(str1,str2));
        Assert.assertEquals(true,SimpleUtil.isNotNullAndEmptyAnyRtn(str1,str2));

        str1 = null;str2 = null;
        Assert.assertEquals(false,SimpleUtil.isNotNullAndEmpty(str1,str2));
        Assert.assertEquals(false,SimpleUtil.isNotNullAndEmptyAnyRtn(str1,str2));
    }

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


    @Test
    public void testGetExceptionMessage(){
       try {
           int i = 1/0;
       }catch (Exception e){
           //System.out.println(SimpleUtil.getExceptionMessage(e));
       }
    }


    @Test
    public void testAES(){
        /*
         * 此处使用AES-128-ECB加密模式，key需要为16位。
         */
        String cKey = "Y0GsnKNELsL0O0G0";
        // 需要加密的字串
        String cSrc = "BN:19999922";//BN:bnd-cpn
//	     System.out.println(cSrc);
        // 加密
        String enString = SimpleAES_ECB_128.encrypt(cKey,cSrc);
        System.out.println("加密后的字串是：" + enString);

        // 解密
        String DeString = SimpleAES_ECB_128.decrypt(cKey,enString);
        System.out.println("解密后的字串是：" + DeString);
        Assert.assertEquals(DeString,cSrc);
    }

}
