package com.evandrosantos.cursomc.services.email;

import com.evandrosantos.cursomc.domain.Pedido;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendOrderConfirmationEmail(Pedido pedido) throws UnirestException;

    JsonNode sendEmail(SimpleMailMessage msg) throws UnirestException;
}
