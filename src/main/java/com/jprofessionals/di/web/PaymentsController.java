package com.jprofessionals.di.web;

import com.jprofessionals.di.core.professionals.Payment;
import com.jprofessionals.di.core.professionals.PaymentDetails;
import com.jprofessionals.di.core.professionals.ProfessionalPayments;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
class PaymentsController {

  private final ProfessionalPayments payments;

  @Autowired
  PaymentsController(ProfessionalPayments payments) {
    this.payments = payments;
  }

  @RequestMapping(method = RequestMethod.POST, path = "/users/{uid}/payments", consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<PaymentResponse> pay(@PathVariable(name = "uid") String uid, @RequestBody PaymentRequest request) {

    PaymentDetails details = adaptRequest(uid, request);

    Payment payment = this.payments.pay(details);

    return adaptResponse(payment, HttpStatus.CREATED);
  }

  @RequestMapping(method = RequestMethod.GET, path = "/users/{uid}/payments/{id}")
  @ResponseStatus(HttpStatus.OK)
  ResponseEntity<PaymentResponse> lookup(@PathVariable(name = "id") String paymentId) {

    Payment payment = this.payments.find(paymentId);

    return adaptResponse(payment, HttpStatus.OK);
  }

  private PaymentDetails adaptRequest(String uid, PaymentRequest request) {
    return new PaymentDetails(uid, request.provider, request.amount, request.currency);
  }

  private ResponseEntity<PaymentResponse> adaptResponse(Payment payment, HttpStatus success) {
    if (payment == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    PaymentResponse response = new PaymentResponse();
    response.id = payment.getId();
    response.status = payment.getStatus().name();
    response.provider = payment.getDetails().provider;
    response.amount = payment.getDetails().amount;
    response.currency = payment.getDetails().currency;

    return new ResponseEntity<>(response, success);
  }


}
