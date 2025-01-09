package com.github.norisilva.renegotiation.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.norisilva.renegotiation.domain.Payment;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@ApplicationScoped
public class PaymentJsonPublish {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void publishPayment(Payment payment) {
        try {
            URL url = new URL("http://localhost:9090/sendJson");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            try (OutputStream os = connection.getOutputStream()) {
                os.write(serializePayment(payment));
                os.flush();
            }

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_ACCEPTED
                    && responseCode != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Unexpected response from server: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Payment sent to server");
    }

    private byte[] serializePayment(Payment payment) throws IOException {
        return objectMapper.writeValueAsBytes(payment);
    }
}