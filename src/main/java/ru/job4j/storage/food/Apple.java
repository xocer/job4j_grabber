package ru.job4j.storage.food;

import java.time.LocalDate;

public class Apple extends Food {

    public Apple(String name, LocalDate expiryDate, LocalDate createDate, double price) {
        super(name, expiryDate, createDate, price);
    }
}
