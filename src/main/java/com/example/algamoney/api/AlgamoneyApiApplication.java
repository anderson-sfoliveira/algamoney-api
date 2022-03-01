package com.example.algamoney.api;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.algamoney.api.config.property.AlgamoneyApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(AlgamoneyApiProperty.class)
public class AlgamoneyApiApplication {
    
    @PostConstruct
    void started() {
      TimeZone.setDefault(TimeZone.getTimeZone("TimeZone"));
    }

	public static void main(String[] args) {
		SpringApplication.run(AlgamoneyApiApplication.class, args);
	}

}
