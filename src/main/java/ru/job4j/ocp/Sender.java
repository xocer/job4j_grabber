package ru.job4j.ocp;

public class Sender {
    private final ConsoleSender consoleSender;

    public Sender(ConsoleSender consoleSender) {
        this.consoleSender = consoleSender;
    }

    public void sendMessage(String message) {
        consoleSender.sendToConsole(message);
    }
}
