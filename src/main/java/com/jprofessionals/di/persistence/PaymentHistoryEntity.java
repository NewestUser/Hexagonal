package com.jprofessionals.di.persistence;

import java.util.ArrayList;
import java.util.List;

class PaymentHistoryEntity {

  private Integer sequence;
  private List<PaymentEntity> payments;

  public PaymentHistoryEntity(Integer sequence, List<PaymentEntity> payments) {
    this.sequence = sequence;
    this.payments = payments;
  }

  Integer increaseSequence() {
    if (sequence == null) {
      sequence = 0;
    } else {
      sequence = ++sequence;
    }

    return sequence;
  }

  void add(PaymentEntity entity) {
    if (payments == null) {
      payments = new ArrayList<>();
    }

    payments.add(entity);
  }

  List<PaymentEntity> records() {
    if (payments == null) {
      payments = new ArrayList<>();
    }

    return payments;
  }
}
