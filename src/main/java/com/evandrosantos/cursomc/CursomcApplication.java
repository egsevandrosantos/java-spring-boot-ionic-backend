package com.evandrosantos.cursomc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CursomcApplication {
    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }
}
