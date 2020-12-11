package ru.job4j.grabber;

import java.time.LocalDateTime;

public class Post {
    private String name;
    private String text;
    private String url;
    private LocalDateTime date;

    public Post(String name, String text, String url, LocalDateTime date) {
        this.name = name;
        this.text = text;
        this.url = url;
        this.date = date;
    }
}