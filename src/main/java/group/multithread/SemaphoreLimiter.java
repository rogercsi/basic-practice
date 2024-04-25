package group.multithread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreLimiter {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        for(int i = 0; i < 100; i++) {
            int finalI = i;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    doSomething(semaphore, finalI);
                }
            });
            t.start();
        }
    }
    private static  void doSomething(Semaphore semaphore, int index) {
        boolean acquired = false;
        try {
            acquired = semaphore.tryAcquire(1, TimeUnit.NANOSECONDS);
            if(acquired) {
                System.out.println("执行业务逻辑 index:" + index);
            } else {
                System.out.println("信号量未获取执行的逻辑 index:" + index);
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
