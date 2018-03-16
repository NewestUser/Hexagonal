package com.jprofessionals.di.core.professionals;

public interface PaymentGateway {

  PaymentStatus process(PaymentDetails details);

}
