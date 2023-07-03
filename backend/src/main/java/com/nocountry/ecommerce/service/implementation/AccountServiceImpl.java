package com.nocountry.ecommerce.service.implementation;

import com.nocountry.ecommerce.dto.CustomerRegistration;
import com.nocountry.ecommerce.dto.CustomerUpdate;
import com.nocountry.ecommerce.dto.EmailValues;
import com.nocountry.ecommerce.model.Account;
import com.nocountry.ecommerce.model.Customers;
import com.nocountry.ecommerce.repository.AccountRepository;
import com.nocountry.ecommerce.repository.CustomerRepository;
import com.nocountry.ecommerce.security.jwt.JwtProvider;
import com.nocountry.ecommerce.service.AccountService;
import com.nocountry.ecommerce.service.EmailService;
import com.nocountry.ecommerce.util.enums.Role;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private AccountRepository accountRepository;
    @Value("${verification.base-url}")
    protected String baseUrl;
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String emailFrom;
    @Autowired
    private EmailService emailService;

    @Override
    public CustomerRegistration createCustomer(Customers customers){
        CustomerRegistration customerRegistration = new CustomerRegistration();
        int anoActual = LocalDate.now().getYear();
        String numeracion = obtenerNumeracionAutomatica();
        
        String email = customers.getEmail();
        customerRegistration.setEmail(email);
        String password = passwordEncoder.encode(customers.getPassword());
        customerRegistration.setPassword(password);
        String name = customers.getName();
        String lastName = customers.getLastName();
        String fullName = name + " " + lastName;
        customerRegistration.setFullName(fullName);
        String verificationCode = RandomString.make(64);
        customerRegistration.setVerificationCode(verificationCode);
        

        Customers saveCustomer = new Customers(email, password);
        saveCustomer.setEmail(email);
        saveCustomer.setPassword(password);
        saveCustomer.setName(name);
        saveCustomer.setLastName(lastName);
        String customerNumber = anoActual + "-" + numeracion;
        saveCustomer.setRol(Role.USER);
        saveCustomer.setNumber(customerNumber);
        saveCustomer.setVerificationCode(verificationCode);
        String jwt = jwtProvider.generateToken(saveCustomer);
        customerRegistration.setToken(jwt);

        try {
            this.sendVerificationCodeToEmail(saveCustomer);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        customerRepository.save(saveCustomer);

        return customerRegistration;
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        try {
            if (email == null || email.isEmpty()) {
                throw new IllegalArgumentException("Email cannot be empty");
            }
            return accountRepository.findByEmail(email);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error finding account by email: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error finding account by email", e);
        }
    }

    @Override
    public Optional<Account> findByTokenPassword(String tokenPassword) {
        try {
            if (tokenPassword == null || tokenPassword.isEmpty()) {
                throw new IllegalArgumentException("Token cannot be empty");
            }
            return accountRepository.findByTokenPassword(tokenPassword);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error finding Token by email: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error finding Token by email", e);
        }
    }
    
    @Override
    public CustomerUpdate updateCustomer(Customers customer) {
        if(customerRepository.findByEmail(String.valueOf(customer.getEmail())).isEmpty()){
            throw new IllegalStateException("Customer does not exists");
        }

        Optional<Customers> existingCustomer = customerRepository.findByEmail(customer.getEmail());
        Customers customerUpdated = existingCustomer.get();

        CustomerUpdate customerDto = new CustomerUpdate();
        String name = customer.getName();
        customerDto.setName(name);
        String lastName = customer.getLastName();
        customerDto.setLastName(lastName);
        String country = customer.getCountry();
        customerDto.setCountry(country);
        String address = customer.getAddress();
        customerDto.setAddress(address);

        customerUpdated.setName(name);
        customerUpdated.setLastName(lastName);
        customerUpdated.setCountry(country);
        customerUpdated.setAddress(address);

        customerRepository.save(customerUpdated);

        return customerDto;
    }

    private String obtenerNumeracionAutomatica() {
        String maxNumber = accountRepository.findByNumber();
        if (maxNumber == null || maxNumber.isEmpty()) {
            return "1";
        } else {
            int separatorIndex = maxNumber.indexOf("-");
            if (separatorIndex != -1 && separatorIndex + 1 < maxNumber.length()) {
                String numeracion = maxNumber.substring(separatorIndex + 1);
                int number = Integer.parseInt(numeracion.trim());
                number++;
                return String.valueOf(number);
            } else {
                return "1";
            }
        }
    }
    
    public void sendVerificationCodeToEmail(Customers customers) throws MessagingException, UnsupportedEncodingException {
        String subject = " Please verify your registration";
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
        mailContent += "<p> Dear " + customers.getName() + " " + customers.getLastName() + ",</p>";
        mailContent += "<p> Please click the link below to verify to your registration:</p>";
        
        String verifyURL = baseUrl + "verify/" + customers.getVerificationCode();
        mailContent += "<h3><a href=\"" + verifyURL + "\" target=_blank >Click to verify your account</a></h3>";
        
        mailContent +=  "<p> Thanks you <br> Ecommerce Team </p>";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        
        helper.setFrom(emailFrom,senderName);
        helper.setTo(customers.getEmail());
        helper.setSubject(subject);
        helper.setText(mailContent,true);
        
        javaMailSender.send(message);
    }
    @Transactional
    @Override
    public boolean verifyAccount(String verificationCode) {
        Account account = accountRepository.findByVerificationCode(verificationCode);

        if (account == null || account.isActive()) {
            return false;
        } else {
            account.setActive(true);
            return true;
        }
    }
    @Override
    public EmailValues sendPasswordRecoveryToEmail(Customers emailRecoverPass) throws MessagingException, UnsupportedEncodingException {
        String email = emailRecoverPass.getEmail();
        
        Customers customersRequest = customerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("The account does not exist." + email));


        EmailValues emailValues = new EmailValues();
        
        emailValues.setMailTo(customersRequest.getEmail());
        
        String fullName = customersRequest.getName() + " " + customersRequest.getLastName();
        emailValues.setFullName(fullName);
        
        UUID uuid = UUID.randomUUID();
        String tokenPassword = uuid.toString();
        emailValues.setToken(tokenPassword);
        customersRequest.setTokenPassword(tokenPassword);

        String subject = "Password recovery by Ecommerce Team";
        emailValues.setSubject(subject);
        
        customerRepository.save(customersRequest);
        emailService.sendEmailForgotPassword(emailValues);
        
        return emailValues;
    }
}
