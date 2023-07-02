package com.nocountry.ecommerce.service.implementation;

import com.nocountry.ecommerce.dto.CustomerRegistration;
import com.nocountry.ecommerce.dto.CustomerUpdate;
import com.nocountry.ecommerce.model.Account;
import com.nocountry.ecommerce.model.Customers;
import com.nocountry.ecommerce.repository.AccountRepository;
import com.nocountry.ecommerce.repository.CustomerRepository;
import com.nocountry.ecommerce.service.AccountService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
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
        String customerNumber = anoActual + "-" + numeracion;
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
}
