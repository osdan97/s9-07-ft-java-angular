package com.nocountry.ecommerce.service;

import com.nocountry.ecommerce.dto.OrderRegistration;
import com.nocountry.ecommerce.model.Customers;
import com.nocountry.ecommerce.model.Orders;
import com.nocountry.ecommerce.model.Product;
import jakarta.transaction.Transactional;

import java.util.List;

public interface OrdersService {

    @Transactional
    OrderRegistration createOrder(Orders orderRequest);

    @Transactional
    void changeState(String transactionUuid, String transactionState);

    Orders getOrderById(String transactionUuid);
}
