package com.nocountry.ecommerce.service.implementation;

import com.nocountry.ecommerce.dto.CustomerRegistration;
import com.nocountry.ecommerce.model.Account;
import com.nocountry.ecommerce.model.Customers;
import com.nocountry.ecommerce.repository.AccountRepository;
import com.nocountry.ecommerce.repository.CustomerRepository;
import com.nocountry.ecommerce.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;
    
    private int numeracionAutomatica = 1;

    @Override
    public CustomerRegistration createCustomer(Customers customers){
        CustomerRegistration customerRegistration = new CustomerRegistration();
        int anoActual = LocalDate.now().getYear();
        String numeracion = obtenerNumeracionAutomatica();
        
        String email = customers.getEmail();
        customerRegistration.setEmail(email);
        String password = customers.getPassword();
        customerRegistration.setPassword(password);
        String name = customers.getName();
        String lastName = customers.getLastName();
        String fullName = name + " " + lastName;
        customerRegistration.setFullName(fullName);

        Customers saveCustomer = new Customers(email, password);
        saveCustomer.setEmail(email);
        saveCustomer.setPassword(password);
        saveCustomer.setName(name);
        saveCustomer.setLastName(lastName);
        String customerNumber = anoActual + " - " + numeracion;
        saveCustomer.setNumber(customerNumber);

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
    
    private String obtenerNumeracionAutomatica(){
        String numeracion = String.format("%4d", numeracionAutomatica);
        numeracionAutomatica++;
        return numeracion;
    }
}
