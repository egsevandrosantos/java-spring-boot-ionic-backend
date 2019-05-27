package com.evandrosantos.cursomc.services.email;

import com.evandrosantos.cursomc.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService {
    @Value("${default.sender}")
    private String defaultSender;

    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendOrderConfirmationEmail(Pedido pedido) {
        SimpleMailMessage smm = prepareSimpleMailMessageFromPedido(pedido);
        sendEmail(smm);
    }

    @Override
    public void sendOrderConfirmationEmailHtml(Pedido pedido) {
        MimeMessage mm;
        try {
            mm = prepareMimeMessageFromPedido(pedido);
            sendEmailHtml(mm);
        } catch (MessagingException e) {
            sendOrderConfirmationEmail(pedido);
        }
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

    protected MimeMessage prepareMimeMessageFromPedido(Pedido pedido) throws MessagingException {
        MimeMessage mm = javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mm, true);
        mmh.setTo(pedido.getCliente().getEmail());
        mmh.setFrom(defaultSender);
        mmh.setSubject("Pedido confirmado! Código: " + pedido.getId());
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        mmh.setText(htmlFromTemplatePedido(pedido), true);
        return mm;
    }

    protected String htmlFromTemplatePedido(Pedido pedido) {
        Context context = new Context();
        context.setVariable("pedido", pedido);
        return templateEngine.process("email/confirmacaoPedido", context);
    }
}
