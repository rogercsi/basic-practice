import java.util.Objects;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreLimiter {
    static final Object lock = new Object();
    static int index = 0;
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(10);
        for(int i = 0; i < 100; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    doSomething(semaphore);
                }
            });
            t.start();
        }
    }
    private static  void doSomething(Semaphore semaphore) {
        boolean acquired = false;
        try {
            acquired = semaphore.tryAcquire(3000, TimeUnit.MILLISECONDS);
            if(acquired) {
                int i = SemaphoreLimiter.index;
                synchronized (SemaphoreLimiter.lock) {
                    SemaphoreLimiter.index++;
                }
                System.out.println("执行业务逻辑 index: " + i);
            } else {
                System.out.println("信号量未获取执行的逻辑");
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if(acquired) {
                semaphore.release();
            }
        }
    }
}
