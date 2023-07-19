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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
                .requestMatchers(HttpMethod.PUT,"/api/account/change/**",
                        "api/account/findallcustomerlist",
                        "api/category/updatebyname/**",
                        "api/category/update/**",
                        "api/inventory/update/**",
                        "api/products/update/**")
                .hasRole(Role.ADMIN.name())

                .requestMatchers(HttpMethod.POST,"api/inventory/create",
                        "api/category/create",
                        "api/transaction/inventory")
                .hasRole(Role.ADMIN.name())

                .requestMatchers(HttpMethod.GET, "api/inventory/list",
                        "api/transaction/inventory/**").hasRole(Role.ADMIN.name())

                .requestMatchers(HttpMethod.DELETE,"api/category/delete/**",
                        "api/category/deletebyname/**",
                        "api/products/delete/**")
                .hasRole(Role.ADMIN.name())

                .requestMatchers(HttpMethod.PATCH, "api/products/update-state/**").hasRole(Role.USER.name())

                .requestMatchers(HttpMethod.PUT, "/api/account/updateAccount/**").hasRole(Role.USER.name())

                .requestMatchers(HttpMethod.POST, "api/orders",
                        "/api/pay")
                .hasRole(Role.USER.name())
                .requestMatchers(HttpMethod.GET, "/customer/**").hasRole(Role.USER.name())

                .requestMatchers(HttpMethod.POST,"/api/favorites/create").hasAnyRole(Role.USER.name(),Role.ADMIN.name())
                .requestMatchers(HttpMethod.GET,"/api/favorites/list/**").hasAnyRole(Role.USER.name(),Role.ADMIN.name())
                .requestMatchers(HttpMethod.DELETE,"/api/favorites/deletebyid/**").hasAnyRole(Role.USER.name(),Role.ADMIN.name())

                .requestMatchers("/api/authentication/sign-in",
                        "/api/authentication/sign-up",
                        "/api/user/sign-up",
                        "/api/authentication/forgot-password",
                        "/api/authentication/change-password",
                        "/swagger-ui/**",
                        "/v3/**",
                        "api/category/detail/**",
                        "api/category/list",
                        "/api/authentication/verify/**",
                        "/api/products",
                        "/api/products/totalproducts",
                        "/api/shipping-details/**",
                        "/api/products/totalpages"
                ).permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter(){

        return new JwtAuthorizationFilter();
    }
    @Bean
    public WebMvcConfigurer corsConfigurer(){

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }
}
