package com.example.tripback.config;

import com.example.tripback.common.utils.LocalDateFormatter;
import com.example.tripback.common.utils.LocalTimeFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public LocalDateFormatter localDateFormatter() {
        return new LocalDateFormatter();
    }
    @Bean
    public LocalTimeFormatter localTimeFormatter() {
        return new LocalTimeFormatter();
    }
}
