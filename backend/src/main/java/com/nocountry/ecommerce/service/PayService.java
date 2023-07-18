package com.nocountry.ecommerce.service;

import com.nocountry.ecommerce.model.Pay;
import net.authorize.api.contract.v1.ANetApiResponse;

public interface PayService {
    ANetApiResponse chargePay(Pay pay);
}
