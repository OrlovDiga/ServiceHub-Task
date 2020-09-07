package com.example.testingtask.service.impl;

import com.example.testingtask.data.weatherApi.Main;
import com.example.testingtask.data.weatherApi.WeatherResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Orlov Diga
 */
@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceImplTest {

    @Mock
    private RestTemplate template;

    @InjectMocks
    private WeatherServiceImpl service = new WeatherServiceImpl();

    @Test
    public void getTempByCoordinatesTest() {
        WeatherResponse response = new WeatherResponse();
        Main main = new Main();
        main.setTemp(300.0); //by Kelvin
        response.setMain(main);

        Mockito.doReturn(response)
                .when(template).getForObject(Mockito.any(), Mockito.any(), Mockito.anyMap());

        assertEquals(27, service.getTempByCoordinates("10", "10"));
        assertNotEquals(123, service.getTempByCoordinates("10", "10"));
    }
}
