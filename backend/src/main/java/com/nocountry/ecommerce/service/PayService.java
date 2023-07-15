package com.nocountry.ecommerce.service;

import com.nocountry.ecommerce.dto.Mensaje;
import com.nocountry.ecommerce.model.Pay;

public interface PayService {
    Mensaje chargePay(Pay pay);
}
