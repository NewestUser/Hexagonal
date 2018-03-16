package com.jprofessionals.di.persistence;

import com.google.gson.Gson;
import com.jprofessionals.di.core.professionals.Payment;
import com.jprofessionals.di.core.professionals.PaymentDetails;
import com.jprofessionals.di.core.professionals.PaymentStatus;
import com.jprofessionals.di.core.professionals.PaymentStorage;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class FilePaymentStorage implements PaymentStorage {

  private static final String fileName = "payment_history.json";
  private static final Gson gson = new Gson();

  @Override
  public String storeNewPayment(PaymentStatus status, PaymentDetails details) {

    PaymentHistoryEntity history = getHistory();

    Integer nextId = history.increaseSequence();

    PaymentEntity entity = new PaymentEntity(nextId.toString(), status,
            details.userId, details.provider, details.amount, details.currency);

    history.add(entity);

    storeHistory(history);

    return entity.id;
  }

  @Override
  public Payment findPayment(String paymentId) {

    PaymentHistoryEntity history = getHistory();

    for (PaymentEntity entity : history.records()) {
      if (entity.id.equals(paymentId)) {

        PaymentDetails details = new PaymentDetails(entity.userId, entity.provider, entity.amount, entity.currency);

        return new Payment(entity.id, entity.status, details);
      }
    }

    return null;
  }

  private void storeHistory(PaymentHistoryEntity history) {

    try {
      String json = gson.toJson(history);
      byte[] data = json.getBytes();

      FileOutputStream out = new FileOutputStream(fileName);
      out.write(data);
      out.flush();

    } catch (IOException e) {
      throw new RuntimeException("Some error handling while storing data is required", e);
    }

  }

  private PaymentHistoryEntity getHistory() {

    try {
      File file = new File(fileName);

      if (!file.exists()) {
        file.createNewFile();
      }

      String content = readFile(fileName, Charset.defaultCharset());

      if (content.isEmpty()) {
        content = "{}";
      }

      return gson.fromJson(content, PaymentHistoryEntity.class);

    } catch (IOException e) {
      throw new RuntimeException("Some error handling while fetching data is required", e);
    }

  }

  private String readFile(String path, Charset encoding) throws IOException {
    byte[] encoded = Files.readAllBytes(Paths.get(path));
    return new String(encoded, encoding);
  }

}
