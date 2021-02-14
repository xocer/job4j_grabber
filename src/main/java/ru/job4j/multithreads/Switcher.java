package ru.job4j.multithreads;

public class Switcher {
    public static void main(String[] args) throws InterruptedException {

        MasterSlaveBarrier msb = new MasterSlaveBarrier();

        Thread first = new Thread(
                () -> {
                    while (true) {
                        try {
                            msb.tryMaster();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println("Thread A - 1");

                        try {
                            Thread.sleep(1000);
                            msb.doneMaster();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    while (true) {
                        try {
                            msb.trySlave();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Thread B - 2");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        msb.doneSlave();
                    }
                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
    }
}
