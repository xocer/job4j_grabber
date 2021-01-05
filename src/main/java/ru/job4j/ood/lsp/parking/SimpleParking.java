package ru.job4j.ood.lsp.parking;

public class SimpleParking implements Parking {
    @Override
    public boolean add(Auto auto) {
        return false;
    }

    @Override
    public void remove(Auto auto) {

    }

    @Override
    public int getFreePlaces() {
        return 0;
    }
}
