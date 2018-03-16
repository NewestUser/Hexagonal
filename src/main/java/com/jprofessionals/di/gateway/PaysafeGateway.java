package com.jprofessionals.di.gateway;

import com.jprofessionals.di.core.professionals.PaymentDetails;
import com.jprofessionals.di.core.professionals.PaymentGateway;
import com.jprofessionals.di.core.professionals.PaymentStatus;
import org.springframework.stereotype.Component;

@Component
class PaysafeGateway implements PaymentGateway {

  @Override
  public PaymentStatus process(PaymentDetails details) {

    // process payment details and return response

    return PaymentStatus.SUCCESSFUL;
  }

}
