package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000); /* симулируем выполнение параллельной задачи в течение 1 секунды. */
        progress.interrupt(); //
    }

    @Override
    public void run() {
        String[] symbols = {"\\", "|", "/"};
        int count = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.print("\rLoading ... " + symbols[count++]);
                if (count == symbols.length) {
                    count = 0;
                }
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
