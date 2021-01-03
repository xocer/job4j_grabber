package ru.job4j.storage;

import ru.job4j.storage.food.Food;
import ru.job4j.storage.store.FoodStore;
import ru.job4j.storage.store.Shop;
import ru.job4j.storage.store.Trash;
import ru.job4j.storage.store.Warehouse;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ControlQuality {

    public void checkFood(Food food) {
        int percent = getPercent(food.getCreateDate(), food.getExpiryDate());

        FoodStore foodStore;

        if (percent < 25) {
            foodStore = new Warehouse();
            foodStore.add(food);
        } else if (percent < 75) {
            foodStore = new Shop();
            foodStore.add(food);
        } else if (percent <= 100) {
            foodStore = new Shop();
            food.setDiscount(50);
            foodStore.add(food);
        } else {
            foodStore = new Trash();
            foodStore.add(food);
        }
    }

    public int getPercent(LocalDate start, LocalDate finish) {
        long allDays = ChronoUnit.DAYS.between(start, finish);
        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), finish);
        return (int) ((100 * daysLeft) / allDays);
    }
}