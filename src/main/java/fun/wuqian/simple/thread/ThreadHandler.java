package fun.wuqian.simple.thread;

/**
 * TODO
 * <p>
 * author：悟纤「公众号SpringBoot」
 * date：2023/3/7
 * slogan：大道至简 悟在天成
 */
public abstract class ThreadHandler<T> {
    private  T t;
    public ThreadHandler(T t){
        this.t = t;
    }

    public ThreadHandler(){

    }

    protected T getT(){
        return this.t;
    }

    public abstract void handler(T t);
}
