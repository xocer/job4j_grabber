package ru.job4j.conc__store;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, User> users = new HashMap<>();

    public synchronized boolean delete(User user) {
        return users.remove(user.getId(), user);
    }

    public synchronized boolean update(User user) {
        User tmp = users.get(user.getId());
        if (tmp != null) {
            tmp.setAmount(user.getAmount());
            return true;
        }
        return false;
    }

    public synchronized void add(User user) {
        users.put(user.getId(), user);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User from = users.get(fromId);
        User to = users.get(toId);

        if (from != null && to != null) {
            int balance = from.getAmount();
            if (balance - amount > 0) {
                from.setAmount(from.getAmount() - amount);
                to.setAmount(to.getAmount() + amount);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
         UserStorage store = new UserStorage();

        store.add(new User(1, 100));
        store.add(new User(2, 200));

        store.transfer(1, 2, 50);
        for (User user : store.users.values()) {
            System.out.println(user.getId() + " " + user.getAmount());
        }
    }
}
