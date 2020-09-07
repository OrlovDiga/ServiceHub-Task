package com.example.camelspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author Orlov Diga
 */
@SpringBootApplication
public class CamelSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(CamelSpringBootApplication.class, args);
    }

    @Bean
    public RestTemplate template(){
        return new RestTemplate();
    }

}
