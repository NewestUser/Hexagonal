package com.jprofessionals.di.core.professionals;

public class Payment {

  private final String id;
  private final PaymentStatus status;
  private final PaymentDetails details;

  public Payment(String id, PaymentStatus status, PaymentDetails details) {
    this.id = id;
    this.status = status;
    this.details = details;
  }

  public String getId() {
    return id;
  }

  public PaymentStatus getStatus() {
    return status;
  }

  public PaymentDetails getDetails() {
    return details;
  }

  @Override
  public String toString() {
    return "Payment{" +
            "id='" + id + '\'' +
            ", status=" + status +
            ", details=" + details +
            '}';
  }
}
