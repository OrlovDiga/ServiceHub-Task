package com.example.testingtask.config;

import com.example.testingtask.service.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Orlov Diga
 */
@Configuration
public class AppConfig {

    private final ApplicationContext context;

    public AppConfig(ApplicationContext context) {
        this.context = context;
    }

    @Bean
    public WeatherService weatherServiceAlias(@Value("${weather.service.class}") String qualifier) {
        return (WeatherService) context.getBean(qualifier);
    }
}
