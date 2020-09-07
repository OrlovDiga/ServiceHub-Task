package com.example.testingtask.adapter.impl;

import com.example.testingtask.adapter.FindOutWeather;
import com.example.testingtask.data.serviceARequest.MessageA;
import com.example.testingtask.service.WeatherService;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeoutException;

/**
 * @author Orlov Diga
 */
@Service
public class FindOutWeatherAdapter implements FindOutWeather {

    private final WeatherService service;

    public FindOutWeatherAdapter(@Qualifier("weatherServiceAlias") WeatherService service) {
        this.service = service;
    }

    @Override
    public int getWeather(Exchange exchange) throws TimeoutException {
        MessageA bodyIn = exchange.getIn().getBody(MessageA.class);

        int temp = service.getTempByCoordinates(
                bodyIn.getCoordinates().getLatitude(),
                bodyIn.getCoordinates().getLongitude());

        return temp;
    }
}
