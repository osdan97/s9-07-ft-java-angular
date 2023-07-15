package com.nocountry.ecommerce.service.apipayment;

import java.math.BigDecimal;

import com.nocountry.ecommerce.dto.Mensaje;
import com.nocountry.ecommerce.model.Pay;
import net.authorize.Environment;
import net.authorize.api.contract.v1.*;
import net.authorize.api.controller.base.ApiOperationBase;
import net.authorize.api.controller.CreateTransactionController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ChargeCreditCard {
    @Value("${spring.net.authorize.loginapi}")
    String loginId;

    @Value("${spring.net.authorize.transactionkey}")
    String transactionKey;
    public Mensaje payment(Pay pay) {

        //Common code to set for all requests
        ApiOperationBase.setEnvironment(Environment.SANDBOX);
        MerchantAuthenticationType merchantAuthenticationType  = new MerchantAuthenticationType() ;
        merchantAuthenticationType.setName(loginId);
        merchantAuthenticationType.setTransactionKey(transactionKey);
        ApiOperationBase.setMerchantAuthentication(merchantAuthenticationType);

        // Populate the payment data
        String cardNumber = pay.getCardNumber();
        String expirationDate = pay.getExpirationDate();
        String cardCode = pay.getCardCode();
        //String paymentMethod = pay.getPaymentMethod();
        PaymentType paymentType = new PaymentType();
        CreditCardType creditCard = new CreditCardType();
        creditCard.setCardNumber(cardNumber);
        creditCard.setExpirationDate(expirationDate);
        creditCard.setCardCode(cardCode);
        paymentType.setCreditCard(creditCard);

        // Create the payment transaction request
        Double total = pay.getOrders().getTotal();
        TransactionRequestType txnRequest = new TransactionRequestType();
        txnRequest.setTransactionType(TransactionTypeEnum.AUTH_CAPTURE_TRANSACTION.value());
        txnRequest.setPayment(paymentType);
        txnRequest.setAmount(BigDecimal.valueOf(total));

        // Make the API Request
        CreateTransactionRequest apiRequest = new CreateTransactionRequest();
        apiRequest.setTransactionRequest(txnRequest);
        CreateTransactionController controller = new CreateTransactionController(apiRequest);
        controller.execute();


        CreateTransactionResponse response = controller.getApiResponse();

        if (response!=null) {

            // If API Response is ok, go ahead and check the transaction response
            if (response.getMessages().getResultCode() == MessageTypeEnum.OK) {

                TransactionResponse result = response.getTransactionResponse();
                if (result.getResponseCode().equals("1")) {
                    System.out.println(result.getResponseCode());
                    System.out.println("Successful Credit Card Transaction");
                    System.out.println(result.getAuthCode());
                    System.out.println(result.getTransId());
                    return new Mensaje("Successful Credit Card Transaction: "+ result.getAuthCode());
                }
                else
                {
                    System.out.println("Failed Transaction"+result.getResponseCode());
                    return new Mensaje("Failed Transaction"+result.getResponseCode());
                }
            }
            else
            {
                System.out.println("Failed Transaction:  "+response.getMessages().getResultCode());
                return new Mensaje("Failed Transaction:  "+response.getMessages().getResultCode());
            }
        }
        return new Mensaje("Failed Transaction");
    }
}