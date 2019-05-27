package com.evandrosantos.cursomc.services.email;

import com.evandrosantos.cursomc.domain.Pedido;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService {
    @Value("${default.sender}")
    private String defaultSender;

    @Override
    public void sendOrderConfirmationEmail(Pedido pedido) throws UnirestException {
        SimpleMailMessage smm = prepareSimpleMailMessageFromPedido(pedido);
        sendEmail(smm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setTo(pedido.getCliente().getEmail());
        smm.setFrom(defaultSender);
        smm.setSubject("Pedido confirmado. Código: " + pedido.getId());
        smm.setSentDate(new Date(System.currentTimeMillis()));
        smm.setText(pedido.toString());
        return smm;
    }
}
