package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wgett implements Runnable {
    private final String url;
    private final int speed;
    private final File path;

    public Wgett(String url, int speed, File path) {
        this.url = url;
        this.speed = speed;
        this.path = path;
    }

    @Override
    public void run() {
        /* Скачать файл*/
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(path.toString())) {
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
        Thread wget = new Thread(new Wgett(url, speed, new File("result.txt")));
        wget.start();
        wget.join();
    }
}