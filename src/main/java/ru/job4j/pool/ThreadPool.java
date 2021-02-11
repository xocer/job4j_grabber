package ru.job4j.pool;

import ru.job4j.concurrent.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<PoolThreadRunnable> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks;

    public ThreadPool(int maxOfTasks) {
        tasks = new SimpleBlockingQueue<>(maxOfTasks);
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            PoolThreadRunnable poolThreadRunnable = new PoolThreadRunnable(tasks);
            threads.add(poolThreadRunnable);
        }

        for (PoolThreadRunnable runnable : threads) {
            new Thread(runnable).start();
        }
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        threads.forEach(PoolThreadRunnable::doStop);
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool(5);

        for (int i = 0; i < 10; i++) {
            int taskNo = i;
            threadPool.work(() -> {
                String message =
                        Thread.currentThread().getName()
                                + ": Task " + taskNo;
                System.out.println(message);
            });
        }
    }
}
