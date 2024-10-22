package com.unicorn.sg.demo.utils;

import jakarta.inject.Named;
import jakarta.inject.Singleton;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Named
@Singleton
public class DateTimeUtils implements Serializable {

    private transient final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    private transient final DateTimeFormatter dateTimeFormatterShort = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private transient final DateTimeFormatter tpDateFormat = DateTimeFormatter.ISO_DATE_TIME;


    public String format(LocalDateTime localDateTime) {
        return format(localDateTime, dateTimeFormatter);
    }

    public String format(ZonedDateTime zonedDateTime) {
        return format(zonedDateTime, dateTimeFormatter);
    }

    public String format(LocalDateTime localDateTime, DateTimeFormatter dateTimeFormatter) {
        return dateTimeFormatter.format(localDateTime);
    }

    public String format(ZonedDateTime zonedDateTime, DateTimeFormatter dateTimeFormatter) {
        return dateTimeFormatter.format(zonedDateTime);
    }

    public DateTimeFormatter getDateTimeFormatterShort() {
        return dateTimeFormatterShort;
    }

    public DateTimeFormatter getDateTimeFormatter() {
        return dateTimeFormatter;
    }

    public DateTimeFormatter getTpDateFormat() {
        return tpDateFormat;
    }

    public String getRelativeTime(LocalDateTime dateTime) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(dateTime, now);

        // Convert the duration into different time units
        long seconds = duration.getSeconds();
        long minutes = duration.toMinutes();
        long hours = duration.toHours();
        long days = duration.toDays();

        if (seconds < 60) {
            return "before " + seconds + " second" + (seconds == 1 ? "" : "s");
        } else if (minutes < 60) {
            return "before " + minutes + " minute" + (minutes == 1 ? "" : "s");
        } else if (hours < 24) {
            return "before " + hours + " hour" + (hours == 1 ? "" : "s");
        } else if (days < 1) {
            return "before " + hours + " hour" + (hours == 1 ? "" : "s");
        } else if (days < 2) {
            return "before 1 day";
        } else if (days < 7) {
            return "before " + days + " day";
        } else {
            return dateTime.format(dateTimeFormatterShort);
        }
    }
}
