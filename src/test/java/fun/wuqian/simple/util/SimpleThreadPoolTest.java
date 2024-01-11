package fun.wuqian.simple.util;

import fun.wuqian.simple.thread.SimpleThreadPool;
import fun.wuqian.simple.thread.ThreadHandler;

/**
 * author：悟纤「公众号SpringBoot」
 * date：2024/1/11
 */
public class SimpleThreadPoolTest {

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            SimpleThreadPool.getInstance().excute(new ThreadHandler("hello-"+i) {
                @Override
                public void handler(Object o) {
                    System.out.println(o);
                    //System.out.println(SimpleThreadPool.getInstance().getActiveCount());
                }
            });
        }
    }

}
