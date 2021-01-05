package ru.job4j.ood.lsp.parking;

public interface Parking {
    boolean add(Auto auto);

    void remove(Auto auto);

    int getFreePlaces();
}
