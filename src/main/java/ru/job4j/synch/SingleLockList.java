package ru.job4j.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.*;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private final SimpleArray<T> array = new SimpleArray<>();

    public synchronized void add(T value) {
        array.add(value);
    }

    public synchronized T get(int index) {
        return array.get(index);
    }

    private List<T> copy(SimpleArray<T> list) {
        ArrayList<T> result = new ArrayList<>();
        for (int i = 0; i < list.getSize(); i++) {
            result.add(list.get(i));
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return copy(array).iterator();
    }
}