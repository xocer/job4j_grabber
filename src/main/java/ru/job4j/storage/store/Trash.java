package ru.job4j.storage.store;

import ru.job4j.storage.food.Food;

import java.util.ArrayList;
import java.util.List;

public class Trash implements FoodStore {
    private final List<Food> products = new ArrayList<>();

    @Override
    public void add(Food food) {
        products.add(food);
    }
}
