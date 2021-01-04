package ru.job4j.storage.store;

import ru.job4j.storage.food.Food;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Trash implements FoodStore {
    private final List<Food> products = new ArrayList<>();

    @Override
    public void add(Food food) {
        products.add(food);
    }

    @Override
    public boolean accept(Food food) {
        int percent = getPercent(food.getCreateDate(), food.getExpiryDate());
        return percent >= 100;
    }

    @Override
    public List<Food> clear() {
        List<Food> list = new ArrayList<>();
        Collections.copy(products, list);
        products.clear();
        return list;
    }
}
