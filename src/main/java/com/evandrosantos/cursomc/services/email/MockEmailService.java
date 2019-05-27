package com.evandrosantos.cursomc.services.email;

import com.mashape.unirest.http.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {
    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public JsonNode sendEmail(SimpleMailMessage msg) {
        LOG.info("Simulando envio de email...");
        LOG.info(msg.toString());
        LOG.info("Email enviado...");
        return null;
    }
}
