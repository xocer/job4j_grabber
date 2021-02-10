package ru.job4j.pool;

import ru.job4j.concurrent.SimpleBlockingQueue;

public class PoolThreadRunnable implements Runnable {
    private SimpleBlockingQueue<Runnable> tasks = null;
    private boolean isStopped = false;

    public PoolThreadRunnable(SimpleBlockingQueue tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        while (!isStopped) {
            try {
                Runnable runnable = tasks.poll();
                runnable.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void doStop() {
        isStopped = true;
    }
}
