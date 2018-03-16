package com.jprofessionals.di.gateway;

import com.jprofessionals.di.core.professionals.PaymentDetails;
import com.jprofessionals.di.core.professionals.PaymentStatus;
import org.springframework.stereotype.Component;

@Component
class PayPalGateway {

  PaymentStatus process(PaymentDetails details) {

    // process payment details and return response

    return PaymentStatus.FAILED;
  }
}
