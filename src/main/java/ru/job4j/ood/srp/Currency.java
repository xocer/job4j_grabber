package ru.job4j.ood.srp;

public enum Currency {
    USD(60),
    EUR(75);

    private final int cost;

    Currency(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
}
