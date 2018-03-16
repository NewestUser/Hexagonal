package com.jprofessionals.di.core.professionals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ProfessionalPaymentsImpl implements ProfessionalPayments {

  private final PaymentGateway gateway;
  private final PaymentStorage storage;

  @Autowired
  ProfessionalPaymentsImpl(PaymentGateway gateway, PaymentStorage storage) {
    this.gateway = gateway;
    this.storage = storage;
  }

  @Override
  public Payment pay(PaymentDetails details) {

    PaymentStatus status = gateway.process(details);

    String id = storage.storeNewPayment(status, details);

    return new Payment(id, status, details);
  }

  @Override
  public Payment find(String paymentId) {

    return storage.findPayment(paymentId);
  }

}
