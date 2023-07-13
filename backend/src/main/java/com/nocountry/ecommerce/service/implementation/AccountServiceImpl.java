package com.nocountry.ecommerce.service.implementation;

import com.nocountry.ecommerce.dto.*;
import com.nocountry.ecommerce.model.Account;
import com.nocountry.ecommerce.model.Customers;
import com.nocountry.ecommerce.model.Phones;
import com.nocountry.ecommerce.model.Users;
import com.nocountry.ecommerce.repository.AccountRepository;
import com.nocountry.ecommerce.repository.CustomerRepository;
import com.nocountry.ecommerce.repository.UserRepository;
import com.nocountry.ecommerce.security.jwt.JwtProvider;
import com.nocountry.ecommerce.service.AccountService;
import com.nocountry.ecommerce.service.EmailService;
import com.nocountry.ecommerce.util.enums.PhoneLabel;
import com.nocountry.ecommerce.util.enums.Role;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.*;

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
    @Autowired
    private UserRepository userRepository;

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
    public UserRegistrationDto createUser(Users user){
        UserRegistrationDto userRegistration = new UserRegistrationDto();
        int anoActual = LocalDate.now().getYear();
        String numeracion = obtenerNumeracionAutomatica();

        String email = user.getEmail();
        userRegistration.setEmail(email);
        String password = passwordEncoder.encode(user.getPassword());
        userRegistration.setPassword(password);
        String name = user.getName();
        String lastName = user.getLastName();
        String fullName = name + " " + lastName;
        userRegistration.setFullName(fullName);
        String verificationCode = RandomString.make(64);
        userRegistration.setVerificationCode(verificationCode);


        Users saveUser = new Users(email, password);
        saveUser.setEmail(email);
        saveUser.setPassword(password);
        saveUser.setName(name);
        saveUser.setLastName(lastName);
        String customerNumber = anoActual + "-" + numeracion;
        saveUser.setRol(Role.ADMIN);
        saveUser.setNumber(customerNumber);
        saveUser.setVerificationCode(verificationCode);
        String jwt = jwtProvider.generateToken(saveUser);
        userRegistration.setToken(jwt);

        try {
            this.sendVerificationCodeToEmail(saveUser);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        userRepository.save(saveUser);

        return userRegistration;
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
    @Transactional
    @Override
    public CustomerUpdate updateCustomer(Customers customer) {
        String uuidCustomer = customer.getAccountUuid();

        if(customerRepository.findByAccountUuid(String.valueOf(uuidCustomer)).isEmpty()){
            throw new IllegalStateException("Customer does not exists");
        }

        Optional<Customers> existingCustomer = customerRepository.findByAccountUuid(uuidCustomer);
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

        List<Phones> phonesList = customer.getPhonesList();

        if (phonesList != null && !phonesList.isEmpty()) {
            List<PhonesDto> phonesDtoList = new ArrayList<>();

            for (Phones phone : phonesList) {
                PhonesDto newPhone = new PhonesDto();
                newPhone.setPhoneLabel(phone.getPhoneLabel());
                newPhone.setPhoneNumber(phone.getPhoneNumber());
                newPhone.setCityCode(phone.getCityCode());
                newPhone.setCountryCode(phone.getCountryCode());
                phonesDtoList.add(newPhone);
            }

            List<Phones> updatedPhones = new ArrayList<>();
            Set<PhoneLabel> existingPhoneLabels = new HashSet<>();

            for (PhonesDto phoneDto : phonesDtoList) {
                PhoneLabel phoneLabel = phoneDto.getPhoneLabel();
                String phoneNumber = phoneDto.getPhoneNumber();

                Phones existingPhone = customerUpdated.getPhonesList().stream()
                        .filter(phone -> phone.getPhoneLabel() == phoneLabel)
                        .findFirst()
                        .orElseGet(() -> {
                            Phones newPhone = new Phones();
                            newPhone.setPhoneUuid(UUID.randomUUID().toString());
                            newPhone.setPhoneLabel(phoneLabel);
                            existingPhoneLabels.add(phoneLabel);
                            return newPhone;
                        });

                existingPhone.setPhoneNumber(phoneNumber);
                existingPhone.setCityCode(phoneDto.getCityCode());
                existingPhone.setCountryCode(phoneDto.getCountryCode());
                updatedPhones.add(existingPhone);
            }

            List<Phones> existingPhones = customer.getPhonesList();
            existingPhones.removeIf(phone -> !existingPhoneLabels.contains(phone.getPhoneLabel()));

            customerUpdated.setPhonesList(updatedPhones);
            customerDto.setPhonesList(phonesDtoList);
        }

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

    public EmailValues sendVerificationCodeToEmail(Customers emailVerificationCode) throws MessagingException, UnsupportedEncodingException {

        String email = emailVerificationCode.getEmail();

        EmailValues emailValues = new EmailValues();

        emailValues.setMailTo(email);

        String fullName = emailVerificationCode.getName() + " " + emailVerificationCode.getLastName();
        emailValues.setFullName(fullName);

        String verificationCode = emailVerificationCode.getVerificationCode();
        emailValues.setToken(verificationCode);

        String subject = " Please verify your registration";
        emailValues.setSubject(subject);

        emailService.sendEmailVerificationCode(emailValues);

        return  emailValues;
    }

    public EmailValues sendVerificationCodeToEmail(Users emailVerificationCode) throws MessagingException, UnsupportedEncodingException {

        String email = emailVerificationCode.getEmail();

        EmailValues emailValues = new EmailValues();

        emailValues.setMailTo(email);

        String fullName = emailVerificationCode.getName() + " " + emailVerificationCode.getLastName();
        emailValues.setFullName(fullName);

        String verificationCode = emailVerificationCode.getVerificationCode();
        emailValues.setToken(verificationCode);

        String subject = " Please verify your registration";
        emailValues.setSubject(subject);

        emailService.sendEmailVerificationCode(emailValues);

        return  emailValues;
    }

    @Transactional
    @Override
    public boolean verifyAccount(String verificationCode) {
        Account account = accountRepository.findByVerificationCode(verificationCode);

        if (account == null || account.isActive()) {
            return false;
        } else {
            account.setActive(true);
            accountRepository.updateVerificationCode(account.getEmail());
            return true;
        }
    }
    @Override
    public EmailValues sendPasswordRecoveryToEmail(Account emailRecoverPass) throws MessagingException, UnsupportedEncodingException {
        String email = emailRecoverPass.getEmail();
        EmailValues emailValues = new EmailValues();

        if(emailRecoverPass.getRol() == Role.ADMIN){
            Users userRequest = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("The account does not exist." + email));
            emailValues.setMailTo(userRequest.getEmail());

            String fullName = userRequest.getName() + " " + userRequest.getLastName();
            emailValues.setFullName(fullName);

            UUID uuid = UUID.randomUUID();
            String tokenPassword = uuid.toString();
            emailValues.setToken(tokenPassword);
            userRequest.setTokenPassword(tokenPassword);

            String subject = "Password recovery by Ecommerce Team";
            emailValues.setSubject(subject);

            userRepository.save(userRequest);
            emailService.sendEmailForgotPassword(emailValues);
        }else{
            Customers customersRequest = customerRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("The account does not exist." + email));
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
        }

        return emailValues;
    }
    @Override
    public Account changePassword(ChangePassword changePassword){

        String token = changePassword.getTokenPassword();
        Account account = accountRepository.findByTokenPassword(token)
                .orElseThrow(() -> new UsernameNotFoundException("The account does not exist." + token));

        String newPassword = passwordEncoder.encode(changePassword.getPassword());
        account.setPassword(newPassword);
        account.setTokenPassword(null);

        String jwt = jwtProvider.generateToken(account);
        account.setToken(jwt);
        changePassword.setToken(jwt);
        accountRepository.save(account);

        return account;
    }
    @Override
    public Account findByUsernameReturnToken(String username){
        Account user = accountRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario no existe" + username));
        String jwt = jwtProvider.generateToken(user);
        user.setToken(jwt);
        return user;
    }

    @Override
    public Optional<Customers> findByUuid(String uuid) {
        try {
            if (uuid == null || uuid.isEmpty()) {
                throw new IllegalArgumentException("Email cannot be empty");
            }
            return customerRepository.findByAccountUuid(uuid);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error finding account by email: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error finding account by email", e);
        }
    }
}

