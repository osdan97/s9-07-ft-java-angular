package com.nocountry.ecommerce.service;

import com.nocountry.ecommerce.dto.OrderRegistration;
import com.nocountry.ecommerce.model.Customers;
import com.nocountry.ecommerce.model.Orders;
import com.nocountry.ecommerce.model.Product;

import java.util.List;

public interface OrdersService {
    OrderRegistration createOrder(Orders order);
}
