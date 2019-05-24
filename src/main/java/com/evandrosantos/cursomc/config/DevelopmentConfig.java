package com.evandrosantos.cursomc.config;

import com.evandrosantos.cursomc.services.DBService;
import com.evandrosantos.cursomc.services.email.EmailService;
import com.evandrosantos.cursomc.services.email.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("development")
public class DevelopmentConfig {
    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        if (!ddlAuto.equals("create"))
            return false;

        dbService.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }
}
