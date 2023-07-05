package com.nocountry.ecommerce.service;

import com.nocountry.ecommerce.dto.OrderRegistration;
import com.nocountry.ecommerce.model.Orders;

public interface OrdersService {
    OrderRegistration createOrder(Orders order);
}
