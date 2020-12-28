package multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomThreadPoolExecutor {

    private BlockingQueue<Runnable> blockingQueue;
    private List<WorkerThread> workerThreads;

    public CustomThreadPoolExecutor(int poolSize) {
        blockingQueue = new LinkedBlockingQueue<>();
        workerThreads = new ArrayList<>(poolSize);
        for (int i = 0; i < poolSize; ++i) {
            WorkerThread wt = new WorkerThread("Custom Pool Thread " + ++i);
            wt.start();
            workerThreads.add(wt);
        }
    }

    private void submitTask(Runnable r) {
        try {
            blockingQueue.put(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class WorkerThread extends Thread {
        private String threadName;
        public WorkerThread(String name) {
            super(name);
            threadName = name;
        }
        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("current thread :- " + threadName);
                    blockingQueue.take().run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        CustomThreadPoolExecutor ctpe = new CustomThreadPoolExecutor(2);
        ctpe.submitTask(() -> {
            System.out.println("Task 1");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        ctpe.submitTask(() -> System.out.println("Task 2"));
    }
}
