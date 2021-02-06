package ru.job4j.concurrent;

import java.util.concurrent.atomic.AtomicBoolean;

public class ParallelSearch {
    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>(5);
        AtomicBoolean flag = new AtomicBoolean(true);
        final Thread consumer = new Thread(
                () -> {
                    while (flag.get()) {
                        if (queue.getCount() > 0) {
                            System.out.println(queue.poll());
                        }
                    }
                }
        );
        consumer.start();
        new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        queue.offer(index);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    flag.set(false);
                }

        ).start();
    }
}
