package fun.wuqian.simple.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * TODO
 * <p>
 * author：悟纤「公众号SpringBoot」
 * date：2023/3/7
 * slogan：大道至简 悟在天成
 */
public class SimpleThreadPool {
    private static volatile SimpleThreadPool instance = null;
    private int THREAD_COUNT = 15;//下面会进行计算.这里给出的是1核的.
    private static ThreadPoolExecutor executorService = null;

    ///////////////////init instance//////////////
    private SimpleThreadPool() {
        initExecutorService();
    }
    private void initExecutorService(){
        DefaultThreadFactory factoryBuilder  = new DefaultThreadFactory("Simple");
        //获取的是逻辑核数，比如：物理核数是6，逻辑核数是12，那么这里的值就是12.
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        //理论值是25，这里设置小一点.
        THREAD_COUNT = availableProcessors*THREAD_COUNT;
        executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(THREAD_COUNT,factoryBuilder);
    }

    public static SimpleThreadPool getInstance(){
        if(instance == null){
            synchronized(SimpleThreadPool.class){
                if(instance == null){
                    instance = new SimpleThreadPool();
                }
            }
        }
        return instance;
    }

    public void excute(ThreadHandler threadHandler){
        if(!executorService.isShutdown()){
            executorService.execute(new MyThread(threadHandler));
//            //不限制线程，那么就执行执行，限制的话，需要先获取许可.
//            if(isPermitExcute(t)){
//                executorService.execute(new MyThread(t));
//            }
        }
    }

    private class MyThread extends Thread{
        private ThreadHandler threadHandler;
        public MyThread(ThreadHandler threadHandler) {
            this.threadHandler = threadHandler;
        }
        @Override
        public void run() {
            threadHandler.handler(threadHandler.getT());

//			System.err.println("start---"+this.getName()+"--"+this+"-----"+t+"-----"+SimpleDateAndTimeHelper.Current.get_yMdHms());

            //invoke the abstract method 'doInBackground'
            //long startTime = System.currentTimeMillis();
            //doInBackground(t);
            //long endTime = System.currentTimeMillis();
            //System.out.println("Task time:"+(endTime-startTime)+"MS");
            //release permit.
            //releasePermit(t);

            //run finish.
            //runFinish(t,startTime,endTime);
            //System.err.println("end---"+this.getName()+"--"+this+"-----"+t+"-----"+SimpleDateAndTimeHelper.Current.get_yMdHms());
        }
    }


    public void printCount(String action){
        //将exec转换为ThreadPoolExecutor,ThreadPoolExecutor有方法 getActiveCount()可以得到当前活动线程数
        // ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)executorService;
        StringBuffer sb = new StringBuffer(action+"-->");
        sb.append("TaskCount(任务数[包括完成和正在执行的])=").append(executorService.getTaskCount());
        sb.append(",CompletedTaskCount(线程池在运行过程中已完成的任务数)=").append(executorService.getCompletedTaskCount());
        sb.append(",ActiveCount(获取活动的线程的数量)=").append(executorService.getActiveCount());
        sb.append(",QueueSize(队列中的线程数)=").append(executorService.getQueue().size());
        System.out.println(sb);
    }

    public void printCount(){
       this.printCount("pool");
    }

    public void printCount(String action,int completedTaskCount){
        if(executorService.getCompletedTaskCount()%completedTaskCount == 0){
            this.printCount(action);
        }
    }

    /**
     * 获取executorService
     * @return
     */
    public  ThreadPoolExecutor getExecutorService() {
        return executorService;
    }

    /**
     * 获取活动的线程的数量
     * @return
     */
    public long getActiveCount(){
        return executorService.getActiveCount();
    }

    /**
     * 任务数[包括完成和正在执行的]
     * @return
     */
    public long getTaskCount(){
        return executorService.getTaskCount();
    }

    /**
     * QueueSize(队列中的线程数)
     * @return 队列中的线程数
     */
    public long getQueueCount(){
        return this.executorService.getQueue().size();
    }


//    public static void main(String[] args) {
//        for(int i=0;i<10;i++){
//            SimpleThreadPool.getInstance().excute(new ThreadHandler("hello-"+i) {
//                @Override
//                public void handler(Object o) {
//                    System.out.println(o);
//                }
//            });
//        }
//    }

}
