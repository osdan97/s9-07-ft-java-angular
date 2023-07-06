package com.nocountry.ecommerce.service.implementation;

import com.nocountry.ecommerce.dto.OrderDetailsRegistration;
import com.nocountry.ecommerce.dto.OrderRegistration;
import com.nocountry.ecommerce.model.Customers;
import com.nocountry.ecommerce.model.OrderDetails;
import com.nocountry.ecommerce.model.Orders;
import com.nocountry.ecommerce.model.Product;
import com.nocountry.ecommerce.repository.CustomerRepository;
import com.nocountry.ecommerce.repository.OrdersRepository;
import com.nocountry.ecommerce.repository.ProductRepository;
import com.nocountry.ecommerce.service.OrdersService;
import com.nocountry.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductService productService;
    @Override
    public OrderRegistration createOrder(Orders order) {

        String customerEmail = order.getCustomers().getEmail();
        Customers customersRequest = customerRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new UsernameNotFoundException("The account does not exist." + customerEmail));

        OrderRegistration orderRegistration = new OrderRegistration();

        int anoActual = LocalDate.now().getYear();
        String numeracion = obtenerNumeracionAutomatica();

        String number = anoActual + "-" + numeracion;
        orderRegistration.setNumber(number);

        String name = customersRequest.getName();
        String lastName = customersRequest.getLastName();

        String fullName = name + " " + lastName;
        orderRegistration.setFullName(fullName);

        Double shippingCost = order.getShippingCost();
        orderRegistration.setShippingCost(shippingCost);


        List<OrderDetails> orderDetails = order.getOrderDetailsList();

        if (orderDetails == null || orderDetails.isEmpty()){
            orderDetails = new ArrayList<>();
            List<OrderDetailsRegistration> orderDetailsRegistrationList = orderRegistration.getOrderDetailsRegistrationList();

            for (OrderDetailsRegistration orderDetailsRegistration : orderDetailsRegistrationList){

                OrderDetails orderDetail = new OrderDetails();
                String orderDetailsUuid = UUID.randomUUID().toString();
                orderDetail.setOrderDetailUuid(orderDetailsUuid);

                String productName = orderDetailsRegistration.getProductName();
                orderDetailsRegistration.setProductName(productName);
                Product product = productService.getProduct(orderDetailsRegistration.getProductName());
                orderDetail.setProduct(product);


                int quantity = orderDetailsRegistration.getQuantity();
                orderDetailsRegistration.setQuantity(quantity);
                orderDetail.setQuantity(quantity);

                Double price = orderDetailsRegistration.getPrice();
                orderDetailsRegistration.setPrice(price);
                orderDetail.setPrice(price);

                double tax = 19.00;

                double amountTotal = calculateTotal(price,quantity);
                orderDetail.setTotalAmount(amountTotal);

                double amountTaxes = calculateTax(amountTotal,tax);
                orderDetail.setTaxesAmount(amountTaxes);

                double total = amountTotal + amountTaxes;
                orderDetailsRegistration.setTotal(total);
                orderDetail.setTotal(total);

                orderDetailsRegistrationList.add(orderDetailsRegistration);
                orderDetails.add(orderDetail);
            }
            orderRegistration.setOrderDetailsRegistrationList(orderDetailsRegistrationList);
        }

        Double total = calculateTotalForOrderDetails(orderDetails);
        orderRegistration.setTotal(total);

        Orders saveOrder = new Orders(total);
        saveOrder.setNumber(number);
        saveOrder.setTotal(total);

        Double amountTotal = calculateTotalPriceForOrderDetails(orderDetails);
        saveOrder.setAmountTotal(amountTotal);

        Double amountTaxes = calculateTotaltaxesForOrderDetails(orderDetails);
        saveOrder.setAmountTaxes(amountTaxes);

        saveOrder.setCustomers(customersRequest);


        order.setOrderDetailsList(orderDetails);
        ordersRepository.save(order);

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
    private Double calculateTotaltaxesForOrderDetails(List<OrderDetails> orderDetails) {
        Double total = 0.0;
        for (OrderDetails orderDetail : orderDetails) {
            total += orderDetail.getTaxesAmount();
        }
        return total;
    }
    private Double calculateTotalPriceForOrderDetails(List<OrderDetails> orderDetails) {
        Double total = 0.0;
        for (OrderDetails orderDetail : orderDetails) {
            total += orderDetail.getTotalAmount();
        }
        return total;
    }
    private Double calculateTotalForOrderDetails(List<OrderDetails> orderDetails) {
        Double total = 0.0;
        for (OrderDetails orderDetail : orderDetails) {
            total += orderDetail.getTotal();
        }
        return total;
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
