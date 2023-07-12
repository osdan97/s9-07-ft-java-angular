package com.nocountry.ecommerce.config;

import com.nocountry.ecommerce.security.CustomUserDetailsService;
import com.nocountry.ecommerce.security.jwt.JwtAuthorizationFilter;
import com.nocountry.ecommerce.util.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        AuthenticationManagerBuilder auth = http.getSharedObject(
                AuthenticationManagerBuilder.class
        );
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);

        AuthenticationManager authenticationManager = auth.build();

        http.cors();
        http.csrf().disable();
        http.authenticationManager(authenticationManager);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.PUT,"/api/account/change/**","api/account/findallcustomerlist").hasRole(Role.ADMIN.name())
                .requestMatchers(HttpMethod.PUT, "/api/account/updateAccount/**").hasRole(Role.USER.name())
                .requestMatchers(HttpMethod.POST,"/api/wishlist/**").hasAnyRole(Role.USER.name(),Role.ADMIN.name())
                .requestMatchers("/api/authentication/sign-in",
                        "/api/authentication/sign-up",
                        "/swagger-ui/**",
                        "/v3/**",
                        "/api/account/updateAccount/**",
                        "/pet/**",
                        "/api/account/verify/**"
                ).permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter(){

        return new JwtAuthorizationFilter();
    }
}
