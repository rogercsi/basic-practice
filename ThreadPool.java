import java.time.LocalDateTime;
import java.util.concurrent.*;

public class ThreadPool {
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    static ExecutorService workStealingPool = Executors.newWorkStealingPool(1);

    void submit() {
        fixedThreadPool.submit(new Runnable() {
            public void run() {
                System.out.println("hello");
            }
        });
    }

    static void schedule() {
        System.out.println("start schedule " + LocalDateTime.now());
        scheduledExecutorService.schedule(new Runnable() {
            public void run() {
                System.out.println("hello"+ LocalDateTime.now());
            }
        },2000, TimeUnit.MILLISECONDS);
        System.out.println("schedule done" + LocalDateTime.now());
    }

    public static void main(String[] args) {
        schedule();
    }
}
