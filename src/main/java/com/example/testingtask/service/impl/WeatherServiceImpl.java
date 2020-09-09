package com.example.testingtask.service.impl;

import com.example.testingtask.data.weatherApi.WeatherResponse;
import com.example.testingtask.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Orlov Diga
 */
@Service("openWeatherService")
public class WeatherServiceImpl implements WeatherService {

    private static final Double ZERO_FOR_CELSIUS_BY_KELVIN = -273.15;

    @Value("${weather.api.url}")
    private String weatherApiUrl;

    @Value("${weather.api.key}")
    private String weatherApiKey;

    @Autowired
    private RestTemplate template;

    public int getTempByCoordinates(String latitude, String longitude) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("lon", longitude);
        uriVariables.put("lat", latitude);
        uriVariables.put("key", weatherApiKey);
        WeatherResponse response = template.getForObject(weatherApiUrl, WeatherResponse.class, uriVariables);

        double temp = response.getMain().getTemp() + ZERO_FOR_CELSIUS_BY_KELVIN;

        return (int) Math.round(temp);
    }

    public String getWeatherApiUrl() {
        return weatherApiUrl;
    }

    public void setWeatherApiUrl(String weatherApiUrl) {
        this.weatherApiUrl = weatherApiUrl;
    }

    public String getWeatherApiKey() {
        return weatherApiKey;
    }

    public void setWeatherApiKey(String weatherApiKey) {
        this.weatherApiKey = weatherApiKey;
    }
}
