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
import com.nocountry.ecommerce.util.enums.TransactionState;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public OrderRegistration createOrder(Orders orderRequest) {

        String customerEmail = orderRequest.getCustomers().getEmail();
        Customers customersRequest = customerRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new UsernameNotFoundException("The account does not exist." + customerEmail));

        Orders order = new Orders();
        OrderRegistration orderRegistration = new OrderRegistration();

        String transactionUuid = UUID.randomUUID().toString();
        order.setTransactionUuid(transactionUuid);

        LocalDateTime createdDate = LocalDateTime.now();
        order.setCreatedDate(createdDate);
        orderRegistration.setCreatedDate(createdDate);

        order.setTransactionState(TransactionState.ON_HOLD);
        orderRegistration.setTransactionState(TransactionState.ON_HOLD);

        int currentYear = LocalDate.now().getYear();
        String numeration = obtenerNumeracionAutomatica();
        String number = currentYear + "-" + numeration;
        order.setNumber(number);
        orderRegistration.setNumber(number);

        String name = customersRequest.getName();
        String lastName = customersRequest.getLastName();

        String fullName = name + " " + lastName;
        order.setCustomers(customersRequest);
        orderRegistration.setFullName(fullName);

        Double shippingCost = order.getShippingCost();
        orderRegistration.setShippingCost(shippingCost);

        List<OrderDetails> orderDetailsList = orderRequest.getOrderDetailsList();
        List<OrderDetailsRegistration> orderDetailsRegistrationList = new ArrayList<>();

        if (orderDetailsList != null && !orderDetailsList.isEmpty()) {
            for (OrderDetails orderDetail : orderDetailsList) {
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                OrderDetailsRegistration orderDetailsRegistration = new OrderDetailsRegistration();
                String productUuid = orderDetail.getProduct().getId();

                Product product = productService.getProductByUuid(productUuid)
                        .orElseThrow(() -> new EntityNotFoundException("The account does not exist." + productUuid));

                orderDetailsRegistration.setProductName(product.getName());

                int quantity = orderDetail.getQuantity();
                orderDetailsRegistration.setQuantity(quantity);

                Double price = product.getPrice();
                orderDetailsRegistration.setPrice(price);

                double tax = 19.00;
                double amountTotal = calculateTotal(price, quantity);
                orderDetailsRegistration.setTotalAmount(amountTotal);

                double amountTaxes = calculateTax(amountTotal, tax);
                orderDetailsRegistration.setTaxesAmount(amountTaxes);

                double total = amountTotal + amountTaxes;
                orderDetailsRegistration.setTotal(Double.valueOf(decimalFormat.format(total)));

                orderDetailsRegistrationList.add(orderDetailsRegistration);
            }
            order.setOrderDetailsList(orderDetailsList);

            List<OrderDetails> updatedOrderDetailsList = new ArrayList<>();
            for (OrderDetailsRegistration registration : orderDetailsRegistrationList) {

                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setOrderDetailUuid(UUID.randomUUID().toString());
                String productName = registration.getProductName();
                Product product = productService.getProduct(productName)
                        .orElseThrow(() -> new EntityNotFoundException("The account does not exist." + productName));
                orderDetails.setProduct(product);
                orderDetails.setQuantity(registration.getQuantity());
                orderDetails.setPrice(registration.getPrice());
                orderDetails.setTotalAmount(registration.getTotalAmount());
                orderDetails.setTaxesAmount(registration.getTaxesAmount());
                orderDetails.setTotal(registration.getTotal());
                updatedOrderDetailsList.add(orderDetails);
            }

            order.setOrderDetailsList(updatedOrderDetailsList);

            Double amountTotal = calculateTotalPriceForOrderDetails(updatedOrderDetailsList);
            order.setAmountTotal(amountTotal);
            orderRegistration.setAmountTotal(amountTotal);

            Double amountTaxes = calculateTotaltaxesForOrderDetails(updatedOrderDetailsList);
            order.setAmountTaxes(amountTaxes);
            orderRegistration.setAmountTaxes(amountTaxes);

            Double total = calculateTotalForOrderDetails(updatedOrderDetailsList);
            order.setTotal(total);
            orderRegistration.setTotal(total);
        }

        orderRegistration.setOrderDetailsRegistrationList(orderDetailsRegistrationList);

        ordersRepository.save(order);

        return orderRegistration;
    }

    private double calculateTotal(Double price, int quantity) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        Double amount = price * quantity;
        return Double.parseDouble(decimalFormat.format(amount));
    }

    private double calculateTax(double amount, double tax) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        Double percentageTax = (double) (tax/100);
        Double amountTaxes = amount * percentageTax;

        return Double.parseDouble(decimalFormat.format(amountTaxes));
    }
    private Double calculateTotaltaxesForOrderDetails(List<OrderDetails> orderDetails) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        Double total = 0.0;
        for (OrderDetails orderDetail : orderDetails) {
            total += orderDetail.getTaxesAmount();
        }
        return Double.valueOf(decimalFormat.format(total));
    }
    private Double calculateTotalPriceForOrderDetails(List<OrderDetails> orderDetails) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        Double total = 0.0;
        for (OrderDetails orderDetail : orderDetails) {
            total += orderDetail.getTotalAmount();
        }
        return Double.valueOf(decimalFormat.format(total));
    }
    private Double calculateTotalForOrderDetails(List<OrderDetails> orderDetails) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        Double total = 0.0;
        for (OrderDetails orderDetail : orderDetails) {
            total += orderDetail.getTotal();
        }
        return Double.valueOf(decimalFormat.format(total));
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
