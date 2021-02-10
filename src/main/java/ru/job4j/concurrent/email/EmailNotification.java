package ru.job4j.concurrent.email;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private final ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void email(User user) {
        String name = user.getUsername();
        String address = user.getEmail();

        String subject = String.format("Notification {%s} to email {%s}.", name, address);
        String body = String.format("Add a new event to {%s}", name);

        pool.execute(() ->{
            send(subject, body, address);
        });
    }

    public void send(String subject, String body, String email) {

    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}