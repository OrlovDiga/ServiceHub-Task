package com.example.camelspringboot.service;

/**
 * @author Orlov Diga
 */
public interface WeatherService {

    public int getTempByCoordinates(String latitude, String longitude);
}
