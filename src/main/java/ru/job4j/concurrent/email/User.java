package ru.job4j.concurrent.email;

import lombok.Data;

@Data
public class User {
    private final String username;
    private final String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
