package ru.job4j.cach;

import java.util.concurrent.ConcurrentHashMap;

public class NonBlockingCash {
    private final ConcurrentHashMap<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }
    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), (a, b) -> {
            int version = model.getVersion();
            if (b.getVersion() != version) {
                throw new OptimisticException("Ошибочка");
            }
            model.setVersion(++version);
            return model;
        }) != null;
    }

    public boolean delete(Base model) {
        return memory.remove(model.getId(), model);
    }

    public ConcurrentHashMap<Integer, Base> getMemory() {
        return memory;
    }
}
