package com.nocountry.ecommerce.service;

import com.nocountry.ecommerce.dto.CustomerLoginResponse;
import com.nocountry.ecommerce.model.Account;

public interface AuthenticationService {
    CustomerLoginResponse signInAndReturnJWT(Account signInRequest);
}
