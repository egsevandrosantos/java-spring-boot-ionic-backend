package com.evandrosantos.cursomc.services.email;

import com.evandrosantos.cursomc.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public interface EmailService {
    void sendOrderConfirmationEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage msg);

    void sendOrderConfirmationEmailHtml(Pedido pedido);

    void sendEmailHtml(MimeMessage msg);
}
