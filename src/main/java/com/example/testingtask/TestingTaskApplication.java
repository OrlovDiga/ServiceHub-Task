package com.example.testingtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TestingTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestingTaskApplication.class, args);
	}

    @Bean
    public RestTemplate template(){
        return new RestTemplate();
    }
}
