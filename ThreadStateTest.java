import java.util.concurrent.locks.LockSupport;

public class ThreadStateTest {
    /**
     * not working now
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Runnable childThreadHello = new Runnable() {
            @Override
            public void run() {
                System.out.println("child thread hello");
                LockSupport.park(lock);
                System.out.println("child thread wake up");
            }
        };
        childThreadHello.run();
        Thread.sleep(1000);
        LockSupport.unpark((Thread) childThreadHello);
        System.out.println("main thread end");

        lock.wait();
    }
}
