package ru.job4j.cach;

import java.util.concurrent.ConcurrentHashMap;

public class NonBlockingCash {
    private final ConcurrentHashMap<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }
    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), (a, b) -> {
            if (b.getVersion() != model.getVersion()) {
                throw new OptimisticException("Ошибочка");
            }
            int newVersion = model.getVersion() + 1;
            Base res = new Base(model.getId(), newVersion);
            res.setName(model.getName());

            return res;
        }) != null;
    }

    public boolean delete(Base model) {
        return memory.remove(model.getId(), model);
    }

    public Base find(int id) {
        return memory.get(id);
    }
}
