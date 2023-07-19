package com.nocountry.ecommerce.service.implementation;

import com.nocountry.ecommerce.dto.OrderDetailsRegistration;
import com.nocountry.ecommerce.dto.OrderRegistration;
import com.nocountry.ecommerce.dto.ShippingDetailsRegistration;
import com.nocountry.ecommerce.model.*;
import com.nocountry.ecommerce.repository.OrdersRepository;
import com.nocountry.ecommerce.repository.ShippingDetailsRepository;
import com.nocountry.ecommerce.service.AccountService;
import com.nocountry.ecommerce.service.OrdersService;
import com.nocountry.ecommerce.service.ProductService;
import com.nocountry.ecommerce.service.ShippingDetailsCustomerService;
import com.nocountry.ecommerce.util.enums.TransactionState;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ShippingDetailsRepository shippingDetailsRepository;
    @Autowired
    private ShippingDetailsCustomerService shippingDetailsCustomerService;

    @Transactional
    @Override
    public OrderRegistration createOrder(Orders orderRequest) {

        String uuid = orderRequest.getAccount().getAccountUuid();
        String shipping_uuid = orderRequest.getShippingDetails().getShippingDetailUuid();
        Customers customersRequest = accountService.findByUuid(uuid)
                .orElseThrow(() -> new UsernameNotFoundException("The account does not exist." + uuid));

        ShippingDetailsCustomer shippingCustomerUuid = shippingDetailsCustomerService.findShippingDetailsCustomerByCustomerAndShipping(uuid, shipping_uuid);

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
        order.setAccount(customersRequest);
        orderRegistration.setFullName(fullName);

        order.setDescription("");
        order.setQuantity(0);
        order.setTransaction_type("ORDEN DE VENTA");

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

        ShippingDetails shippingDetailsRequest = orderRequest.getShippingDetails();
        ShippingDetailsRegistration shippingDetailsRegistration = new ShippingDetailsRegistration();

        //ShippingDetailsCustomer

        if (shippingDetailsRequest != null){
            ShippingDetails shippingDetails = new ShippingDetails();

            if(shippingCustomerUuid == null){
                String shippingDetailsUuid = UUID.randomUUID().toString();
                shippingDetails.setShippingDetailUuid(shippingDetailsUuid);
            }else{
                shippingDetails.setShippingDetailUuid(shipping_uuid);
            }

            String nameShipping = shippingDetailsRequest.getName();
            shippingDetails.setName(nameShipping);

            String lastNameShipping = shippingDetailsRequest.getLastName();
            shippingDetails.setLastName(lastNameShipping);

            String fullNameShipping = nameShipping + " " + lastNameShipping;
            shippingDetailsRegistration.setFullName(fullNameShipping);

            String address1 = shippingDetailsRequest.getAddress1();
            shippingDetails.setAddress1(address1);
            shippingDetailsRegistration.setAddress(address1);

            String address2 = shippingDetailsRequest.getAddress2();
            shippingDetails.setAddress2(address2);
            shippingDetailsRegistration.setAddress2(address2);

            String postalCode = shippingDetailsRequest.getPostalCode();
            shippingDetails.setPostalCode(postalCode);
            shippingDetailsRegistration.setPostalCode(postalCode);

            String provincia = shippingDetailsRequest.getProvincia();
            shippingDetails.setProvincia(provincia);
            shippingDetailsRegistration.setProvincia(provincia);

            String city = shippingDetailsRequest.getCity();
            shippingDetails.setCity(city);
            shippingDetailsRegistration.setCity(city);

            String country = shippingDetailsRequest.getCountry();
            shippingDetails.setCountry(country);
            shippingDetailsRegistration.setCountry(country);

            shippingDetails.setOrder(order);
            shippingDetailsRepository.save(shippingDetails);

        }else {
            throw new IllegalArgumentException("Shipping address cannot be empty.");
        }

        orderRegistration.setShippingDetailsRegistration(shippingDetailsRegistration);

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

    @Transactional
    @Override
    public void changeState(String transactionUuid, String transactionState){
        if(transactionUuid == null){
            throw new IllegalArgumentException("Transaction Uuid is required");
        }
        if(transactionState == null){
            throw new IllegalArgumentException("Transaction State is required");
        }
        ordersRepository.changeState(transactionUuid, transactionState);
    }

    @Override
    public Orders getOrderById(String transactionUuid){
        return ordersRepository.findById(transactionUuid).orElseThrow(() ->
                new UsernameNotFoundException("The transaction does not exist" + transactionUuid));
    }
}
