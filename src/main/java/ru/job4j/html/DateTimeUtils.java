package ru.job4j.html;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtils {

    public static SimpleDateFormat getSimpleDateFormat(String pattern) {
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = {"янв", "фев", "мар", "апр", "май", "июн",
                "июл", "авг", "сен", "окт", "ноя", "дек"};
        dfs.setShortMonths(months);
        final String template = pattern;
        SimpleDateFormat sdf = new SimpleDateFormat(template, dfs);
        return sdf;
    }

    public static LocalDateTime getLocalDateTime(String date) throws ParseException {
        String[] dateArray = date.split(", ");
        LocalDateTime localDateTime;
        LocalDate localDate;
        LocalTime localTime = LocalTime.parse(dateArray[1], DateTimeFormatter.ofPattern("HH:mm"));

        if (dateArray[0].contains("сегодня")) {
            localDate = LocalDate.now();
            localDateTime = LocalDateTime.of(localDate, localTime);
        } else if (date.contains("вчера")) {
            localDate = LocalDate.now().minusDays(1);
            localDateTime = LocalDateTime.of(localDate, localTime);
        } else {
            SimpleDateFormat sdf = getSimpleDateFormat("d MMM yy");
            Date tmp = sdf.parse(date);
            localDate = tmp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            localDateTime = LocalDateTime.of(localDate, localTime);
        }
        return localDateTime;
    }
}
