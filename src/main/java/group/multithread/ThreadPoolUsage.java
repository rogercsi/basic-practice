package group.multithread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolUsage {
    private volatile static AtomicInteger times = new AtomicInteger(0);
    static Object lock = new Object();
    static class Task implements Runnable {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            synchronized(ThreadPoolUsage.lock) {
                System.err.println("hello time " +times.get() + " from thread " + Thread.currentThread().getName());
                times.incrementAndGet();
            }
        }

    }
    public static void main(String[] args) {
        
        AtomicInteger threadNum = new AtomicInteger(0);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 0, TimeUnit.MILLISECONDS, 
                        new LinkedBlockingQueue<>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("pull-service-" + threadNum.incrementAndGet());
                return t;
            }
        });
        for (int i = 0; i < 1000; i++) {
            executor.submit(new Task());
        }
    }
}
