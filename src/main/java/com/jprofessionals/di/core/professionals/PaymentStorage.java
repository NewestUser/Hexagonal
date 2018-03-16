package com.jprofessionals.di.core.professionals;

public interface PaymentStorage {

  String storeNewPayment(PaymentStatus status, PaymentDetails details);

  Payment findPayment(String paymentId);
}
