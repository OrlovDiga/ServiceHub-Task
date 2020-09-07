package com.example.testingtask.adapter;

import org.apache.camel.Exchange;

import java.util.concurrent.TimeoutException;

/**
 * @author Orlov Diga
 */
public interface FindOutWeather {

    public int getWeather(Exchange exchange) throws TimeoutException;
}
