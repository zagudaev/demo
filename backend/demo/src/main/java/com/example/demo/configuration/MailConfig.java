package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.persistence.Column;
import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.yandex.ru");
        mailSender.setPort(465);
        mailSender.setUsername("leobox63@yandex.ru");
        mailSender.setPassword("Everest1987");
        mailSender.setProtocol("smtps");
        Properties properties = mailSender.getJavaMailProperties();
        properties.setProperty("mail.transport.protocol","smtps");
        properties.setProperty("mail.debug","true");
        properties.put("mail.smtp.starttls.enable", "true");
        return mailSender;
    }
}
