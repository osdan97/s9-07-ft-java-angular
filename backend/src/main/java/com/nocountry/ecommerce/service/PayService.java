package com.nocountry.ecommerce.service;

import com.nocountry.ecommerce.model.Pay;
import jakarta.mail.MessagingException;
import net.authorize.api.contract.v1.ANetApiResponse;

import java.io.UnsupportedEncodingException;

public interface PayService {
    ANetApiResponse chargePay(Pay pay) throws MessagingException, UnsupportedEncodingException;
}
