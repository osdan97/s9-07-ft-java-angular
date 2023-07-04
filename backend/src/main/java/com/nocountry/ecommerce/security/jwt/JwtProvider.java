package com.nocountry.ecommerce.security.jwt;

import com.nocountry.ecommerce.model.Account;
import com.nocountry.ecommerce.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface JwtProvider {
    String generateToken(UserPrincipal auth);

    String generateToken(Account account);

    Authentication getAuthentication(HttpServletRequest request);

    boolean isTokenValid(HttpServletRequest request);
}
