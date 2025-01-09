package com.github.norisilva.renegotiation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {

    private String codigoBarra;
    private BigDecimal valorPago;
    private String cpfCnpj;
    private String nomePagador;
    private String email;
    private String telefone;
    private String endereco;
    private String cidade;
    private String estado;
    private String cep;
    private String pais;
    private String numeroTransacao;
    private String dataPagamento;
    private String horaPagamento;
    private String metodoPagamento;
    private String statusPagamento;
    private String historicoVida;

}
