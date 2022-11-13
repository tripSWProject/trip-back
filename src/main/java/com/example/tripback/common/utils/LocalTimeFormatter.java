package com.example.tripback.common.utils;

import org.springframework.format.Formatter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalTimeFormatter implements Formatter<LocalTime> {
    @Override
    public LocalTime parse(String text, Locale locale) {
        return LocalTime.parse(text, DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public String print(LocalTime object, Locale locale) {
        return DateTimeFormatter.ofPattern("HH:mm").format(object);
    }
}
