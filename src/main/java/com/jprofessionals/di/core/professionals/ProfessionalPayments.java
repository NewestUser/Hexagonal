package com.jprofessionals.di.core.professionals;

public interface ProfessionalPayments {

  Payment pay(PaymentDetails details);

  Payment find(String paymentId);
}
