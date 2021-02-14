package ru.job4j.multithreads;

public class MasterSlaveBarrier {
    private boolean flag = true;

    public synchronized void tryMaster() throws InterruptedException {
        while (!flag) {
            wait();
        }
    }

    public synchronized void trySlave() throws InterruptedException {
        while (flag) {
            wait();
        }
    }

    public synchronized void doneMaster() throws InterruptedException {
        flag = false;
        notify();
    }

    public synchronized void doneSlave() {
        flag = true;
        System.out.println();
        notify();
    }
}
