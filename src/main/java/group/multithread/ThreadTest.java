package group.multithread;

public class ThreadTest {
    private static Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {System.out.println("Hello, world!");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("sleep 1s");
            synchronized (lock) {
                try {
                    lock.wait();
                    System.out.println("wait end");
                } catch ( InterruptedException ex ) {}
            }
            System.out.println("bye!");
        });
        t.start();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("sleep 2s");
        synchronized (lock) {
            lock.notify();
        }
        t.join();
        System.out.println("notify from main");
        System.out.println("main end!");
    }
}
