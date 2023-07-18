package com.nocountry.ecommerce.service.implementation;

import com.nocountry.ecommerce.model.Orders;
import com.nocountry.ecommerce.model.Pay;
import com.nocountry.ecommerce.service.OrdersService;
import com.nocountry.ecommerce.service.PayService;
import com.nocountry.ecommerce.service.apipayment.ChargeCreditCard;
import com.nocountry.ecommerce.util.enums.TransactionState;
import net.authorize.api.contract.v1.ANetApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {
    @Autowired
    private ChargeCreditCard chargeCreditCard;
    @Autowired
    private OrdersService ordersService;
    @Override
    public ANetApiResponse chargePay(Pay pay) {

        String transactionUuid = pay.getOrders().getTransactionUuid();
        String transactionState = TransactionState.COMPLETED.toString();
        ordersService.changeState(transactionUuid, transactionState);

        return chargeCreditCard.run(pay);
    }
}
