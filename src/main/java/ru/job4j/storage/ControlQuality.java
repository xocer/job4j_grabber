package ru.job4j.storage;

import ru.job4j.storage.food.Food;
import ru.job4j.storage.store.FoodStore;
import ru.job4j.storage.store.Shop;
import ru.job4j.storage.store.Trash;
import ru.job4j.storage.store.Warehouse;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final List<FoodStore> storages = new ArrayList<>();

    public ControlQuality() {
        storages.add(new Warehouse());
        storages.add(new Shop());
        storages.add(new Trash());
    }

    public void distribute(Food food) {
        for (FoodStore f : storages) {
            if (f.accept(food)) {
                f.add(food);
                break;
            }
        }
    }
}