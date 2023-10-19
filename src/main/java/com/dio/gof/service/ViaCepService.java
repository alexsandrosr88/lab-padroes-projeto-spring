package com.dio.gof.service;

import com.dio.gof.dto.EnderecoViaCepDTO;
import com.dio.gof.model.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*
Client HTTP, criado via OpenFeign, para o consumo da API do ViaCep
https://spring.io/projects/spring-cloud-openfeign
https://viacep.com.br
 */
@FeignClient(name = "viacep", url = "http://viacep.com.br/ws")
public interface ViaCepService {

    @GetMapping("/{cep}/json/")
    EnderecoViaCepDTO consultarCep(@PathVariable("cep") String cep);
}