package com.evandrosantos.cursomc.config;

import com.evandrosantos.cursomc.services.email.EmailService;
import com.evandrosantos.cursomc.services.email.SmtpEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("production")
public class ProductionConfig {
    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }
}
