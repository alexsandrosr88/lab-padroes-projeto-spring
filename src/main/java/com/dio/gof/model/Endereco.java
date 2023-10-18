package com.dio.gof.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
Os atributos desse modelo foram gerados automaticamente pelo site jsonschema2pojo.org.
Para isso, usamos o JSON de retorno da API do VIACEP.
https://wwww.jsonschema2pojo.org
https://viacep.com.br
 */
@Data
@Entity
public class Endereco {

    @Id
    private String cep;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;

}
