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

    public synchronized void offer(T value) {
        while (queue.size() == size) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.offer(value);
        count++;
        notify();
    }

    public synchronized T poll() {
        T tmp;
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
                queue.offer(random.nextInt(50));
            }
        });

        Thread consume = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(queue.poll());
            }
        });

        produce.start();
        consume.start();
    }
}