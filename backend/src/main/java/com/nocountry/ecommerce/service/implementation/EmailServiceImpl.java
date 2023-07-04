package com.nocountry.ecommerce.service.implementation;

import com.nocountry.ecommerce.dto.EmailValues;
import com.nocountry.ecommerce.repository.CustomerRepository;
import com.nocountry.ecommerce.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private CustomerRepository customerRepository;
    @Value("${verification.base-url}")
    protected String baseUrl;
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String emailFrom;
    @Override
    public void sendEmailForgotPassword(EmailValues emailValues) throws MessagingException, UnsupportedEncodingException {
        String email = emailValues.getMailTo();
        String fullName = emailValues.getFullName();
        String subject = emailValues.getSubject();
        String tokenPassword = emailValues.getToken();

        String senderName = "Ecommerce Team";
        String mailContent = "<head>";
        mailContent += "<style>";
        mailContent += "a{";
        mailContent += "display: block;";
        mailContent += "width: 200px;";
        mailContent += "font-family: Arial, Helvetica, sans-serif;";
        mailContent += "font-weight: 700;";
        mailContent += "color: #FFB344;";
        mailContent += "background-color: #00A19D;";
        mailContent += "border-radius: 10px;";
        mailContent += "padding: 15px 30px;";
        mailContent += "margin: 20px 20px;";
        mailContent += "text-align: center;";
        mailContent += "text-decoration: none;";
        mailContent += "}";
        mailContent += "a:hover{";
        mailContent += "background-color: #FFB344;";
        mailContent += "border: 2px solid #00A19D;";
        mailContent += "color: #00A19D;";
        mailContent += "}";
        mailContent += "</style>";
        mailContent += "</head>";

        mailContent += "<p>Dear " + fullName + ",</p>";

        String verifyURL = baseUrl + "changepassword/" + tokenPassword;
        mailContent += "<h3><a href=\"" + verifyURL + "\" target=_blank >Click for change password</a></h3>";

        mailContent += "<p> Thank you <br>The Ecommerce Team </p>";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        emailValues.setMailFrom(emailFrom);
        emailValues.setSubject(subject);
        emailValues.setMailTo(email);
        emailValues.setFullName(fullName);

        helper.setFrom(emailFrom, senderName);
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(mailContent, true);

        javaMailSender.send(message);
    }
    @Override
    public void sendEmailVerificationCode(EmailValues emailValues) throws MessagingException, UnsupportedEncodingException {
        String email = emailValues.getMailTo();
        String fullName = emailValues.getFullName();
        String subject = emailValues.getSubject();
        String verificationCode = emailValues.getToken();

        String senderName = "Ecommerce";
        String mailContent = "<head>";
        mailContent += "<style>";
        mailContent += "a{";
        mailContent += "display: block;";
        mailContent += "width: 200px;";
        mailContent += "font-family: Arial, Helvetica, sans-serif;";
        mailContent += "font-weight: 700;";
        mailContent += "color: #FFB344;";
        mailContent += "background-color: #00A19D;";
        mailContent += "border-radius: 10px;";
        mailContent += "padding: 15px 30px;";
        mailContent += "margin: 20px 20px;";
        mailContent += "text-align: center;";
        mailContent += "text-decoration: none;";
        mailContent += "}";
        mailContent += "a:hover{";
        mailContent += "background-color: #FFB344;";
        mailContent += "border: 2px solid #00A19D;";
        mailContent += "color: #00A19D;";
        mailContent += "}";
        mailContent += "</style>";
        mailContent += "</head>";
        mailContent += "<p> Dear " + fullName + ",</p>";
        mailContent += "<p> Please click the link below to verify to your registration:</p>";

        String verifyURL = baseUrl + "verify/" + verificationCode;
        mailContent += "<h3><a href=\"" + verifyURL + "\" target=_blank >Click to verify your account</a></h3>";

        mailContent +=  "<p> Thanks you <br> Ecommerce Team </p>";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(emailFrom,senderName);
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(mailContent,true);

        javaMailSender.send(message);
    }
}
