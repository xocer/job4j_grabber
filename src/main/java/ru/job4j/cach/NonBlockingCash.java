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

    public synchronized Base find(int id) {
        Base tmp = memory.get(id);
        Base result = new Base(tmp.getId(), tmp.getVersion());
        result.setName(tmp.getName());
        return result;
    }
}
