package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
}