package com.jprofessionals.di;

import com.jprofessionals.di.core.professionals.Payment;
import com.jprofessionals.di.core.professionals.ProfessionalPayments;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.jprofessionals.di.core",
//        "com.jprofessionals.di.web", // start without this module (just because I can)
        "com.jprofessionals.di.gateway",
        "com.jprofessionals.di.persistence",
})
public class App {

  public static void main(String[] args) {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);

    ProfessionalPayments payments = context.getBean(ProfessionalPayments.class);

    System.out.println("=====================================");

    String paymentId = getPaymentId(args);

    Payment payment = payments.find(paymentId);

    printPayment(paymentId, payment);

    System.out.println("=====================================");

    context.close();
  }

  private static String getPaymentId(String[] args) {
    return (args != null && args.length > 0) ? args[0] : "0";
  }

  private static void printPayment(String id, Payment payment) {
    if (payment == null) {
      System.out.println("Payment with id '" + id + "' was not found");
      return;
    }

    System.out.println(payment);
  }

}
