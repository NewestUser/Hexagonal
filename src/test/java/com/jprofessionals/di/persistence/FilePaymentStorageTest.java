package com.jprofessionals.di.persistence;

import com.jprofessionals.di.core.professionals.Payment;
import com.jprofessionals.di.core.professionals.PaymentDetails;
import com.jprofessionals.di.core.professionals.PaymentStatus;
import com.jprofessionals.di.core.professionals.PaymentStorage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static com.jprofessionals.di.core.professionals.PaymentStatus.FAILED;
import static com.jprofessionals.di.core.professionals.PaymentStatus.SUCCESSFUL;
import static java.math.BigDecimal.TEN;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FilePaymentStorageTest {

  private final PaymentStorage storage = new FilePaymentStorage();

  @Test
  public void findStoredPayment() throws Exception {
    PaymentStatus successful = SUCCESSFUL;
    PaymentDetails details = new PaymentDetails("uid", "paysafe", TEN, "BGN");

    String id = storage.storeNewPayment(successful, details);

    Payment payment = storage.findPayment(id);

    assertThat(payment.getId(), is(id));
    assertThat(payment.getStatus(), is(successful));
  }

  @Test
  public void storeMultiplePayments() throws Exception {
    PaymentDetails successfulPayment = new PaymentDetails("uid1", "paysafe", TEN, "BGN");
    PaymentDetails failedPayment = new PaymentDetails("uid2", "paysafe", TEN, "BGN");

    String firstId = storage.storeNewPayment(SUCCESSFUL, successfulPayment);
    String secondId = storage.storeNewPayment(FAILED, failedPayment);

    Payment firstPayment = storage.findPayment(firstId);
    Payment secondPayment = storage.findPayment(secondId);

    assertThat(firstPayment.getStatus(), is(SUCCESSFUL));
    assertThat(secondPayment.getStatus(), is(FAILED));
  }

  @After
  public void tearDown() throws Exception {
    Files.deleteIfExists(Paths.get("payment_history.json"));
  }
}