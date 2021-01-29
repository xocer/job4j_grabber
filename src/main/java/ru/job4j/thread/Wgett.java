package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wgett implements Runnable {
    private final String url;
    private final int speed;

    public Wgett(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        /* Скачать файл*/
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("result.txt")) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead = 0;
            while (bytesRead != -1) {
                long start = System.currentTimeMillis();
                bytesRead = in.read(dataBuffer, 0, 1024);
                long finish = System.currentTimeMillis();
                long time = finish - start;
                if (speed - time > 0) {
                    Thread.sleep(speed - time);
                }
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wgett(url, speed));
        wget.start();
        wget.join();
    }
}
