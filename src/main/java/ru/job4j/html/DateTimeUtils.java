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
    private static final String DEFAULT_DATE_PATTERN = "d MMM yy";
    private final SimpleDateFormat format;

    public DateTimeUtils() {
        this.format = getSimpleDateFormat(DEFAULT_DATE_PATTERN);
    }

    private SimpleDateFormat getSimpleDateFormat(String pattern) {
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = {"янв", "фев", "мар", "апр", "май", "июн",
                "июл", "авг", "сен", "окт", "ноя", "дек"};
        dfs.setShortMonths(months);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, dfs);
        return sdf;
    }

    public LocalDateTime getLocalDateTime(String date) throws ParseException {
        String[] dateArray = date.split(", ");
        LocalDateTime localDateTime;
        LocalDate localDate;
        LocalTime localTime = LocalTime.parse(dateArray[1], DateTimeFormatter.ofPattern("HH:mm"));

        if (dateArray[0].contains("сегодня")) {
            localDate = LocalDate.now();
        } else if (date.contains("вчера")) {
            localDate = LocalDate.now().minusDays(1);
        } else {
            Date tmp = format.parse(date);
            localDate = tmp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        localDateTime = LocalDateTime.of(localDate, localTime);

        return localDateTime;
    }
}
