package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class CountBarrier {
    private final Object monitor = this;

    private final int total;
    @GuardedBy("this")
    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public synchronized void count() {
        count++;
        System.out.println(Thread.currentThread().getName() + " увеличила нить на 1 раз. Count сейчас равен " + count);
        monitor.notifyAll();
    }

    public synchronized void await() {
        while (count <= total) {
            try {
                System.out.println("Await ждет");
                monitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Метод await запущен");
    }

    public static void main(String[] args) throws InterruptedException {
        CountBarrier barrier = new CountBarrier(15);
        Thread one = new Thread(barrier::await);
        Thread two = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                barrier.count();
            }
        }, "Нить 2");

        one.start();
        two.start();
    }
}
