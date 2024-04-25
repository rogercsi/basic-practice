package group.multithread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class MultiThread {
    class Task implements Runnable {
        private LinkedBlockingQueue taskQueue = new LinkedBlockingQueue();
        private AtomicBoolean running = new AtomicBoolean(true);

        public void submitTask(Object task) throws InterruptedException {
            taskQueue.put(task);
        }

        @Override
        public void run() {
            while (running.get()) {
                try {
                    Object task = taskQueue.take(); // 如果没有任务，会使线程阻塞，一旦有任务，会被唤醒
                    doSomething(task);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }

        public void shutdown() {
            if (running.compareAndSet(true, false)) {
                System.out.println(Thread.currentThread() + " is stoped");
            }
        }

        private void doSomething(Object task) throws InterruptedException {
            System.err.println(task);
            Thread.sleep(500);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Create an instance of MultiThread
        MultiThread multiThread = new MultiThread();
        // Now create an instance of Task within the MultiThread context
        Task task = multiThread.new Task();
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        task.submitTask("task1");
        thread.start();
        for (int i = 0; i < 5; i++) {
            task.submitTask(i);
            // if (i == 3) {
            //     task.shutdown();
            // }
        }
        int cycle = 1;
        // Wait for all tasks to finish before exiting
        while (task.taskQueue.size() > 0) {
            System.out.println("wait cycle " + cycle++);
            Thread.sleep(100); // adjust sleep time as needed
        }
    }
}
