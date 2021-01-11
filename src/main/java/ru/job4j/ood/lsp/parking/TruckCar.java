package ru.job4j.ood.lsp.parking;

public class TruckCar implements Auto {
    private final int id;
    private final int size;

    public TruckCar(int id, int size) {
        this.id = id;
        this.size = size;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int getId() {
        return id;
    }
}
