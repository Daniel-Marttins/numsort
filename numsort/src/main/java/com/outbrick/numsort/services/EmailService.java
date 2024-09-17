package com.outbrick.numsort.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendEmail(String to, String subject, String username, String password) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            Context context = new Context();
            context.setVariable("username", username);
            context.setVariable("password", password);

            String body = templateEngine.process("welcome-email", context);

            helper.setText(body, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(from);

            mailSender.send(mimeMessage);
            logger.info("E-mail enviado com sucesso para: {}", to);
        } catch (MessagingException e) {
            logger.error("Falha ao enviar email para: {}. Erro: {}", to, e.getMessage(), e);
        }

    }

}
