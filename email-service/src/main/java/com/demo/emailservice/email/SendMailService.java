package com.demo.emailservice.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendMailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String toMail,
                         String subject,
                         String body) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("${spring.mail.username}");
        message.setSubject(subject);
        message.setTo(toMail);
        message.setText(body);

        mailSender.send(message);
    }

}
