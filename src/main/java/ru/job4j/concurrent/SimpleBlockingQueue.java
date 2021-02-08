package ru.job4j.concurrent;

import lombok.Getter;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int size;
    @Getter
    private int count;

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() == size) {
            wait();
        }
        queue.offer(value);
        count++;
        notify();
    }

    public synchronized T poll() throws InterruptedException {
        T tmp;
        while (queue.isEmpty()) {
            wait();
        }

        tmp = queue.poll();
        count--;
        notify();
        return tmp;
    }

    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        Random random = new Random();

        Thread produce = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    queue.offer(random.nextInt(50));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consume = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    System.out.println(queue.poll());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        produce.start();
        consume.start();
    }
}