package com.jprofessionals.di.persistence;

import com.jprofessionals.di.core.professionals.PaymentStatus;

import java.math.BigDecimal;

class PaymentEntity {
  String id;
  PaymentStatus status;
  String userId;
  String provider;
  BigDecimal amount;
  String currency;

  PaymentEntity(String id, PaymentStatus status, String userId, String provider, BigDecimal amount, String currency) {
    this.id = id;
    this.status = status;
    this.userId = userId;
    this.provider = provider;
    this.amount = amount;
    this.currency = currency;
  }
}
