package com.jprofessionals.di.gateway;

import com.jprofessionals.di.core.professionals.PaymentDetails;
import com.jprofessionals.di.core.professionals.PaymentGateway;
import com.jprofessionals.di.core.professionals.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class PaymentRouter implements PaymentGateway {

  private final PaysafeGateway paysafe;
  private final PayPalGateway paypal;

  @Autowired
  PaymentRouter(PaysafeGateway paysafe, PayPalGateway paypal) {
    this.paysafe = paysafe;
    this.paypal = paypal;
  }

  @Override
  public PaymentStatus process(PaymentDetails details) {

    switch (details.provider) {

      case "paypal":
        return paypal.process(details);

      case "paysafe":
        return paysafe.process(details);

      default:
        return PaymentStatus.FAILED;
    }

  }
}
