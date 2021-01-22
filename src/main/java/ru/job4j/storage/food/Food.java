package ru.job4j.storage.food;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Food {
    String name;
    LocalDate expiryDate;
    LocalDate createDate;
    double price;
    int discount;

    public Food(String name, LocalDate expiryDate, LocalDate createDate, double price) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
    }
}
