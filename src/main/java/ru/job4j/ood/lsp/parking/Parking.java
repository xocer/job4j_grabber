package ru.job4j.ood.lsp.parking;

public interface Parking {
    boolean add(Auto auto) throws Exception;

    void remove(Auto auto);

    int getFreeCarsPlaces();

    int getFreeTruckCarsPlaces();
}
