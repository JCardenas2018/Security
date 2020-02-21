package com.vallegrande.springsecurity;

import com.vallegrande.springsecurity.modelo.Persona;
import com.vallegrande.springsecurity.modelo.PersonaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @Profile("demo")
    @Bean
    CommandLineRunner initDatabase(PersonaRepository repository) {
        return args -> {
            repository.save(new Persona("Jesus Alejandro", "Cardenas Vilca", new BigDecimal("21")));
        };
    }
}
