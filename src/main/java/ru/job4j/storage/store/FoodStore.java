package ru.job4j.storage.store;

import ru.job4j.storage.food.Food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public interface FoodStore {
    void add(Food food);

    boolean accept(Food food);

    List<Food> clear();

    default int getPercent(LocalDate start, LocalDate finish) {
        long allDays = ChronoUnit.DAYS.between(start, finish);
        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), finish);
        return (int) ((100 * daysLeft) / allDays);
    }
}
