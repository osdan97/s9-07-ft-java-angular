package com.nocountry.ecommerce.service.implementation;

import com.nocountry.ecommerce.dto.OrderDetailsRegistration;
import com.nocountry.ecommerce.dto.OrderRegistration;
import com.nocountry.ecommerce.model.OrderDetails;
import com.nocountry.ecommerce.model.Orders;
import com.nocountry.ecommerce.repository.OrdersRepository;
import com.nocountry.ecommerce.service.OrdersService;
import com.nocountry.ecommerce.util.enums.TransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public OrderRegistration createOrder(Orders order) {

        OrderRegistration orderRegistration = new OrderRegistration();

        int anoActual = LocalDate.now().getYear();
        String numeracion = obtenerNumeracionAutomatica();

        String number = anoActual + "-" + numeracion;
        orderRegistration.setNumber(number);

        Double shippingCost = order.getShippingCost();
        orderRegistration.setShippingCost(shippingCost);

        LocalDateTime createDate = LocalDateTime.now();
        orderRegistration.setCreatedDate(createDate);

        String transactionState = TransactionState.ON_HOLD.getValue();
        orderRegistration.setTransactionState(TransactionState.valueOf(transactionState));

        OrderDetailsRegistration orderDetailsRegistration = new OrderDetailsRegistration();
        List<OrderDetails> orderDetails = order.getOrderDetailsList();

        if (orderDetails == null || orderDetails.isEmpty()){
            orderDetails = new ArrayList<>();

            for (OrderDetailsRegistration orderDetailsElement : orderRegistration.getOrderDetailsRegistrationList()){
                OrderDetails orderDetailsProduct = new OrderDetails();
                String orderDetailsUuid = UUID.randomUUID().toString();
                orderDetailsProduct.setOrderDetailUuid(orderDetailsUuid);

                int quantity = orderDetailsElement.getQuantity();
                orderDetailsProduct.setQuantity(quantity);

                Double price = orderDetailsElement.getPrice();
                orderDetailsProduct.setPrice(price);

                double tax = 19.00;

                double amountTotal = calculateTotal(price,quantity);

                double amountTaxes = calculateTax(amountTotal,tax);
                orderDetailsProduct.setTaxes(amountTaxes);

                double total = amountTotal + amountTaxes;
                orderDetailsProduct.setTotalAmount(total);

                orderDetails.add(orderDetailsProduct);
            }
        }

        return orderRegistration;
    }

    private double calculateTotal(Double price, int quantity) {
        Double amount = price * quantity;
        return amount;
    }

    private double calculateTax(double amount, double tax) {

        Double percentageTax = (double) (tax/100);

        Double amountTaxes = amount * percentageTax;

        return amountTaxes;
    }


    private String obtenerNumeracionAutomatica() {
        String maxNumber = ordersRepository.findByNumber();
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
