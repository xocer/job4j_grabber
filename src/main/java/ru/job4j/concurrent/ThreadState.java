package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(
                () -> {}
        );
        System.out.println(first.getState());
        first.start();
        System.out.println(first.getName() + " нить запущена");

        Thread second = new Thread(
                () -> {}
        );
        System.out.println(second.getState());
        second.start();
        System.out.println(second.getName() + " нить запущена");

        while (first.getState() != Thread.State.TERMINATED
                || second.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getState());
            System.out.println(second.getState());
        }
        System.out.println(first.getState());
        System.out.println(second.getState());

        System.out.println("Все нити закончили работу и метод main тоже");
    }
}