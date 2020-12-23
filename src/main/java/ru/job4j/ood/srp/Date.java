package ru.job4j.ood.srp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {
    public LocalDate getDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy, MMM, dd");
        return LocalDate.parse(date, formatter);
    }
}
