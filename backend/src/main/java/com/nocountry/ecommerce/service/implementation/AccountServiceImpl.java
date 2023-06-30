package com.nocountry.ecommerce.service.implementation;

import com.nocountry.ecommerce.dto.CustomerRegistration;
import com.nocountry.ecommerce.model.Customers;
import com.nocountry.ecommerce.repository.CustomerRepository;
import com.nocountry.ecommerce.service.AccountService;
import com.nocountry.ecommerce.util.enums.Entities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerRegistration createCustomer(Customers customers){
        CustomerRegistration customerRegistration = new CustomerRegistration();
        String email = customers.getEmail();
        customerRegistration.setEmail(email);
        String password = customers.getPassword();
        customerRegistration.setPassword(password);
        String name = customers.getName();
        String lastName = customers.getLastname();
        String fullName = name + " " + lastName;
        customerRegistration.setFullName(fullName);

        Customers saveCustomer = new Customers();
        saveCustomer.setEmail(email);
        saveCustomer.setPassword(password);
        saveCustomer.setName(name);
        saveCustomer.setLastname(lastName);
        saveCustomer.setEntity(Entities.CUSTOMER);

        customerRepository.save(saveCustomer);

        return customerRegistration;
    }
}
