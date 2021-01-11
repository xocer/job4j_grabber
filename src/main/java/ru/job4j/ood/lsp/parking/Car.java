package ru.job4j.ood.lsp.parking;

public class Car implements Auto {
    private final int id;
    private final int size = 1;

    public Car(int id) {
        this.id = id;
    }

    @Override
    public int size() {
        return size;
    }

    public int getId() {
        return id;
    }
}
