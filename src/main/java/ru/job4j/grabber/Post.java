package ru.job4j.grabber;

import java.time.LocalDateTime;

public class Post {
    private String name;
    private String text;
    private String url;
    private LocalDateTime date;

    public Post(String text, LocalDateTime date) {
        this.text = text;
        this.date = date;
    }
}