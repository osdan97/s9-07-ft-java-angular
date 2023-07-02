package com.nocountry.ecommerce.service;

import com.nocountry.ecommerce.model.Account;

public interface AuthenticationService {
    Account signInAndReturnJWT(Account signInRequest);
}
