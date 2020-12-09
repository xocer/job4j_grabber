package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SqlRuParse {
    public static void main(String[] args) throws Exception {
        for (int j = 1; j < 6; j++) {
            Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers/" + j).get();
            Elements row = doc.select(".postslisttopic");
            Elements date = doc.select(".altCol");
            Elements newDate = new Elements();
            for (int i = 0; i < date.size(); i++) {
                if (i % 2 != 0) {
                    newDate.add(date.get(i));
                }
            }
            for (int i = 0; i < row.size(); i++) {
                System.out.println(row.get(i).child(0).attr("href"));
                System.out.println(row.get(i).text());
                System.out.println(newDate.get(i).text());
            }
        }
    }

    public static LocalDateTime getLocalDateTime(String date) {
        String[] dateArray = date.split(", ");
        LocalDateTime localDateTime;
        LocalDate localDate;
        LocalTime localTime = LocalTime.parse(dateArray[1], DateTimeFormatter.ofPattern("HH:mm"));

        if (dateArray[0].equals("сегодня")) {
            localDate = LocalDate.now();
            localDateTime = LocalDateTime.of(localDate, localTime);
        } else if (date.contains("вчера")) {
            localDate = LocalDate.now().minusDays(1);
            localDateTime = LocalDateTime.of(localDate, localTime);
        } else {
            localDateTime = LocalDateTime.parse(
                    date, DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm"));
        }
        return localDateTime;
    }
}