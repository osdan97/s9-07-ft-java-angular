package com.nocountry.ecommerce.service.implementation;

import com.nocountry.ecommerce.dto.Mensaje;
import com.nocountry.ecommerce.model.Orders;
import com.nocountry.ecommerce.model.Pay;
import com.nocountry.ecommerce.repository.OrdersRepository;
import com.nocountry.ecommerce.service.OrdersService;
import com.nocountry.ecommerce.service.PayService;
import com.nocountry.ecommerce.service.apipayment.ChargeCreditCard;
import com.nocountry.ecommerce.util.enums.TransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {
    @Autowired
    private ChargeCreditCard chargeCreditCard;
    @Autowired
    private OrdersService ordersService;
    @Override
    public Mensaje chargePay(Pay pay) {
        pay.setTransactionState(TransactionState.COMPLETED);
        String transactionUuid = pay.getOrders().getTransactionUuid();
        Orders orders = ordersService.getOrderById(transactionUuid);
        pay.setOrders(orders);
        String transactionState = TransactionState.COMPLETED.toString();
        ordersService.changeState(transactionState, transactionUuid);
        return chargeCreditCard.payment(pay);
    }
}
