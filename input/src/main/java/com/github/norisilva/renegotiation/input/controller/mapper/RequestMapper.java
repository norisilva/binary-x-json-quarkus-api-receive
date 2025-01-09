package com.github.norisilva.renegotiation.input.controller.mapper;

import com.github.norisilva.renegotiation.domain.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RequestMapper {
    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

    @Mapping(source = "codigoBarra", target = "codigoBarra")
    @Mapping(source = "valorPago", target = "valorPago")
    @Mapping(source = "email", target = "email")
    Payment toPayment(String codigoBarra, double valorPago, String email);
}