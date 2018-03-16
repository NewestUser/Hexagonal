package com.jprofessionals.di.core.professionals;

import java.math.BigDecimal;

public class PaymentDetails {

  public final String userId;
  public final String provider;
  public final BigDecimal amount;
  public final String currency;

  public PaymentDetails(String userId, String provider, BigDecimal amount, String currency) {
    this.userId = userId;
    this.provider = provider;
    this.amount = amount;
    this.currency = currency;
  }


}
