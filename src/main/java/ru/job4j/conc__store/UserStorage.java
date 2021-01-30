package ru.job4j.conc__store;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final List<User> users = new ArrayList<>();

    public synchronized boolean delete(User user) {
        return users.remove(user);
    }

    public synchronized boolean update(User user) {
        for (User value : users) {
            if (value.getId() == user.getId()) {
                value.setAmount(user.getAmount());
                return true;
            }
        }
        return false;
    }

    public synchronized boolean add(User user) {
        return users.add(user);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User from = null;
        User to = null;
        for (User user : users) {
            if (user.getId() == fromId) {
                from = user;
            } else if (user.getId() == toId) {
                to = user;
            }
        }

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
        for (User user : store.users) {
            System.out.println(user.getId() + " " + user.getAmount());
        }
    }
}
