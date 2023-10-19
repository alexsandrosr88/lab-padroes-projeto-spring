package com.dio.gof.dto;

import lombok.Data;

@Data
public class EnderecoViaCepDTO {

    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;
}
