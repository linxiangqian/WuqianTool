package fun.wuqian.simple.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * author：悟纤「公众号SpringBoot」
 * date：2023/9/14
 */
public class SimpleDateUtilTest {

    @Test
    public void testParse(){
        System.out.println(SimpleDateUtil.parse("2023-11-20",SimpleDateUtil.YYYY_MM_DD));
    }

    @Test
    public void testGetRelativeDate(){
       Date date = SimpleDateUtil.Current.getRelativeDate(7,true);
       String dateStr = SimpleDateUtil.format(date,SimpleDateUtil.YYYY_MM_DD_HH_MM_SS);
       System.out.println(dateStr);
    }

}
