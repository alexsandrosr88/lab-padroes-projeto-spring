package com.dio.gof.model;

import com.dio.gof.dto.EnderecoViaCepDTO;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cep;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;


    public Endereco(EnderecoViaCepDTO enderecoViaCepDTO){
        cep = enderecoViaCepDTO.getCep();
        logradouro = enderecoViaCepDTO.getLogradouro();
        bairro = enderecoViaCepDTO.getBairro();
        cidade = enderecoViaCepDTO.getLocalidade();
        uf = enderecoViaCepDTO.getUf();
    }

}
