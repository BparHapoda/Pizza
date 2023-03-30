package com.example.pizza.services;

import com.example.pizza.dtos.OrderForm;
import jakarta.mail.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailService implements MessageService {
    @Override
    public void send(OrderForm orderForm) throws MessagingException {
        Properties props = System.getProperties();
        String host = "imap.yandex.com";
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user","cdrom5@yandex.ru");
        props.put("mail.smtp.password", "gzfydrptobalduns");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.quitwait", "false");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.debug", "true");

        Session session = Session.getInstance(props);
        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);
        message.setHeader("Content-Type", "text/plain; charset=UTF-8");
        message.setSubject("subject", "UTF-8");
        message.setText("body", "UTF-8");

        try {
            message.setFrom(new InternetAddress("cdrom5@yandex.ru"));
            InternetAddress toAddress = new InternetAddress("cddvdrom@yandex.ru");
            message.addRecipient(Message.RecipientType.TO, toAddress);

            message.setSubject("subjectt");
            message.setText("bodyy");
            Transport transport = session.getTransport("smtp");
            transport.connect(host, "cdrom5@yandex.ru", "gzfydrptobalduns");
            transport.sendMessage(message, message.getAllRecipients());

            transport.close();
        } catch (AddressException ae) {
        } catch (MessagingException me) {
        }
    }
}