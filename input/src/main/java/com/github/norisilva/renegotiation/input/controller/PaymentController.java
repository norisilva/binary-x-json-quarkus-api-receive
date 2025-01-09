package com.github.norisilva.renegotiation.input.controller;

import com.github.norisilva.renegotiation.business.PaymentProcessor;
import com.github.norisilva.renegotiation.domain.Payment;
import com.github.norisilva.renegotiation.input.controller.mapper.RequestMapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;


@Path("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private  final PaymentProcessor paymentProcessor;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String handler(@QueryParam("codigoBarra") String codigoBarra, @QueryParam("valorPago") double valorPago, @QueryParam("email") String email) {
        Payment payment = RequestMapper.INSTANCE.toPayment(codigoBarra, valorPago, email);
        paymentProcessor.processPayment(payment);
        return "Valor pago: " + payment.getValorPago() + " para o código de barras: " + payment.getCodigoBarra() + ". Aguarde o processamento, você receberá uma mensagem com o comprovante no email: " + payment.getEmail();

    }
}