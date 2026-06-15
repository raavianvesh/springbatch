package org.example.springbatch.config;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class DataGenerationConfig {

    @Bean
    public Faker faker() {
        return new Faker(Locale.US);
    }
}
