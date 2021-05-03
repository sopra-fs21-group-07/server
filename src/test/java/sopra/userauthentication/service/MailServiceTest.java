package sopra.userauthentication.service;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import sopra.userauthentication.model.NotificationEmail;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static javax.mail.Message.RecipientType;

public class MailServiceTest {

    @InjectMocks
    private MailService mailService;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MimeMessage mimeMessage;
    @Autowired
    private NotificationEmail notificationEmail;

    @BeforeEach
    public void setup() {
        mimeMessage = new MimeMessage((Session)null);
        javaMailSender = mock(JavaMailSender.class);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
        mailService = new MailService(javaMailSender);
    }


    @Test
    void sendMailTest(){
        NotificationEmail notificationEmail = new NotificationEmail();
        notificationEmail.setBody("asd");
        notificationEmail.setSubject("qwe");
        notificationEmail.setRecipient("yxc");

        mailService.sendMail(notificationEmail);
    }






        /*@Autowired
        private MailService mailService;
        @Autowired
        private JavaMailSender javaMailSender;
        @Autowired
        private MimeMessage mimeMessage;
        @Autowired
        private NotificationEmail notificationEmail;

        @BeforeEach
        public void setup() {
            mimeMessage = new MimeMessage((Session)null);
            javaMailSender = mock(JavaMailSender.class);
            when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
            mailService = new MailService(javaMailSender);
        }

        @Test
        public void emailTest() {
            String recipient = "example@example.com";
            notificationEmail.setRecipient("test");

            mailService.sendMail(notificationEmail);
            assertEquals(recipient, "test");
        }*/



}