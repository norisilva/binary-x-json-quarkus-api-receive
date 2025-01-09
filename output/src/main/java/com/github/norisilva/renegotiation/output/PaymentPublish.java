package com.github.norisilva.renegotiation.output;

import com.github.norisilva.renegotiation.domain.Payment;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@ApplicationScoped
public class PaymentPublish {

    public void publishPayment(Payment payment) {
        try {
            URL url = new URL("http://localhost:9090/send");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/octet-stream");

            try (OutputStream os = connection.getOutputStream()) {
                os.write(serializePayment(payment));
                os.flush();
            }

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_ACCEPTED
                    && responseCode != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Resposta inesperada do Kafka : " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Payment sent to Kafka");
    }

    private byte[] serializePayment(Payment payment) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(payment);
        out.flush();
        return bos.toByteArray();
    }
}