package com.dio.gof.dto;

import lombok.Data;

@Data
public class ClienteRequestDTO {
    private String nome;
    private String cep;
    private Integer numero;
    private String complemento;
}