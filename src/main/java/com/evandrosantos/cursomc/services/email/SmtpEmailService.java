package com.evandrosantos.cursomc.services.email;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService extends AbstractEmailService {
    @Autowired
    private MailSender mailSender;


    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

    @Override
    public JsonNode sendEmail(SimpleMailMessage msg) throws UnirestException {
        LOG.info("Enviando email...");
        //String domain = "sandbox3232fb3acc0244279a9d494eb03097c6.mailgun.org";
        //String apiKey = "e2be8013510957ccb0493761d85d898e-39bc661a-9a35ebdc";
        //HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + domain + "/messages")
//                .basicAuth("api", apiKey)
//                .field("from", "cursomc@evandro.example.com")
//                .field("to", msg.getTo())
//                .field("subject", msg.getSubject())
//                .field("text", msg.getText())
//                .asJson();
        mailSender.send(msg);
        LOG.info("Email enviado...");
        return null;
//        return request.getBody();
    }
}
