package com.nocountry.ecommerce.service.implementation;

import com.nocountry.ecommerce.dto.ShippingDetailsCustomerName;
import com.nocountry.ecommerce.dto.ShippingDetailsCustomerRegistration;
import com.nocountry.ecommerce.model.Customers;
import com.nocountry.ecommerce.model.ShippingDetailsCustomer;
import com.nocountry.ecommerce.repository.ShippingDetailsCustomerRepository;
import com.nocountry.ecommerce.service.AccountService;
import com.nocountry.ecommerce.service.ShippingDetailsCustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ShippingAddressCustomerServiceImpl implements ShippingDetailsCustomerService {
    @Autowired
    private AccountService accountService;
    @Autowired
    private ShippingDetailsCustomerRepository shippingDetailsCustomerRepository;
    @Transactional
    @Override
    public ShippingDetailsCustomerRegistration addShippingAddress(String accountUuid, ShippingDetailsCustomer shippingDetails) {

        Customers customer = accountService.findByUuid(accountUuid)
                .orElseThrow(() -> new UsernameNotFoundException("The account does not exist." + accountUuid));

        if (!customer.isActive()) {
            throw new IllegalStateException("Account is not active.");
        }


        if (shippingDetails.isPrimaryAddress()) {
            // Desactivar direcciones anteriores como primary
            List<ShippingDetailsCustomer> existingAddresses = customer.getShippingDetailsList();
            existingAddresses.forEach(address -> address.setPrimaryAddress(false));
        }

        ShippingDetailsCustomer shippingDetailsRequest = new ShippingDetailsCustomer();
        shippingDetailsRequest.setShippingDetailUuid(UUID.randomUUID().toString());

        String shippingDetailsName = shippingDetails.getShippingDetailsName();
        shippingDetailsRequest.setShippingDetailsName(shippingDetailsName);

        String name = shippingDetails.getName();
        shippingDetailsRequest.setName(name);

        String lastName = shippingDetails.getLastName();
        shippingDetailsRequest.setLastName(lastName);

        String address1 = shippingDetails.getAddress1();
        shippingDetailsRequest.setAddress1(address1);

        String address2 = shippingDetails.getAddress2();
        shippingDetailsRequest.setAddress2(address2);

        String postalCode = shippingDetails.getPostalCode();
        shippingDetailsRequest.setPostalCode(postalCode);

        String provincia = shippingDetails.getProvincia();
        shippingDetailsRequest.setProvincia(provincia);

        String city = shippingDetails.getCity();
        shippingDetailsRequest.setCity(city);

        String country = shippingDetails.getCountry();
        shippingDetailsRequest.setCountry(country);

        shippingDetailsRequest.setCustomer(customer);
        shippingDetailsRequest.setActive(true);

        shippingDetailsRequest.setPrimaryAddress(shippingDetails.isPrimaryAddress());
        shippingDetailsRequest.setGift(shippingDetails.isGift());

        customer.getShippingDetailsList().add(shippingDetailsRequest);

        shippingDetailsCustomerRepository.save(shippingDetailsRequest);

        ShippingDetailsCustomerRegistration savedShippingDetailsDTO = new ShippingDetailsCustomerRegistration();
        savedShippingDetailsDTO.setShippingDetailsName(shippingDetailsName);
        savedShippingDetailsDTO.setName(name);
        savedShippingDetailsDTO.setLastName(lastName);
        savedShippingDetailsDTO.setAddress1(address1);
        savedShippingDetailsDTO.setAddress2(address2);
        savedShippingDetailsDTO.setPostalCode(postalCode);
        savedShippingDetailsDTO.setProvincia(provincia);
        savedShippingDetailsDTO.setCity(city);
        savedShippingDetailsDTO.setCountry(country);
        savedShippingDetailsDTO.setActive(shippingDetails.isActive());
        savedShippingDetailsDTO.setPrimaryAddress(shippingDetails.isPrimaryAddress());
        savedShippingDetailsDTO.setGift(shippingDetails.isGift());

        return savedShippingDetailsDTO;
    }
    @Override
    public void changeToPrimaryAddress(String accountUuid, String shippingDetailUuid) {
        Customers customer = accountService.findByUuid(accountUuid)
                .orElseThrow(() -> new UsernameNotFoundException("The account does not exist." + accountUuid));

        if (!customer.isActive()) {
            throw new IllegalStateException("Account is not active.");
        }

        ShippingDetailsCustomer shippingDetails = customer.getShippingDetailsList().stream()
                .filter(address -> address.getShippingDetailUuid().equals(shippingDetailUuid))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Shipping details not found."));

        if (shippingDetails.isPrimaryAddress()) {
            throw new IllegalStateException("The selected address is already the primary address.");
        }

        // Desactivar direcciones anteriores como primary
        List<ShippingDetailsCustomer> existingAddresses = customer.getShippingDetailsList();
        existingAddresses.forEach(address -> address.setPrimaryAddress(false));

        // Establecer la dirección seleccionada como primary
        shippingDetails.setPrimaryAddress(true);

        shippingDetailsCustomerRepository.save(shippingDetails);
    }
    @Override
    public List<ShippingDetailsCustomerName> findShippingDetailsCustomerByName(String customerUuid){
        List<ShippingDetailsCustomer> shippingDetailsList = shippingDetailsCustomerRepository.findByCustomerAccountUuid(customerUuid);

        List<ShippingDetailsCustomerName> shippingDetailsCustomerNameList = new ArrayList<>();
        for (ShippingDetailsCustomer shippingDetails : shippingDetailsList) {
            ShippingDetailsCustomerName shippingDetailsCustomerName = new ShippingDetailsCustomerName();
            String name = shippingDetails.getShippingDetailsName();
            shippingDetailsCustomerName.setShippingDetailsCustomerName(name);
            shippingDetailsCustomerNameList.add(shippingDetailsCustomerName);
        }
        return shippingDetailsCustomerNameList;
    }
}
