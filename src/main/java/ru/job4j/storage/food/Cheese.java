package ru.job4j.storage.food;

import java.time.LocalDate;

public class Cheese extends Food {
    public Cheese(String name, LocalDate expiryDate, LocalDate createDate, double price) {
        super(name, expiryDate, createDate, price);
    }
}
