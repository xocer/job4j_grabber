package ru.job4j.storage.store;

import ru.job4j.storage.food.Food;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shop implements FoodStore {
    private final List<Food> products = new ArrayList<>();

    @Override
    public void add(Food food) {
        products.add(food);
    }

    @Override
    public boolean accept(Food food) {
        int percent = getPercent(food.getCreateDate(), food.getExpiryDate());
        if (percent > 75 && percent < 100) {
            food.setDiscount(50);
            return true;
        }
        return percent >= 25 && percent <= 75;
    }

    @Override
    public List<Food> clear() {
        List<Food> list = new ArrayList<>();
        Collections.copy(products, list);
        products.clear();
        return list;
    }
}
