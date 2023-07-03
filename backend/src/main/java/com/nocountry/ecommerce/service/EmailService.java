package com.nocountry.ecommerce.service;

import com.nocountry.ecommerce.dto.EmailValues;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface EmailService {
    void sendEmailForgotPassword(EmailValues emailValues) throws MessagingException, UnsupportedEncodingException;
}
