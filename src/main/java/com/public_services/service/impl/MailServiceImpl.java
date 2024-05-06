package com.public_services.service.impl;

import com.public_services.service.MailService;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {
    @Override
    public void sendEmail(String email, String msg) {
//        System.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        //todo: поменять сендера
        String from = "servicesbel@mail.ru";
        String to = email;
        Properties properties = System.getProperties();
        properties.put("mail.transport.protocol", "smtps");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.mail.ru");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtps.ssl.checkserveridentity", true);
        properties.put("mail.smtps.ssl.trust", "*");
        properties.put("mail.smtp.ssl.enable", "true");
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "pywx3fPQnuTGCmmf6HyM");
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Ответ сервиса для онлайн записи и получения услуг");
            message.setText(msg);
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
